package easy.effective.coding.java_basic.TestLambda;

import org.junit.jupiter.api.Test;

/**
 * Lambda表达式引入了”->“箭头操作符
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 *
 * 语法格式一：无参，无返回值
 *  ()-> System.out.println("Hello");
 *
 *
 *
 *
 */
public class TestLambda2 {

    @Test
    public void test(){

        int num = 0;//jdk1.7前必须是final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello" + num);
            }
        };

        r.run();

        System.out.println("----------------");

        Runnable r1 = () -> System.out.println("hello" + num);

        r1.run();
    }



}
