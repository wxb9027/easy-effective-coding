package easy.effective.coding.bingfabiancheng.future.guavafuture;

import com.google.common.util.concurrent.*;
import easy.effective.coding.bingfabiancheng.future.jdkfuture.RealData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;

/**
 * future.get())为阻塞的，Guava的future不是.
 * 但在Guava中，增强了Future模式，增加了对Future模式完成时的回调接口，
 * 使得Future完成时可以自动通知应用程序进行后续处理。
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        // 1.使用MoreExecutors.listeningDecorator()方法将一个普通的线程池
        // 包装为一个包含通知功能的Future线程池。
        ListeningExecutorService executor = listeningDecorator(Executors.newFixedThreadPool(10));


        ListenableFuture<String> future1 = executor.submit(new RealData("guava1"));
        ListenableFuture<String> future2 = executor.submit(new RealData("guava2"));
        List<ListenableFuture<String>> futureList = new ArrayList<>();
        futureList.add(future1);
        futureList.add(future2);

        for(ListenableFuture future : futureList){
        // 2 这里相当于future.get(),但是这个方法不是阻塞的。
         Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("异步处理成功，数据：" + s);
            }

            @Override
            public void onFailure(Throwable e) {
                System.out.println("异步处理失败，数据：" + e);
            }
        });

        }
        executor.awaitTermination(1,TimeUnit.SECONDS);

        System.out.println("main task done ...");

        TimeUnit.SECONDS.sleep(20);

    }
}
