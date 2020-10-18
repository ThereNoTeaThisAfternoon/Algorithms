package 删除链表的倒数第N个节点;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: RemoveNthNodeFromEndOfList.java
 * 类的详细说明
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.18 - 下午 8:56
 * @label LinkedList TwoPointers Recursive
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            System.out.println("请输入一个有效的待删除链表的倒数第 n 个节点：2");
            ListNode result = new Solution().removeNthFromEnd(head, Integer.parseInt(in.readLine()));
            String s = PublicMethod.listNodeToString(result);
            System.out.println("返回的结果为：" + s);
        }
    }
}

/**
 * 待删除的节点有三种情况：头节点，尾节点，中间节点
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        // 创建一个虚拟节点，充当链表头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode firstGo = dummy;
        ListNode secondGo = dummy;

        while (n-- > 0) {
            firstGo = firstGo.next;
        }
        while (firstGo.next != null) {
            secondGo = secondGo.next;
            firstGo = firstGo.next;
        }
        secondGo.next = secondGo.next.next;// 删除目标节点
        return dummy.next;
    }
}

/**
 * Recursive
 */
class SolutionCopy {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNode(head, n) == n ? head.next : head;
    }

    private int removeNode(ListNode head, int n) {
        if (head.next == null) {
            return 1;
        }
        int target = removeNode(head.next, n);
        if (target == n) {
            if (n == 1) {
                head.next = null;
            } else {
                head.next = head.next.next;
            }
        }
        return target + 1;
    }
}