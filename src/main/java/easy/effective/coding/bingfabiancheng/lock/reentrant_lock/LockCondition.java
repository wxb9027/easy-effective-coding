package easy.effective.coding.bingfabiancheng.lock.reentrant_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition  await() && signal()基本用法:
 * await()方法会使当前线程等待，同时释放当前锁，
 * 当其他线程中使用signal()方法或者signalAll()方法时，
 * 线程会重新获得锁并继续执行。
 * 或者当线程被中断时，也能跳出等待。
 *
 * 在signal()方法调用后，系统会从当前Condition对象的等待队列中唤醒一个线程。
 * 一旦线程被唤醒，它会重新尝试获得与之绑定的重入锁，
 * 一旦成功获取，就可以继续执行了。
 * 因此，在signal()方法调用之后，一般需要释放相关的锁，
 * 让给被唤醒的线程，让它可以继续执行。
 */
public class LockCondition implements Runnable{
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    @Override
    public void run() {
        lock.lock();
        System.out.println("t1 获得锁.");
        try {
            System.out.println("await t1线程等待，释放锁.");
            condition.await();
            System.out.println("t1 is going on.");

        } catch (InterruptedException e) { //await()可以被中断
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockCondition lockCon = new LockCondition();
        Thread t1 = new Thread(lockCon);
        t1.start();

        TimeUnit.SECONDS.sleep(1);// 让t1先获得锁，如果main先获得锁，t1 await后就不会被唤醒了

        lock.lock();
        System.out.println("main 获得锁.");

        condition.signal();
        System.out.println("唤醒 t1，t1重新等待2s后，main释放锁.");
        TimeUnit.SECONDS.sleep(2);

        lock.unlock();
        System.out.println("main 释放锁，t1 执行.");
    }
}

