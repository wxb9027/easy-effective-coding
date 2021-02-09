package easy.effective.coding.java_basic.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch，倒计时锁，闭锁，是一个同步辅助类。
 * 多个线程共享CountDownLatch实例，调用countDownLatch.await()方法的线程会被阻塞，
 * 直至其他线程调用countDownLatch.countDown()减法操作并且计数器至0
 */
public class TestCountDownLatch {

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(5);

        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间：" + (end - start));
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {


        try {
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } finally {
            latch.countDown();
        }
    }
}
