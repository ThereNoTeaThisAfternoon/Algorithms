package 分隔链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: PartitionList.java
 * 类的详细说明
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.3 - 下午 5:31
 * @label LinkedList TwoPointers
 */
public class PartitionList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型链表head：[1,4,3,2,5,2]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            System.out.println("请输入一个特定值x：3");
            int x = Integer.parseInt(in.readLine());
            ListNode result = new SolutionCopy().partition(head, x);
            String out = PublicMethod.listNodeToString(result);
            System.out.println(out);
        }
    }
}

/**
 * 使用原链表
 */
class Solution {

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        // 为该链表添加一个头节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        ListNode dummy = new ListNode(-1); // 使用dummy链表来保存大于等于x的节点
        ListNode dummyNext = dummy;
        // 分隔节点
        while (cur.next != null) {
            // 当前节点的下一节点值小于x，cur直接走到下一位
            if (cur.next.val < x) {
                cur = cur.next;
            } else {
                ListNode node = cur.next;
                cur.next = node.next;
                node.next = null;
                dummyNext.next = node;
                dummyNext = dummyNext.next;
            }
        }
        // 合并两个链表
        cur.next = dummy.next;
        return dummyHead.next;
    }
}

/**
 * 不使用原链表
 */
class SolutionCopy {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
