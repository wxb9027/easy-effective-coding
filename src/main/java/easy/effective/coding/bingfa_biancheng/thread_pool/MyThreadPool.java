package easy.effective.coding.bingfa_biancheng.thread_pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用自己的线程工厂和拒绝策略
 */
public class MyThreadPool {
    public static void main(String[] args) {
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
