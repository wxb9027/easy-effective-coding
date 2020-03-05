package easy.effective.coding.bingfa_biancheng.kejianxing;

import java.util.concurrent.TimeUnit;

/**
 * @author 韦海燕 2020年2月2日18:02:15
 * The updates of one thread are not visible to other threads.
 * 1.由于t线程将stop=false放入自己的cpu缓存中，导致主线程对stop=true的修改对于线程t不可见，
 *   所以线程t不会停止
 * 2.对stop用volatile关键字进行修饰，可以使主线程对stop=true的修改写到主内存，t也会从主内存
 *   中读取stop=true.
 *
 *   解决方法：
 *   方式一： 将stop变量使用volatile修饰
 *   方式二： 让线程t休眠，t唤醒后就会从主内存读取stop
 */
public class TestKeJianXing {
    private /*volatile*/ static boolean stop = false;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            int i = 0;
            while (!stop){
                //pause(1);
                i++;
                //System.out.println("加了我，验证可见性就失败啦。为什么呢？");
            }
            System.out.println("线程成功停止.");
        });
        t.start();

        pause(2);
        System.out.printf("Stop thread.");
        stop = true;
    }

    public static void pause(int timeSecond){
        try {
            TimeUnit.SECONDS.sleep(timeSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
