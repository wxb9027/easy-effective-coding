package easy.effective.coding.java_basic.collections;

import java.util.Arrays;
import java.util.List;

/**
 * 使用Arrays.asList()把数组转换集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛UnsupportedOperationException异常。
 * 因为Arrays.asList()方法返回的ArrayList是Arrays类的一个内部类，只提供了个别方法的实现。
 */
public class ArraysAsList {

    public static void main(String[] args) {
        String[] stringArray = new String[3];
        stringArray[0] = "one";
        stringArray[1] = "two";
        stringArray[2] = "three";


        List<String> stringList = Arrays.asList(stringArray);
        //在使用数组转集合时，需要使用李逵java.util.ArrayList直接创建一个新集合。
//        List<String> stringList = new java.util.ArrayList<String>(Arrays.asList(stringArray));

        stringList.set(0, "oneList");
        System.out.println(stringArray[0]);

        stringList.add("four");
        stringList.remove(2);
        stringList.clear();
        System.out.println(stringArray);
        System.out.println(stringList);


    }
}
