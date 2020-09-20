package 移除重复节点;
/**
 * FileName: RemoveDuplicateNode.java
 * 类的详细说明
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * @label LinkedList
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.26
 * @version 1.00
 */

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateNode {
    public static void main(String[] rgs) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[1,2,3,3,2,1]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            ListNode result = new SolutionCopy().removeDuplicateNode(head);
            String out = PublicMethod.listNodeToString(result);
            System.out.println("去除重复链表后结果为：" + out);
        }
    }
}

class Solution {
    //哈希表
    public ListNode removeDuplicateNode(ListNode head) {
        if (head == null)
            return null;
        Set<Integer> set = new HashSet<>();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null) {
            if (!set.contains(pre.next.val)) {
                set.add(pre.next.val);
                pre = pre.next;
            } else
                pre.next = pre.next.next;
        }
        return dummy.next;
    }
}

class SolutionCopy {
    //双重循环
    public ListNode removeDuplicateNode(ListNode head) {
        ListNode ob = head;
        while (ob != null) {
            ListNode oc = ob;
            while (oc.next != null) {
                if (oc.next.val == ob.val)
                    oc.next = oc.next.next;
                else
                    oc = oc.next;
            }
            ob = ob.next;
        }
        return head;
    }
}