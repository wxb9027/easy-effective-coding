package easy.effective.coding.java_basic.TypeTest;

import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 强引用：
 * String str = “abc”;
 * list.add(str);
 * 软引用：
 * 如果弱引用对象回收完之后，内存还是报警，继续回收软引用对象
 * 弱引用：
 * 如果虚引用对象回收完之后，内存还是报警，继续回收弱引用对象
 * 虚引用：
 * 虚拟机的内存不够使用，开始报警，这时候垃圾回收机制开始执行System.gc(); String s = “abc”;如果没有对象回收了， 就回收没虚引用的对象
 */
public class TestReference {

    @Test
    public void test() throws InterruptedException {
        //创建一个100m的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        SoftReference<byte[]> softCacheData = new SoftReference<>(cacheData);
        cacheData = null;
        System.out.println("GC回收之前cacheData：" + cacheData);
        System.out.println("GC回收之前softCacheData：" + softCacheData.get());

        //GC回收
        System.gc();
        Thread.sleep(1000);

        System.out.println("GC回收之后cacheData：" + cacheData);
        System.out.println("GC回收之后softCacheData：" + softCacheData.get());

        //再创建一个150m的数据
        byte[] newCacheData = new byte[150 * 1024 * 1024];

        System.out.println("创建150M对象之后cacheData：" + cacheData);
        System.out.println("创建150M对象之后softCacheData：" + softCacheData);
    }

    @Test
    public void test2() {
        Car car = new Car(22000, "silver");
        WeakReference<Car> weakCar = new WeakReference<Car>(car);

        int i = 0;

        while (true) {
            Car c = weakCar.get();
            if (c != null) {
                i++;
                //这一行为弱引用
                System.out.println("car=====" + c);
                //这一行为强引用
//        System.out.println("car====="+car);
                System.out.println("Object is alive for " + i + " loops - " + c);
            } else {
                System.out.println("Object has been collected.");
                break;
            }
        }
    }
}