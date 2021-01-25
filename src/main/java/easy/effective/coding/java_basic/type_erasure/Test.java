package easy.effective.coding.java_basic.type_erasure;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 泛型是Java 1.5的新特性，泛型的本质是参数化类型。编译时检查类型安全。所有的强制转换都是自动和隐式的。
 * Java的泛型是伪泛型，只在编译时强化它的类型信息，而在运行时丢弃(或者擦除)它的元素类型信息。
 * Java的泛型基本上都是在编译器这个层次上实现的，在生成的字节码中是不包含泛型中的类型信息的，使用泛型的时候加上类型参数，在编译器编译的时候会去掉，这个过程称为类型擦除。
 * Array不
 *  支持泛型，所以建议使用List代替Array，因为List可以提供编译期的类型安全保证。
 */
public class Test {

    public static void main(String[] args) {

        ConcurrentHashMap map = new ConcurrentHashMap();

        //泛型信息都会被擦掉
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abc");

        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(123);

        System.out.println(list1.getClass() == list2.getClass());


        //通过反射可以避开泛型限制，添加不同类型的元素
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);

        try {
            list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


        //不指定泛型的时候
        int i = Test.add(1, 2); //这两个参数都是Integer，所以T为Integer类型
        Number f = Test.add(1, 1.2); //这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Number
        Object o = Test.add(1, "asd"); //这两个参数一个是Integer，以风格是Float，所以取同一父类的最小级，为Object

        //指定泛型的时候
        int a = Test.<Integer>add(1, 2); //指定了Integer，所以只能为Integer类型或者其子类
//        int b = Test.<Integer>add(1, 2.2); //编译错误，指定了Integer，不能为Float
        Number c = Test.<Number>add(1, 2.2); //指定为Number，所以可以为Integer和Float

    }


    //这是一个简单的泛型方法
    public static <T> T add(T x, T y) {
        return y;
    }
}