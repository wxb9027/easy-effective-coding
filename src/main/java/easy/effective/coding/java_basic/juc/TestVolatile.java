package easy.effective.coding.java_basic.juc;

/**
 * 多个线程操作volatile修饰的共享变量时，可以保证内存中的数据可见(但不能保证原子性)。
 * 相较于synchronized是一种较为轻量级的同步策略
 *
 * 注意：
 * 1. volatile不具备互斥性；
 * 2. 不能保证变量的原子性；
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
//            synchronized (td) {
                if (td.isFlag()) {
                    System.out.println("------------");
                    break;
                }
//            }
        }
    }
}

class ThreadDemo implements Runnable{

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        flag = true;
        System.out.println("flag="+isFlag());
    }

    public boolean isFlag() {
        return flag;
    }
}
