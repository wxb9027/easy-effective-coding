package easy.effective.coding.bingfabiancheng.lock.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *synchronized不可以被中断，指的是synchronized在等待锁时不可中断，一旦获得锁就可以被中断
 */
public class SyncInterrupt {
    public synchronized void foo_1(){  // foo_1 没有在等待锁，不会被中断
        while (true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("foo_1 被中断");
                //break;  //如果foo_1推出，foo_2得到锁后就可以被中断
            }
        }
    }
    public synchronized void foo_2(){  //foo_2 在等待锁，不可以被中断
        while (true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("foo_2 被中断");
            }
        }
    }

    public static void main(String[] args) {
        SyncInterrupt syncInterrupt = new SyncInterrupt();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            syncInterrupt.foo_1();
        });

        executorService.execute(() -> {
            syncInterrupt.foo_2();
        });

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 执行该方法后，foo_1会被不断中断，但是foo_2会不，因为foo_2在等待锁，不会被中断
         */
        executorService.shutdownNow();
        System.out.println("main end.");
    }
}
