package easy.effective.coding.data_structure;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列模拟实现栈
 */
public class QueueImplStack {

    @Test
    public void test() {
        QueueImplStack stack = new QueueImplStack();
        stack.push(1);
        System.out.println(stack.peek());
        stack.push(2);
        System.out.println(stack.peek());
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println("=============");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }

    // 定义队列
    private Queue<Integer> queue;

    public QueueImplStack() {
        queue = new LinkedList();
    }

    // 入栈--在队尾加入元素后,让其他元素按顺序出队再入队，保持新加入的元素永远在队头
    public void push(Integer e) {
        queue.offer(e);
        int size = queue.size();
        int i = 0;
        while (i < size - 1) {
            queue.offer(queue.poll());
            i++;
        }
    }

    // 出栈--将队尾前的其它所有元素出队再入队，直至队尾元素移到队头
    public Integer pop() {
        return queue.poll();
    }

    // 查看栈顶元素--即队头元素
    public Integer peek() {
        return queue.peek();
    }

    // 是否为空
    public boolean isEmpty() {
        return queue.isEmpty();
    }

}