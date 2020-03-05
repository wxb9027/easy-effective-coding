package easy.effective.coding.bingfa_biancheng.thread_basic;

/**
 * 线程组的使用，为线程起名，便于管理
 */
public class ThreadGroupName {
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("我是线程组名");
        Thread t1 = new Thread(tg,new Task(),"我是线程名字");
        t1.start();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("running_thread_grouo:" + Thread.currentThread().getThreadGroup().getName());
        }
    }
}
