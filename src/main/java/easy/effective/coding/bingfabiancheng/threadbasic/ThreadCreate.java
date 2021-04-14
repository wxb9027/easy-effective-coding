package easy.effective.coding.bingfabiancheng.threadbasic;

import java.sql.CallableStatement;
import java.util.concurrent.Callable;

/**
 * 1. 使用接口
 * 2. 使用继承
 * 3. 使用线程池执行指定的方法（多个线程执行一个方法）
 * 待补充....
 */
public class ThreadCreate {
    public static void main(String[] args) {

        //1.直接重写run方法
        new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        // 2.使用Runnbale接口
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },"线程3").start();

        //3.使用lambda,等价于上述第2种
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName());
        },"线程1").start();

    }

}
