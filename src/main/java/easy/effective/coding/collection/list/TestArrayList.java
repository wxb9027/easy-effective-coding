package easy.effective.coding.collection.list;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList extends MyArray{
    // 1. 表示构造方法
    // 2. this表示当前实例，super代表父类；
   public TestArrayList(){
       this("调用我的另一个构造方法");

   }
   public TestArrayList(String para){
       super("haha");
       System.out.println("我是TestArrayListLIst：" + para);
   }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
    }
}
