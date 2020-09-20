package 两数相加Ⅱ;
/**
 * FileName: AddTowNumbers.java
 * 类的详细说明
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 这两个数字都不会以零开头。[1,2,3,4] [2,3,4,5]
 *
 * @label LinkedList
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.14
 * @version 1.00
 */

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class AddTwoNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = PublicMethod.stringToListNode(line);
            line = in.readLine();
            ListNode l2 = PublicMethod.stringToListNode(line);

            ListNode result = new Solution().addTwoNumbers(l1, l2);
            String out = PublicMethod.listNodeToString(result);
            System.out.println("最终结果为：");
            System.out.println(out);
        }
    }
}

class Solution {
    /**
     * MethodName: addTwoNumbers
     *
     * @param l1 listNode
     * @param l2 listNode
     * @return listNode 求得的和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }
}
