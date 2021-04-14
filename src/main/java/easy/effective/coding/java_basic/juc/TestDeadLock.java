package easy.effective.coding.java_basic.juc;

import java.util.concurrent.TimeUnit;

public class TestDeadLock {

    public static void main(String[] args) {

        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(() -> {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "获取lockA成功");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (lockB) {
                        System.out.println(Thread.currentThread().getName() + "获取lockB成功");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "获取lockB成功");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        synchronized (lockA) {
                            System.out.println(Thread.currentThread().getName() + "获取lockA成功");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "线程2").start();
    }
}


