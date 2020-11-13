package 奇偶链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: OddEvenLinkedList.java
 * 类的详细说明
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.13 - 下午 1:07
 * @label LinkedList
 */
public class OddEvenLinkedList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个单链表head：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            ListNode result = new Solution().oddEvenList(head);
            String output = PublicMethod.listNodeToString(result);
            System.out.println(output);
        }
    }
}

class Solution {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head.next;
        ListNode odd = head, even = dummy;
        ListNode next = head.next.next;
        while (next != null) {
            odd.next = next;
            odd = odd.next;
            next = next.next;
            if (next != null) {
                even.next = next;
                even = even.next;
                next = next.next;
            }
        }
        odd.next = dummy;
        even.next = null;
        return head;
    }
}

class SolutionCopy {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
