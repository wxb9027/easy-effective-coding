package easy.effective.coding.data_structure;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * 用栈模拟实现队列
 */
public class StackImplQueue {

    @Test
    public void test1() {
        StackImplQueue1 queue = new StackImplQueue1();
        queue.enqueue(1);
        System.out.println(queue.peek());
        queue.enqueue(2);
        System.out.println(queue.peek());
        queue.enqueue(3);
        System.out.println(queue.peek());
        System.out.println("=============");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
    @Test
    public void test2() {
        StackImplQueue2 queue = new StackImplQueue2();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }


    /**
     * 方式一：利用辅助栈，保证新加入的元素永远沉在数据栈底（标准答案）
     */
    class StackImplQueue1 {
        // 数据栈
        private Stack<Integer> stack;
        // 辅助栈
        private Stack<Integer> aux;

        StackImplQueue1() {
            stack = new Stack<>();
            aux = new Stack<>();
        }

        // 入队--通过数据栈与辅助栈相互交换，保证新加入的元素沉在数据栈底
        public void enqueue(Integer e) {
            while (!stack.isEmpty()) {
                aux.push(stack.pop());
            }
            stack.push(e);
            while (!aux.isEmpty()) {
                stack.push(aux.pop());
            }

        }

        // 出队--弹出数据栈元素
        public Integer dequeue() {
            return stack.pop();
        }

        // 查看队头元素
        public Integer peek() {
            return stack.peek();
        }

        // 是否为空
        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    /**
     * 方式二：入队和出队使用不同的栈（非标答案 by fxm）
     * 注：无法实现栈的peek操作
     */
    class StackImplQueue2 {
        // 数据栈
        private Stack<Integer> inStack;
        // 辅助栈
        private Stack<Integer> outStack;

        StackImplQueue2() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        //入队
        public void enqueue(Integer e) {
            inStack.push(e);
        }

        //出队
        public Integer dequeue() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

        // 查看队头元素
       /* public Integer peek() {
            if (!outStack.isEmpty()) {
                outStack.peek();
            }
        }*/

        // 是否为空
        public boolean isEmpty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }
    }
}

