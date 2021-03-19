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
            return 0L;
        }

    };

    @Test
    public void test() throws InterruptedException {
        threadLocalLong.set(100L);
        threadLocalString.set("test");

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalString.set("thread");
                str = "fxm";
                System.out.println(Thread.currentThread().getName()+":"+threadLocalLong.get());
                System.out.println(Thread.currentThread().getName()+":"+threadLocalString.get());
                System.out.println(Thread.currentThread().getName()+":"+str);
            }
        }).start();

        Thread. sleep(2000);
        System.out.println(Thread.currentThread().getName()+":"+threadLocalLong.get());
        System.out.println(Thread.currentThread().getName()+":"+threadLocalString.get());
        System.out.println(Thread.currentThread().getName()+":"+str);
    }
}
