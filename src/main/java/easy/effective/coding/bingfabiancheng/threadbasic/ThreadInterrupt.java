package easy.effective.coding.bingfabiancheng.threadbasic;

import java.util.concurrent.TimeUnit;

/**
 *         Thread.currentThread().interrupt();   // 中断线程，给线程添加中断标志，还可以让
 *                                                   wait()和sleep()方法抛出InterruptedException,
 *                                                   同时中断标志被清除(这一个特性也是很重要的)
 *         Thread.currentThread().isInterrupted();  //判读线程是否中断
 *         Thread.interrupted();  // 判断线程是否中断，同时将中断状态设置为非中断
 *
 *         注意:只要一个方法可以抛出InterruptedException，就表示线程调用该方法时可以被中断。
 *
 */

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true){
                // 线程中断不会停止线程，只是给线程添加了一个被中断的标志位，通过标志位判断线程是否被中断
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + "被中断");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted when sleep.");
                    // 异常会重新设置中断标志
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
