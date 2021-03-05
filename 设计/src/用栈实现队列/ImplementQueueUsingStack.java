package 用栈实现队列;

import Design公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * FileName: ImplementQueueUsingStack.java
 * 类的详细说明
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * |--void push(int x) 将元素 x 推到队列的末尾
 * |--int pop() 从队列的开头移除并返回元素
 * |--int peek() 返回队列开头的元素
 * |--boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.5 - 下午 8:33
 * @label Design
 */
public class ImplementQueueUsingStack {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串数组表示一些方法functions：[\"MyQueue\",\"push\",\"push\",\"peek\",\"pop\",\"empty\"]");
        while ((line = in.readLine()) != null) {
            String[] functions = PublicMethod.stringToStringArray(line);
            System.out.println("请输入一些操作options：[[],[1],[2],[],[],[]]");
            int[][] options = PublicMethod.stringTo2DIntegerArray(in.readLine());
            MyQueue myQueue = null;
            String[] results = new String[functions.length];
            for (int i = 0; i < functions.length; ++i) {
                switch (functions[i]) {
                    case "MyQueue":
                        myQueue = new MyQueue();
                        results[i] = "null";
                        break;
                    case "push":
                        assert myQueue != null;
                        myQueue.push(options[i][0]);
                        results[i] = "null";
                        break;
                    case "peek":
                        assert myQueue != null;
                        results[i] = String.valueOf(myQueue.peek());
                        break;
                    case "pop":
                        assert myQueue != null;
                        results[i] = String.valueOf(myQueue.pop());
                        break;
                }
            }
            System.out.println(Arrays.toString(results));
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
class MyQueue {

    Deque<Integer> stack1; // 输入栈
    Deque<Integer> stack2; // 输出栈

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (stack2.isEmpty()) {
            in2out();
        }
        return stack2.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (stack2.isEmpty()) {
            in2out();
        }
        return stack2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    private void in2out() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
}
