package easy.effective.coding.bingfa_biancheng.future.jdkfuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用jdk提供的future模式
 * Future模式是多线程开发中非常常见的一种设计模式，
 * 它的核心思想是异步调用。当我们需要调用一个函数方法时，
 * 如果这个函数执行得很慢，那么我们就要进行等待。但有时候，
 * 我们可能并不急着要结果。因此，我们可以让被调者立即返回，
 * 让它在后台慢慢处理这个请求。对于调用者来说，
 * 则可以先处理一些其他任务，在真正需要数据的场合再去尝试获得需要的数据。
 *
 *
 * Callable接口完成真实数据的组装，是Future框架和应用程序之间重要的接口；
 * FutureTask用于接收Callable实例，并提交线程池；
 */
public class App {
    public static void main(String[] args) {
        //ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()/2);
        ExecutorService pool = Executors.newWorkStealingPool();

        List<FutureTask> futures = new ArrayList<>();

        synchronized (this){
        for (int i=0 ; i<500 ;i++){
            FutureTask<String> future = new FutureTask<>(new RealData("query" + i));
            pool.submit(future);

            futures.add(future);
        }
        System.out.println("请求完毕");

        }

        try {
            System.out.println("开始其他业务。。。。");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("结束其他业务。。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("获取请求结果。。。");
        try {
            for (FutureTask future : futures ){
                // get方法为阻塞的，Guava的future不是
                System.out.println("结果：" + future.get(30,TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("main task done ...");
    }



}
