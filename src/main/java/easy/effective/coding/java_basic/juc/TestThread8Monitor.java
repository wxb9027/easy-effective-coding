package easy.effective.coding.java_basic.juc;

/**
 * 题目：判断打印的“one” or “two” ？
 * 1.两个普通同步方法，两个线程标准打印 ？ //one two
 * 2.新增Thread.sleep()给getOne() ? //one two
 * 3.新增普通方法getThree(),打印？ // three one two
 * 4.两个普通同步方法，两个number对象，打印？ //two one
 * 5.getOne()为静态同步方法，getTwo为普通同步方法，一个number对象，打印？ //two one
 * 6.两个方法均为静态同步方法，一个number对象，打印？ //one two
 * 7.getOne()为静态同步方法，getTwo为普通同步方法，两个number对象，打印？ //two one
 * 8.两个方法均为静态同步方法，两个number对象，打印？ //one two
 *
 * 线程8锁的关键之处：
 * 1）非静态方法的锁默认为 this；静态方法的锁为 对应的class实例
 * 2）某一时刻内，只有一个线程持有锁，无论几个方法
 *
 */
public class TestThread8Monitor {

    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                number.getTwo();
                number2.getTwo();
            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                number.getThree();
            }
        }).start();*/
    }
}

class Number{
    public static synchronized void getOne(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        System.out.println("one");
    }

    public static synchronized void getTwo(){
        System.out.println("two");
    }

    public void getThree(){
        System.out.println("three");
    }
}
