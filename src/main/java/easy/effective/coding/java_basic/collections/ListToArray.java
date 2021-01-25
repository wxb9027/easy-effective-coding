package easy.effective.coding.java_basic.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *使用集合的toArray(T[] array)方法，转换为数组时，要注意需要传入类型完全一样的数组，并且容量大小为list.size
 */
public class ListToArray {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(3);
        list.add("one");
        list.add("two");
        list.add("three");

        //泛型丢失，无法使用String[]接收无参方法返回的结果
        Object[] array1 = list.toArray();
        System.out.println(Arrays.asList(array1));

        //数组长度小于元素个数
        String[] array2 = new String[2];
        list.toArray(array2);
        System.out.println(Arrays.asList(array2));

        //数组长度等于元素个数
        String[] array3 = new String[3];
        list.toArray(array3);
        System.out.println(Arrays.asList(array3));

    }
}
