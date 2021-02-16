package easy.effective.coding.java_basic.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }
}

/**
 * 方式二：synchronized锁
 */
class Ticket implements Runnable {

    private int tick = 100;

    @Override
    public void run() {

        while (true) {
            synchronized (Ticket.class) {
                if (tick <= 0) {
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "完成售票，剩余票数：" + --tick);
            }
        }
    }
}

/**
 * 方式一：Lock显式锁
 */
/*class Ticket implements Runnable {

    private int tick = 300;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {
            lock.lock();
            try {
                if (tick <= 0) {
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "完成售票，剩余票数：" + --tick);
            } finally {
                lock.unlock();
            }

        }
    }
}*/
