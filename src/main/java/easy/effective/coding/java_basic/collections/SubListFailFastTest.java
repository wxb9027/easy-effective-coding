package easy.effective.coding.java_basic.collections;

import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList.subList()获取子列表时，如果主列表集合元素个数增加或删除，会导致子列表的遍历，增加，删除，进而产生fail-fast异常。
 */
public class SubListFailFastTest {

    public static void main(String[] args) {
        List masterList = new ArrayList();

        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List branchList = masterList.subList(0, 3);

        //以下3行，如不注释掉，会导致branchList操作时出现异常
        /*masterList.remove(0);
        masterList.add("ten");
        masterList.clear();*/

        printAll(masterList);
        printAll(branchList);

        //下面4行全部能正确执行
        branchList.clear();
        printAll(masterList);
        printAll(branchList);

        branchList.add("six");
        branchList.add("seven");

        printAll(masterList);
        printAll(branchList);

        branchList.remove(0);

        //子列表修改导致主列表也被动改动
        printAll(masterList);
        printAll(branchList);

    }

    private static void printAll(List list) {
        System.out.println("");

        String value = null;
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
    }
}
