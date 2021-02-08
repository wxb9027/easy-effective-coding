package easy.effective.coding.java_basic.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一、i++原子性问题：i++实际分为三个步骤：读-改-写
 *
 * 二、原子变量：jdk1.5后,java.util.concurrent.atomic包下提供了常用的原子变量：
 *  1.volatile保证了可见性；
 *  2.CAS算法保证数据的原子性（compare-and-swap）；
 *      CAS算法是硬件对于并发操作共享数据的支持。
 *      CAS包含了3个操作数：
 *          内存值V
 *          预估值A
 *          更新值B
 *       当且仅当V==A时，V=B，否则，什么操作都不做。
 *
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable {
    //    private volatile int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ":" + serialNumber.getAndIncrement());
    }
}
