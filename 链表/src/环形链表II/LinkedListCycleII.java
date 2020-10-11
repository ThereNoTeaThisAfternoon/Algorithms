package 环形链表II;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * FileName: LinkedListCycleⅡ.java
 * 类的详细说明
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.11 - 上午 11:34
 * @label LinkedList TwoPointers
 */
public class LinkedListCycleII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[3,2,0,-4]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            System.out.println("请输入该链表最后一个节点指向的节点pos：1");
            int pos = Integer.parseInt(in.readLine());
            PublicMethod.createCycle(head, pos);
            ListNode result = new SolutionCopy().detectCycle(head);
            if (result != null) {
                System.out.println("该链表是环形链表，入环节点值为" + result.val);
            } else {
                System.out.println("该链表不是环形链表");
            }
        }
    }
}

class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode cur = head;
        Set<ListNode> visited = new HashSet<>();
        while (cur != null) {
            if (visited.contains(cur)) {
                return cur;
            } else {
                visited.add(cur);
                cur = cur.next;
            }
        }
        return null;
    }
}

class SolutionCopy {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        //使用快慢指针判断链表是否有环
        ListNode fast = head, slow = head;
        boolean hasCycle = false;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        //若有环，找到入环的节点
        if (hasCycle) {
            ListNode target = head;
            while (target != slow) {
                target = target.next;
                slow = slow.next;
            }
            return target;
        }
        return null;
    }
}