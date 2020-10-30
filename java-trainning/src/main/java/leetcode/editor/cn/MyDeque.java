package leetcode.editor.cn;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 2020/9/26 19:32
 * @Version 1.0
 **/
public class MyDeque {
    @Test
    public void oldApi(){
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        String str = deque.getFirst();
        System.out.println(str);

        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }

}
