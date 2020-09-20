package 两数相加;
/**
 * FileName: AddTwoNumbers.java
 * 类的详细说明
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 这两个数不会以零开头。[2,4,3] [5,6,4]
 *
 * @label LinkedList Math
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.15
 * @version 1.00
 */

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddTwoNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = PublicMethod.stringToListNode(line);
            line = in.readLine();
            ListNode l2 = PublicMethod.stringToListNode(line);

            ListNode ret = new Solution().addTwoNumbers(l1, l2);
            String out = PublicMethod.listNodeToString(ret);
            System.out.print(out);
        }
    }
}

class Solution {
    /**
     * MethodName: addTwoNumbers
     *
     * @param l1 ListNode
     * @param l2 ListNode
     * @return 用链表逆序输出的整数值
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), cur = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;
            sum += l1 == null ? 0 : l1.val;
            sum += l2 == null ? 0 : l2.val;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }
}

class SolutionCopy {
    /*维护一个进位变量temp，效率和上面一样，没什么用*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), cur = head;
        int temp = 0;
        while (l1 != null | l2 != null || temp > 0) {
            if (l1 != null) {
                temp += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                temp += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(temp % 10);
            cur = cur.next;
            temp /= 10;
        }
        return head.next;
    }
}