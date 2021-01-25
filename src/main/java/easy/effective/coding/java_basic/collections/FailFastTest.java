package easy.effective.coding.java_basic.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * fail-fast机制：快速失败机制，java集合框架中的错误检测机制。当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。
 * 在操作迭代器的时候，只要是涉及了改变ArrayList元素的个数的方法都会导致modCount的改变，expectedModCount与modCount的改变不同步，导致两者之间不等，从而产生fail-fast机制
 * 如何避免？
 *  * 1、在遍历过程中所有涉及到改变modCount值的地方全部加上synchronized或者直接使用Collections.synchronizedList（不推荐）
 *  * 2、使用CopyOnWriteArrayList来替换ArrayList。
 *
 *  COW时fail-safe机制的，是在安全的副本上进行便利，集合修改与副本的遍历是没有任何关系的，缺点是读取不到最新的数据。
 *  这也是CAP理论中，C（Consistency）与A（Availability）的矛盾，即一致性与可用性的矛盾。
 *
 * * *为什么以下代码没有出现fail-fast？？？
 */
public class FailFastTest {

    private static List<String> list = new ArrayList<String>();
    //private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {

        // 同时启动两个线程对list进行操作！
        new ThreadOne().start();
        new ThreadTwo().start();
    }

    private static void printAll() {
        System.out.println("");

        String value = null;
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
    }

    /**
     * 向list中依次添加0,1,2,3,4,5，每添加一个数之后，就通过printAll()遍历整个list
     */
    private static class ThreadOne extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i<6) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }

    /**
     * 向list中依次添加10,11,12,13,14,15，每添加一个数之后，就通过printAll()遍历整个list
     */
    private static class ThreadTwo extends Thread {
        @Override
        public void run() {
            int i = 10;
            while (i<16) {
                list.add(String.valueOf(i));
                printAll();
                i++;
            }
        }
    }
}
