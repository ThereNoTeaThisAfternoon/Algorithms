package 旋转链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: RotateList.java
 * 类的详细说明
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.27 - 下午 10:05
 * @label LinkedList TwoPointers
 */
public class RotateList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型链表head：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            System.out.println("请输入将节点向右移动的次数k：2");
            int k = Integer.parseInt(in.readLine());
            ListNode result = new SolutionCopy2().rotateRight(head, k);
            String out = PublicMethod.listNodeToString(result);
            System.out.println(out);
        }
    }
}

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int size = list.size();
        k = size - (k % size); // k == 0，最终得到k为数组长度，导致索引越界
        head = list.get(k % size);
        cur = head;
        for (int i = k + 1; i < size + k; ++i) {
            cur.next = list.get(i % size);
            cur = cur.next;
        }
        cur.next = null;
        return head;
    }
}

/**
 * TwoPointers
 */
class SolutionCopy {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode cursor = head;
        ListNode tail;//尾指针
        int length = 1;
        while (cursor.next != null) {//循环 得到总长度
            cursor = cursor.next;
            length++;
        }
        int loop = length - (k % length);//得到循环的次数
        tail = cursor;//指向尾结点
        cursor.next = head;//改成循环链表
        cursor = head;//指向头结点
        for (int i = 0; i < loop; i++) {//开始循环
            cursor = cursor.next;
            tail = tail.next;
        }
        tail.next = null;//改成单链表
        return cursor;//返回当前头
    }
}

class SolutionCopy2 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int length = 1;
        ListNode cursor = head;
        while (cursor.next != null) {// 得到总长度
            cursor = cursor.next;
            length++;
        }
        int loop = length - (k % length); // 获取循环次数
        cursor.next = head; // 连接首尾

        while (loop-- > 0) {
            cursor = cursor.next;
        }
        head = cursor.next;
        cursor.next = null;
        return head;
    }
}