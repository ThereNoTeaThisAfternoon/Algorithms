package 合并两个有序链表;
/**
 * FileName: MergeTwoSortedLists.java
 * 类的详细说明
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * @label LinkedList
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.01
 * @version 1.00
 */

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MergeTowSortedLists {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode l1 = PublicMethod.stringToListNode(line);
            line = in.readLine();
            ListNode l2 = PublicMethod.stringToListNode(line);
            ListNode res = new SolutionCopy().mergeTowLists(l1, l2);
            String out = PublicMethod.listNodeToString(res);
            System.out.println(out);
        }
    }
}

class Solution {
    //迭代
    public ListNode mergeTowLists(ListNode l1, ListNode l2) {
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                ptr.next = l1;
                l1 = l1.next;
            } else {
                ptr.next = l2;
                l2 = l2.next;
            }
            ptr = ptr.next;
        }
        ptr.next = l1 == null ? l2 : l1;
        return dummyRoot.next;
    }
}

class SolutionCopy {
    //递归
    public ListNode mergeTowLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;
        else if (l1.val <= l2.val) {
            l1.next = mergeTowLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTowLists(l1, l2.next);
            return l2;
        }
    }
}
