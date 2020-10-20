package 重排链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: ReorderList.java
 * 类的详细说明
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.20 - 下午 8:05
 * @label LinkedList Recursive
 */
public class ReorderList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[1,2,3,4]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            new Solution().reorderList(head);
            String out = PublicMethod.listNodeToString(head);
            System.out.println(out);
        }
    }
}

/**
 * 寻找链表中点 + 链表右半逆序 + 合并链表
 */
class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 奇数个链表情况下，后半应该比前半少一个
        if (fast != null) {
            slow = slow.next;
        }

        // 反转后半链表
        ListNode dummy = null;
        ListNode next = slow;
        while (next != null) {
            ListNode node = next;
            next = next.next;
            node.next = dummy;
            dummy = node;
        }
        // head 与 dummy进行交替穿插
        ListNode cur1 = head;
        ListNode cur2 = dummy;

        while (cur2 != null) {
            ListNode node1 = cur1;
            ListNode node2 = cur2;
            cur1 = cur1.next;
            cur2 = cur2.next;
            node1.next = node2;
            node2.next = cur1;
        }
        cur1.next = null;
    }
}

/**
 * 利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可
 */
class SolutionCopy {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
}

/**
 * Recursive
 * 性能较差
 */
class SolutionCopy1 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode temp = head;
        while (temp.next.next != null) temp = temp.next;
        temp.next.next = head.next;
        head.next = temp.next;
        temp.next = null;
        reorderList(head.next.next);
    }
}