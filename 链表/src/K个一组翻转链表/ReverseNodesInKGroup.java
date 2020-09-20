package K个一组翻转链表;
/**
 * FileName: ReverseNodesInKGround.java
 * 类的详细说明
 * 给一个链表，每 k 个节点一组进行翻转，请返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * @label ListNode
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.16
 * @version 1.00
 */

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseNodesInKGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个列表：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            System.out.println("请输入待翻转列表的组数k：2");
            line = in.readLine();
            int k = Integer.parseInt(line);
            ListNode ret = new Solution().reverseKGroup(head, k);
            String out = PublicMethod.listNodeToString(ret);
            System.out.println("翻转后的链表为："+out);
        }
    }
}

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;
        while (true) {
            for (int i = 0; i < k && end != null; i++)
                end = end.next;
            if (end == null)
                break;
            //断开子链表
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            //拼接翻转后的子链表
            pre.next = reverse(start);
            start.next = next;
            //为下一轮翻转准备头节点
            pre = start;
            end = pre;

        }
        return dummy.next;
    }

    //头插法reverse节点
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

