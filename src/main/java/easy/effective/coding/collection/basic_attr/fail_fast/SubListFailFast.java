package easy.effective.coding.collection.basic_attr.fail_fast;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证fail-fast机制----主列的  元素变个数 的变动会引起子列抛异常
 * masterList的任何修改都会导致sublist的 增删改查 抛出ConcurrentModificationException。
 *
 * 1.Sublish是ArrayList的内部类，不能被序列化，不能网络传输；
 * 2.Sublist的修改会引起masterList的修改；
 * 3.主列的  元素变个数 的变动会引起子列抛异常。
 */
public class SubListFailFast {
    public static void main(String[] args) {
    List<String> masterList = new ArrayList<>();
    masterList.add("one");
    masterList.add("two");
    masterList.add("three");
    masterList.add("four");
    masterList.add("five");

    List<String> branchList = masterList.subList(0,3);

    masterList.remove(0);
    masterList.add("ten");
    masterList.clear();


    //branchList.add("Six");
        for (String s: branchList) {
            System.out.println(s);
        }




    }
}
