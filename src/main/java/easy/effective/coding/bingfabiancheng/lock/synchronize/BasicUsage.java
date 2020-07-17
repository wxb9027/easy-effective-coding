package easy.effective.coding.bingfabiancheng.lock.synchronize;

/**
 * sychronized是java内置锁，使用时有如下缺点：
 * 1. 线程取锁时，可能无限等待，不能设置超时
 *
 * 2. 使用sychronized的代码块不能中断；
 *  说明：
 *      lock是可中断锁，而synchronized 不是可中断锁
 *
 *      线程A和B都要获取对象O的锁定，假设A获取了对象O锁，B将等待A释放对O的锁定，
 *
 *      如果使用 synchronized ，如果A不释放，B将一直等下去，不能被中断
 *
 *      如果 使用ReentrantLock，如果A不释放，可以使B在等待了足够长的时间以后，中断等待，而干别的事情
 *
 * 3. 无法获取等待锁的信息，比如等待锁的数量、等待的是哪些线程、各自等待了多长时间
 */
public class BasicUsage {
    // 1.作用在方法上，锁的对象是调用该方法的实例
    public synchronized void addTask_1(){}
    public void addTask_2(){
        synchronized (this){

        }
    }

    // 2.作用在静态方法，锁的对象是当前类
    public static synchronized void addTask_3(){}
    public static void addTask_4(){
        synchronized (BasicUsage.class){

        }
    }

}
