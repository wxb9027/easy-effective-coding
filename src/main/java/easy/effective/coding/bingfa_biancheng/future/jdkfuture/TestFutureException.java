package easy.effective.coding.bingfa_biancheng.future.jdkfuture;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 测试future模式下的异常堆栈
 *
 * 1.call 方法本身抛异常。
 *     当执行submit时，call方法就被执行了，但是知道future.get()被调用才等获得异常；
 * 2.future.get的超时异常。
 *     没有特别的地方，get方法直接抛java.util.concurrent.TimeoutException
 */
public class TestFutureException {
    public static void main(String[] args) throws InterruptedException, IOException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> future = pool.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                System.out.println("step1");
                System.out.println("step2");
                System.out.println("step3");
                System.out.println("step4");
                TimeUnit.SECONDS.sleep(5);
                if ("aa".equals("aa")){
                    throw new IOException("出现异常啦，看看异常在哪？");
                }else {
                    return true;
                }

            }
        });
        System.out.println("任务提交啦");
        TimeUnit.SECONDS.sleep(1);
        try {
            System.out.println("等待call了");
            //future.get();//call会在提交后就执行，一定要执行到get才会拿到异常
            future.get(2,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
