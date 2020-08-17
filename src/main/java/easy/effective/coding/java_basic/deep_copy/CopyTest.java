package easy.effective.coding.java_basic.deep_copy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 浅拷贝：创建一个新对象，然后将当前对象的非静态字段复制到该新对象，如果字段是值类型的，那么对该字段执行复制；如果该字段是引用类型的话，则复制引用但不复制引用的对象。因此，原始对象及其副本引用同一个对象。
 * 深拷贝：创建一个新对象，然后将当前对象的非静态字段复制到该新对象，无论该字段是值类型的还是引用类型，都复制独立的一份。当你修改其中一个对象的任何内容时，都不会影响另一个对象的内容。
 *
 * 注意：Map.putAll()不能实现深拷贝，仅对基本数据类型实现起到深拷贝的作用！
 */
public class CopyTest {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(100);
        list.add(200);

        HashMap<String,Object> map = new HashMap<String,Object>();
        //放基本类型数据
        map.put("basic", 100);
        //放对象
        map.put("list", list);

        HashMap<String,Object> mapNew = new HashMap<String,Object>();
//        mapNew = map;
        mapNew.putAll(map);
//        mapNew = (HashMap)map.clone();
//        map.forEach((k,v) -> mapNew.put(k,v));

        System.out.println("----数据展示-----");
        System.out.println(map);
        System.out.println(mapNew);

        System.out.println("----更改基本类型数据-----");
        map.put("basic", 200);
        System.out.println(map);
        System.out.println(mapNew);

        System.out.println("----更改引用类型数据-----");
        list.add(300);
        System.out.println(map);
        System.out.println(mapNew);

        System.out.println("----使用序列化进行深拷贝-----");
        mapNew = CloneUtils.clone(map);
        list.add(400);
        System.out.println(map);
        System.out.println(mapNew);
    }
}
