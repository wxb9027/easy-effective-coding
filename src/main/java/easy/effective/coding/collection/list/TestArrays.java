package easy.effective.coding.collection.list;

import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试Arrays工具类的使用
 *
 * Arrays.asList()体现的是适配器模式，后台的数据仍然是原数组，
 * 而且返回的ArrayList是Arrrays中的内部类，并不是平时所用的ArrayList
 *
 *
 */
public class TestArrays {
    //测试数组转集合，方式一：视图方式，返回的集合底层还是原数组
    @Test
    public void testAsListView(){
        String[] strings = new String[3];
        strings[0] = "one";
        strings[1] = "two";
        strings[2] = "three";
        List<String> stringList = Arrays.asList(strings);
        //可以改变值
        stringList.set(0,"four");
        System.out.println(strings[0]);
        // 不能改变数组的数量,抛出UnsupportedOperationException
        stringList.add("five");
        stringList.remove(1);
        stringList.clear();
    }

    //测试数组转集合，方式二:创建新的集合，使用视图方式的基础上，创建新的集合
    @Test
    public void testAsListCreate(){
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";
        List<String> stringList = Arrays.asList(stringArray);
        // 创建一个新的数组
        List<String> objectList = new ArrayList<>(stringList);
        objectList.add("five");
        objectList.remove(1);

        objectList.clear();
        objectList.isEmpty();
    }

    //测试集合转数组，集合转数据更加可控
    @Test
    public void testListToArray(){
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        //方式一：泛型丢失，无法使用String[]来接收
        Object[] strings = list.toArray();
        //方式二：数组容量不够
        String[] arrs1 = new String[2];
        list.toArray(arrs1);
        System.out.println(Arrays.asList(arrs1));
        //方式三：数组容量刚好足够
        String[] arrs2 = new String[3];
        list.toArray(arrs2);
        System.out.println(Arrays.asList(arrs2));

    }
}
