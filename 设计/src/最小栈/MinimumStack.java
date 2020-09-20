package 最小栈;
/**
 * FileName: MinimumStack.java
 * 类的详细说明
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素[getMin()]的栈。
 *
 * @label Design Stack
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.12
 * @version 1.00
 */

import Design公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumStack {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入命令集：[\"MinStack\",\"push\",\"push\",\"push\",\"getMin\",\"pop\",\"top\",\"getMin\"]");
        while ((line = in.readLine()) != null) {
            String[] commandLine = new PublicMethod().stringToStringArray(line);
            MinStackCopy order = new MinStackCopy();
            List<Integer> ret = new ArrayList<>();
            for (int i = 1; i < commandLine.length; i++) {
                switch (commandLine[i]) {
                    case "push":
                        int x = new Random().nextInt(10);
                        order.push(x);
                        ret.add(x);
                        break;
                    case "pop":
                        ret.add(order.pop());
                        break;
                    case "top":
                        ret.add(order.top());
                        break;
                    case "getMin":
                        ret.add(order.getMin());
                }
            }
            System.out.println(ret.toString());
        }
    }
}

class MinStack {
    //一、双栈,定义一个数据栈用于存放原来入栈顺序的值，铺助栈栈顶为当前的最小值 stack1 , stack2
    //二、栈元素中除了保存当前值外，额外保存当前最小值Deque<Node>  node.currentValue node.curMinValue
    //三、每次入栈2个元素，一个是入栈的元素本身，一个是当前栈元素的最小值
    private static Deque<Integer> stack;

    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            stack.push(x);
        } else {
            int last = stack.getLast();
            stack.push(x);
            stack.push(Math.min(x, last));
        }
    }

    int pop() {
        stack.pop();
        return stack.pop();
    }

    int top() {
        int temp = stack.pop();
        int top = stack.peek();
        stack.push(temp);
        return top;
    }

    int getMin() {
        return stack.getLast();
    }
}

class MinStackCopy {
    //以单链表形式定义栈，链表头插法
    private Node head;

    MinStackCopy() {

    }

    void push(int x) {
        if (head == null)
            head = new Node(x, x);
        else
            head = new Node(x, Math.min(x, head.min), head);//头插法定义栈
    }

    int pop() {
        int temp = head.val;
        head = head.next;
        return temp;
    }

    int top() {
        return head.val;
    }

    int getMin() {
        return head.min;
    }

    private static class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min) {
            this(val, min, null);
        }

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}




