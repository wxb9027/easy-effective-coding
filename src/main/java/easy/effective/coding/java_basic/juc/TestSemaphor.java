package easy.effective.coding.java_basic.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 业务场景 ：
 * 1、停车场容纳总停车量10。
 * 2、当一辆车进入停车场后，显示牌的剩余车位数响应的减1.
 * 3、每有一辆车驶出停车场后，显示牌的剩余车位数响应的加1。
 * 4、停车场剩余车位不足时，车辆只能在外面等待。
 */
public class TestSemaphor {

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        //模拟20辆车进入停车场
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("===" + Thread.currentThread().getName() + "来到停车场");
                    try {
                        if (semaphore.availablePermits() == 0) {
                            System.out.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName() + "驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, i + "号车").start();
        }
    }

}
