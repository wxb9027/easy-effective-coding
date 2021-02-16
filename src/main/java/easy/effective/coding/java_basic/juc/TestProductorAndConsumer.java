/*
package easy.effective.coding.java_basic.juc;

public class TestProductorAndConsumer {

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

    //进货
    public synchronized void get() {
        while (product >= 1) {
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();
    }

    //卖货
    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + --product);
        this.notifyAll();
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
*/
