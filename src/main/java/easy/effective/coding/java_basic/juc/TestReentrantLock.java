package easy.effective.coding.java_basic.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 什么是可重入锁（ReentrantLock）？
 * 同一个线程可以重入已经上锁的代码段。
 *
 * 实现原理：
 * ReenTrantLock的实现是一种自旋锁，通过循环调用CAS操作来实现加锁。
 * 它的性能比较好也是因为避免了使线程进入内核态的阻塞状态。
 * 想尽办法避免线程进入内核的阻塞状态是我们去分析和理解锁设计的关键钥匙。
 *
 * ReentrantLock和synchronize比较：
 * 可重入性：都具有可重入性；
 * 性能上：优化后的synchronize和Lock的性能几乎无差别；
 * 功能上：synchronize自动上锁解锁，ReentrantLock要手动上锁解锁；
 *
 * ReentrantLock独有的功能：
 * 1.ReentrantLock可以指定公平锁还是非公平锁，synchronize只能是非公平锁；
 * 2.ReenTrantLock提供了一个Condition（条件）类，用来实现分组唤醒需要唤醒的线程们，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 * 3.ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制
 *
 */
public class TestReentrantLock {

    public static void main(String[] args) throws InterruptedException {
        new Count().print();
    }
}

/**
 * 使用锁示例
 */
class Count {
//    MyLock lock = new MyLock();
//    MyReentrantLock lock = new MyReentrantLock();
    Lock lock = new ReentrantLock();

    public void print() throws InterruptedException {
        lock.lock();
        System.out.println("执行doAdd方法前");
        doAdd();
        lock.unlock();
    }

    public void doAdd() throws InterruptedException {
        lock.lock();
        System.out.println("进入doAdd方法");
        lock.unlock();
    }
}

/**
 * 不可重入锁:
 * 若当前线程执行某个方法已经获取了该锁，那么在方法中尝试再次获取锁时，就会获取不到被阻塞。
 */
class MyLock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

/**
 * 可重入锁：
 * 线程可以进入它已经拥有锁的同步代码块儿。
 */
class MyReentrantLock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock()
            throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}