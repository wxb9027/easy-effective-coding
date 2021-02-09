package easy.effective.coding.java_basic.juc;

/**
 * 多个线程操作volatile修饰的共享变量时，可以保证内存中的数据可见以及防止重排。
 * 相较于synchronized是一种较为轻量级的同步策略
 *
 * 注意：
 * 1. volatile不具备互斥性；
 * 2. 不能保证变量的原子性；
 *
 * 底层原理：
 * volatile关键字通过内存屏障来防止指令被重排序。
 * 内存屏障的3个功能：
 * 1、确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令拍到内存屏障之后；
 * 2、强制将对缓存的修改操作立即写入主存，利用缓存一致性机制，并且缓存一致性机制会阻止同时修改由两个以上CPU缓存的内存区域数据；
 * 3、如果是写操作，它会导致其他CPU中对应的缓存行无效；
 *
 * 为了实现volatile的内存语义，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序。
 * 然而，对于编译器来说，发现一个最优布置来最小化插入屏障的总数几乎不可能，为此，Java内存模型采取保守策略。
 * 下面是基于保守策略的JMM内存屏障插入策略：
 * 在每个volatile写操作的前面插入一个StoreStore屏障。
 * 在每个volatile写操作的后面插入一个StoreLoad屏障。
 * 在每个volatile读操作的后面插入一个LoadLoad屏障。
 * 在每个volatile读操作的后面插入一个LoadStore屏障。
 *
 * 缓存一致性协议：每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，
 * 当处理器发现自己缓存行对应的内存地址被修改，就会将当前处理器的缓存行设置为无效状态，当处理器对这个数据进行修改操作时，
 * 会重新从内存中把数据读到处理器缓存中。
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
