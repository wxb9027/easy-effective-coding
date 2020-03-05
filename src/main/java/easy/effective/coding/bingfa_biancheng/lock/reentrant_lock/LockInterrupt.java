package easy.effective.coding.bingfa_biancheng.lock.reentrant_lock;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 无论是sychronized还是lock，在等待锁时都是不可中断的，但是ReentrantLock有两种解决方案：
 *
 * 方案一：等锁超时：lock可以可以使用lock.tryLock(time,timeunit)在等待了足够长的时间以后没有获得锁，而干别的事情
 * 方案二：等锁时可中断
 */
public class LockInterrupt {
    private static final ReentrantLock lock = new ReentrantLock();

    private void foo_1(){
        lock.lock();
        for (int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("foo_1 被中断，i="+i);
            }
            System.out.println("foo_1 在运行，i=" + i);
        }
        if (lock.isHeldByCurrentThread()){
            lock.unlock();
        }
    }

    private void foo_2(){
        lock.lock();
        for (int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("foo_2 被中断，i=" + i);
            }
            System.out.println("foo_2 在运行，i=" + i);
        }
        if (lock.isHeldByCurrentThread()){
            lock.unlock();
        }
    }

    public void foo_3(){
        try {
            if(lock.tryLock(5,TimeUnit.SECONDS)){
                System.out.println("foo_3 在运行.");
            }else {
                System.out.println("foo_3 等待5s后未得到锁。。。不会发生中断，干其他的事情了");
            }
        } catch (InterruptedException e) {
            System.out.println("foo_3 等待锁没有够5s，发生了中断.");
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockInterrupt lockInterrupt = new LockInterrupt();

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(()->{
            lockInterrupt.foo_1();
        });
        es.execute(()->{
            lockInterrupt.foo_2();
        });

        es.execute(()->{
            lockInterrupt.foo_3();
        });

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("haha");
        }
        es.shutdownNow();  // 注释改行，trylock就起作用了
        System.out.println("main end.");
    }
}
