package 反转链表II;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: ReverseLinkedListII.java
 * 类的详细说明
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * <p>
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.18 - 下午 8:54
 * @label LinkedList
 */
public class ReverseLinkedListII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            ListNode result = new Solution().reverseBetween(head, 2, 4);
            String out = PublicMethod.listNodeToString(result);
            System.out.println(out);
        }
    }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int len = right - left + 1;
        if (len < 2) {
            return head;
        }
        ListNode[] help = new ListNode[len];
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 遍历至left的前一个节点，存储前驱节点
        int index = 1;
        ListNode cur = dummy;
        while (index++ < left) {
            cur = cur.next;
        }
        ListNode l = cur;
        // 把链表存入数组，存储后驱节点
        for (int i = 0; i < len; ++i) {
            cur = cur.next;
            help[i] = cur;
        }
        ListNode r = cur.next;
        // 反转添加链表，然后补全链表
        for (int i = 0; i < len; ++i) {
            l.next = help[len - i - 1];
            l = l.next;
        }
        l.next = r;
        return dummy.next;
    }
}

/**
 * 插入排序
 */
class SolutionCopy {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        head = pre.next;
        for (int i = left; i < right; i++) {
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return dummy.next;
    }
}

/**
 * 反转中间一部分链表，然后再合并
 */
class SolutionCopy2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode superior = dummyHead;
        // 1. 遍历至left的前一个节点
        for (int i = 1; i < left; i++) {
            superior = superior.next;
        }
        ListNode prev = null;
        ListNode cur = superior.next;
        // 2. 180°旋转
        for (int i = 0; i <= right - left; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // 3. 修改left和right+left位置处的节点的指向
        superior.next.next = cur;
        superior.next = prev;
        return dummyHead.next;
    }
}