package easy.effective.coding.java_basic.jvm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class TestString {

    static String base = "string";

    /**
     * 字符串常量+字符串常量
     * 编译器底层对这种操作进行了优化，直接优化成了str1，所以两个字符串常量地址相同
     */
    @Test
    public void test1() {
        String str1 = "1234";
        String str2 = "12" + "34";
        System.out.println(str1 == str2);
    }

    /**
     * why？
     */
    @Test
    public void test2() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1); //true

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2); //false
    }

    /**
     * 这种情况的底层实现稍微复杂一些
     * 此时str2的地址是在堆上任意位置重新new出来的一个内存，所以两个字符串地址不同，返回false
     *
     * 底层转化后等价于：
     * StringBuilder stringBuilder = new StringBuilder();
     * stringBuilder.append("34");
     * stringBuilder.append("12");
     * String str2 = stringBuilder.toString();
     */
    @Test
    public void test3() {
        String str1 = "1234";
        String str = "12";
        String str2 = str + "34";
        System.out.println(str1 == str2);
    }

    /**
     * String.intern()的作用:
     * JDK1.8中：先查找常量池中有没有"hello"，如果有，直接返回字符串常量中的地址，如果没有，先把"hello"放到常量池中，然后再去new对象，即在常量池中增加了一个"hello"
     * JDK1.7：不复制实例，只是在常量池中记录首次出现的实例引用，因此intern（）返回的引用和由StringBuilder创建的那个字符串实例是同一个
     * JDK1.6及以前：把首次遇到的字符串实例复制到永久代中，返回的也是永久代中这个字符串实例的引用
     */
    @Test
    public void test4() {
        //把"hello"放在了字符串常量池中
        String str1 = "hello";
        //在堆上申请了一块内存
//        String str2 = new String("hello");
        //先查找常量池中有没有"hello"，如果有，直接返回字符串常量中的地址，如果没有，先把"hello"放到常量池中，然后再去new对象，即在常量池中增加了一个"hello"
        String str2 = new String("hello").intern();
        System.out.println(str1 == str2);
    }

    /**
     * 实验：通过string的intern方法不停的向运行时常量池中存放数据，直到OOM，观察报错信息。
     * 结论：
     * jdk1.6报OOM PermGen space永久代溢出，说明运行时常量池放在永久代（方法区）
     * jdk1.7报OOM java heap space堆内存溢出，说明运行时常量池放在堆中
     * jdk1.8放在元空间里面，和堆相独立。
     * 所以string的intern方法在不同版本会有不同表现。
     */
    @Test
    public void test5() {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }


    /**
     * Hotspot JDK1.8内存溢出测试：此实验什么逻辑？？？
     * 运行时常量池、字符串常量池、静态变量分别存储在哪个位置？
     * <p>
     * 测试结果说明：
     * 字符串常量池存储在Heap区
     * 静态变量在heap中(实验未找到)
     * 运行时常量池在Meta Space
     */
    @Test
    public void test6() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
