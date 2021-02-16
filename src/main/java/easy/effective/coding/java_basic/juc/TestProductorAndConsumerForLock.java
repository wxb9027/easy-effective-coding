package easy.effective.coding.java_basic.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型
 * 通过Lock同步锁、Condition实现线程通信，以及等待唤醒机制。
 */
public class TestProductorAndConsumerForLock {

    public static void main(String[] args) {

        Clerk clerk = new Clerk();
        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(productor, "生产者1号").start();
        new Thread(productor, "生产者2号").start();
        new Thread(consumer, "消费者1号").start();
        new Thread(consumer, "消费者2号").start();
    }

}

//店员
class Clerk {
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //进货
    public void get() {
        lock.lock();
        try {
            //为避免虚假唤醒，应该总是使用在循环中 ？？？
            while (product >= 1) {
                System.out.println("产品已满");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    //卖货
    public synchronized void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

//生产者线程
class Productor implements Runnable {

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.get();
        }
    }
}

//消费者线程
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}
