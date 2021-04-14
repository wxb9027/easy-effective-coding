package easy.effective.coding.java_basic.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟跑步场景:
 * 十名运动员各自准备比赛,需要等待所有运动员都准备好以后,裁判才能说开始然后所有运动员一起跑.
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("所有人都准备好了裁判开始了");
        });
        for (int i = 0; i < 10; i++) {
            //注意:lambda表达式和匿名内部类,只能使用外部final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
                    Thread.sleep(1000 * times);
                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
