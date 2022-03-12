package easy.effective.coding.java_basic.jvm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Java中的所有对象引用均按值传递.
 * 1)当传的是基本类型时，传的是值的拷贝，对拷贝变量的修改不影响原变量；
 * 2)当传的是引用类型时，传的是引用地址的拷贝，但是拷贝的地址和真实地址指向的都是同一个真实数据，因此可以修改原变量中的值；
 * 注：当传递不可变的对象引用（如String、Integer，Double，Float，Long，Boolean，BigDecimal）时，虽然拷贝的也是引用地址，指向的是同一个数据，但是String的值不能被修改，因此无法修改原变量中的值。
 */
public class MethodCallTest {

    public void method(int value) {
        value = 9;
    }

    public void method2(String value) {
        value = "xx";
    }

    public void method3(List value) {
        value.add("bb");
    }

    public void method4(List value) {
        List v = new ArrayList();
        v.add("mm");
        value = v;
    }

    public void method5(Integer value) {
        value = value + 100;
    }

    public void method6(StringBuffer value) {
        value.append("xx");
    }


    @Test
    public void test() {
        int v = 1;
        method(v);
        System.out.println(v);

        Integer v2 = new Integer(1);
        method5(v2);
        System.out.println(v2);

        String s = "aa";
        method2(s);
        System.out.println(s);

        StringBuffer s2 = new StringBuffer("aa");
        method6(s2);
        System.out.println(s2);

        List list = new ArrayList();
        list.add("aa");
        method3(list);
        System.out.println(list);

        List list2 = new ArrayList();
        list2.add("aa");
        method4(list2);
        System.out.println(list2);
    }

}
