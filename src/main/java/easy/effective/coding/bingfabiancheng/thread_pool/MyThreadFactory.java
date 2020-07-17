package easy.effective.coding.bingfabiancheng.thread_pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂：线程名称自定义
 */
public class MyThreadFactory implements ThreadFactory {
    private final String threadNamePrefix;
    private final AtomicInteger threadId = new AtomicInteger(1);

    MyThreadFactory(String featureOfThreadPool){
        this.threadNamePrefix = "中国北京，CYM机房，" + featureOfThreadPool + "-编号-";
    }
    @Override
    public Thread newThread(Runnable task) {
        String threadname = this.threadNamePrefix + threadId.getAndIncrement();
        Thread thread = new Thread(null,task,threadname);
        System.out.println("新建线程：" + thread.getName());
        return thread;
    }
}
