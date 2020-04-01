package easy.effective.coding.collection.basic_attr.fail_fast;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *fail-fast机制--场景二：当遍历集合时，如果对集合进行修改(增加删除)则会抛出抛出ConcurrentModificationException
 *
 * 这个异常是next()方法第一行代码checkForComodification抛出来的
 */
public class ArrayListFailFast {
    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        for (String str : list){   //由于remove操作后，循环没有执行到下一个next方法就退出了，所以没有抛异常
            if ("two".equals(str)){
                list.remove(str);
            }
            System.out.println(str);
        }
    }

    @Test
    public void test2(){  // 在遍历集合的时候，不要轻易删除元素
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        for (String str : list){
            if ("two".equals(str)){
                list.remove(str);
            }
            System.out.println(str);
        }
    }
    @Test
    public void test3(){  // 在遍历集合的时候，可以用iterator来删除元素，多线程要加synchronized
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            synchronized (this) {
                String item = iterator.next();
                if ("tow".equals(item)){
                    iterator.remove();
                }
            }
        }
    }

    /**
     * 使用COW
     * 缺点：
     * 1.每次写要新建集合，易产生GC，适合读多写少的场景，同时建议批量写；
     * 2.由于读和写是不同的集合，读不到最新的数据
     * 优点：
     * 1.读写不用加锁，性能高；
     * 2.fail－safe
     */
    @Test
    public void testCOW(){
        List<Integer> cow = new CopyOnWriteArrayList<>();
        long start = System.nanoTime();
        for (int i = 0;i < 1000_0;i++){
            cow.add(i);
        }
        long end = System.nanoTime();
        System.out.println(end - start);

    }
}
