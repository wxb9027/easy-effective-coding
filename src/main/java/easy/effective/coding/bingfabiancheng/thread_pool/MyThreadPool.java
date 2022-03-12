package easy.effective.coding.bingfabiancheng.thread_pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用自己的线程工厂和拒绝策略
 */
public class MyThreadPool {
    public static void main(String[] args) {

        /*//单线程化的线程池(重要)
        Executors.newSingleThreadExecutor();
        //定长线程池(重要)
        Executors.newFixedThreadPool(2);
        //可缓存线程池
        Executors.newCachedThreadPool();
        //定时线程池
        Executors.newScheduledThreadPool(2);
        //Java8 新增，使用目前机器上可用的处理器作为它的并行级别
        Executors.newWorkStealingPool(2);*/

        // 缓存队列
        BlockingDeque queue = new LinkedBlockingDeque(2);
        ConcurrentLinkedDeque concurrentLinkedDeque=  new ConcurrentLinkedDeque(queue);


        // 线程工厂
        MyThreadFactory esFactory = new MyThreadFactory("同步es");
        MyThreadFactory hbaseFactory = new MyThreadFactory("同步hbase");

        // 拒绝策略
        MyRejectedExecutionHandler handler = new MyRejectedExecutionHandler();

        // 同步es线程池
        ThreadPoolExecutor esThreadPool = new ThreadPoolExecutor(1,2,60, TimeUnit.SECONDS,queue,esFactory,handler);

        // 同步hbase线程池
        ThreadPoolExecutor hbThreadPool = new ThreadPoolExecutor(1,2,60,TimeUnit.SECONDS,queue,hbaseFactory,handler);

        // 创建400个任务，测试溢出
        MyTask task = new MyTask();
        for (int i=0;i<400;i++){
            esThreadPool.execute(task);
            hbThreadPool.execute(task);
        }

    }
    static  class  MyTask implements Runnable{
        private final AtomicLong count = new AtomicLong(0L);
        @Override
        public void run() {
            System.out.println("running_" + count.getAndIncrement());
        }
    }
}
