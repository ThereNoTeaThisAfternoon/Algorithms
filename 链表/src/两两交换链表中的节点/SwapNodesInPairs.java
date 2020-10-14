package 两两交换链表中的节点;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: SwapNodesInPairs.java
 * 类的详细说明
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.14 - 下午 4:36
 * @label LinkedList
 */
public class SwapNodesInPairs {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            ListNode result = new Solution().swapPairs(head);

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (result != null) {
                sb.append(result.val).append(", ");
                result = result.next;
            }
            System.out.println("返回的链表为：" + sb.substring(0, sb.length() - 2) + "]");
        }
    }
}

/**
 * Recursive
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}

/**
 * Iterator
 */
class SolutionCopy {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 创建一个虚拟节点，作为链表的头节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode tmp = dummyNode;
        // 如果有成对的节点出现，则进行交换
        while (tmp.next != null && tmp.next.next != null) {
            // 待交换的两个节点
            ListNode node1 = tmp.next;
            ListNode node2 = tmp.next.next;
            // 完成交换，临时变量tmp指向下一对的头节点
            tmp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            tmp = node1;
        }
        return dummyNode.next;
    }
}

// swap node value
class SolutionCopy2 {
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        int tmp;
        while (cur.next != null) {
            tmp = cur.val;
            cur.val = cur.next.val;
            cur.next.val = tmp;
            if (cur.next.next == null) {
                break;
            }
            cur = cur.next.next;
        }
        return head;
    }
}