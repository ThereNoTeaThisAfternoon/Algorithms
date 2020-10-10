package 环形链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: LinkedListCycle.java
 * 类的详细说明
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.10 - 下午 2:18
 * @label LinkedList TwoPointers
 */
public class LinkedListCycle {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[3,2,0,-4]" );
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            boolean result = new Solution().hasCycle(head);
            System.out.println("该链表：" + (result ? "是" : "不是" ) + "环形链表" );
        }
    }
}


class Solution {
    //根据我多年经验，链表不会太长，设定一个循环计数器，完成环状情况下，循环的退出
    public boolean hasCycle(ListNode head) {
        int counter = 1 << 13;
        ListNode cur = head;
        while (cur != null) {
            if (counter == 0) {
                return true;
            }
            counter--;
            cur = cur.next;
        }
        return false;
    }
}

class SolutionC {
    public boolean hasCycle(ListNode head) {
        //不存在链表，或仅有一个链表不成环
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next;//快指针
        ListNode slow = head;//慢指针
        while (fast != slow) {
            //快指针的下一个节点，或下下个节点为null，走到链表尾部
            if (fast == null || fast.next == null) {
                return false;
            }
            //迭代过程中，每轮循环，快指针走两步，慢指针走一步
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;//快指针追上慢指针
    }
}