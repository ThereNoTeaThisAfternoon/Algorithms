package 排序链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: SortList.java
 * 类的详细说明
 * 给链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 <= Node.val <= 10^5
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.21 - 下午 1:59
 * @label LinkedList Sort
 */
public class SortList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型链表head：[4,2,1,1]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            ListNode result = new Solution().sortList(head);
            String output = PublicMethod.listNodeToString(result);
            System.out.println(output);
        }
    }
}

/**
 * Merge Recursive
 */
class Solution {

    public ListNode sortList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        // 找到当前链表的中间节点并断开链表
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;
        // 递归
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 合并有序链表
        return mergeTwoLists(left, right);
    }

    // 找到链表中间节点（876. 链表的中间结点）
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left != null ? left : right;
        return dummy.next;
    }
}
