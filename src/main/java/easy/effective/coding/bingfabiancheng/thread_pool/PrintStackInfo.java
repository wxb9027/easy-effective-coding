package easy.effective.coding.bingfabiancheng.thread_pool;

import java.util.concurrent.*;

/**
 *
 */
public class PrintStackInfo {
    //不打印堆栈信息
    static class NoStackInfo implements Runnable{
        int a,b;
        NoStackInfo(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void run() {
            try{
                double c = a/b;
                System.out.println("c= " + c);
            }catch (Exception e){
               new Exception("client trace.").printStackTrace();
               throw e;
            }

        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i=0;i<5;i++){
            //当i=0时，没有看到任何异常
            pools.submit(new NoStackInfo(100,i));
        }

        for (int i=0;i<5;i++){
            // 只看到异常是从哪里抛出来，看不到哪里提交的任务
            pools.execute(new NoStackInfo(100,i));
        }
        for (int i=0;i<5;i++){
            // 看到部分堆栈信息
            Future re = pools.submit(new NoStackInfo(100,i));
            re.get();
        }

    }
}
