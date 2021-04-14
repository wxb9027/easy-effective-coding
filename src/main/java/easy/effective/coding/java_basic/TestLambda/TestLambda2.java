package easy.effective.coding.java_basic.TestLambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Lambda表达式引入了”->“箭头操作符
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 * <p>
 * 语法格式一：无参，无返回值
 * ()-> System.out.println("Hello");
 */
public class TestLambda2 {

    @Test
    public void test() {

        //jdk1.7前必须是final
        //jdk1.8后可以不显示指定为final,但实际也是final的,在匿名内部类或lambda表达式种应用后,再修改此变量就会编译报错
        int num = 0;
        int repli = num;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello" + repli);
            }
        };
        r.run();
        System.out.println("----------------");

        num++;
        int repli2 = num;
        Runnable r1 = () -> System.out.println("hello" + repli2);
        r1.run();
    }

    /**
     * Variable used in lambda expression should be final or effectively final
     */
    @Test
    public void test2() {
        String str = "abcf";
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.forEach(x -> {
//            str = str + "1";
//            System.out.println(x + str);
            System.out.println(x + "1");
        });
    }


}
