package easy.effective.coding.bingfabiancheng.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 介绍Executor生成的5类线程池，查看它们的重要参数
 */
public class ThreadPoolType {
    private static ExecutorService singleThread = Executors.newSingleThreadExecutor();
    private static ExecutorService workStealPool = Executors.newWorkStealingPool();
    private static ExecutorService cacheThreaPool = Executors.newCachedThreadPool();
    private static ExecutorService scheduledPool = Executors.newScheduledThreadPool(10);
    private static ExecutorService fixedPool = Executors.newFixedThreadPool(10);

}
