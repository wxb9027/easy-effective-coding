package easy.effective.coding.java_basic.juc;

import org.junit.jupiter.api.Test;

public class TestThreadLocal {

    //普通变量
    private String str = "hi";

    public static ThreadLocal<String> threadLocalString = new ThreadLocal<String>() {
        protected String initialValue() {
            return "";
        }
    };
    public static ThreadLocal<Long> threadLocalLong = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return 200L;
        }
    };
    public static ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 200;
        }
    };

    @Test
    public void test() throws InterruptedException {
        threadLocalString.set("test");
        threadLocalLong.set(100L);

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalString.set("thread");
                str = "fxm";
                //这一行打印的是初始值,而不是当前方法最新set的值.因为新起的线程与主线程是隔离的.
                System.out.println(Thread.currentThread().getName()+":"+threadLocalString.get());
                System.out.println(Thread.currentThread().getName()+":"+threadLocalLong.get());
                System.out.println(Thread.currentThread().getName()+":"+str);
            }
        }).start();

        Thread. sleep(2000);
        System.out.println(Thread.currentThread().getName()+":"+threadLocalString.get());
        System.out.println(Thread.currentThread().getName()+":"+threadLocalLong.get());
        System.out.println(Thread.currentThread().getName()+":"+str);

    }
}
