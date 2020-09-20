package 合并K个排序链表;
/**
 * FileName: MergeKSortedLists.java
 * 类的详细说明
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 测试用例：[[1,4,5],[1,3,4],[2,6]]
 *
 * @label LinkedList Heap DivideAndConquer
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.26
 * @version 1.00
 */

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            ListNode[] lists = PublicMethod.stringToInteger2dListNode(line);
            ListNode res = new Solution().mergeKLists(lists);
            String out = PublicMethod.listNodeToString(res);
            System.out.println(out);
        }
    }
}

class Solution {
    //逐一比较
    //k个指针分别指向 k 条链表
    //比较k个节点（每个链表的首节点），获得最小值节点
    //将选中节点接在最终有序链表后面
    //复杂度 O(KN)O(N)
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (true) {
            ListNode minNode = null;
            int minPointer = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            if (minPointer == -1) {
                break;
            }
            tail.next = minNode;
            tail = tail.next;
            lists[minPointer] = lists[minPointer].next;
        }
        return dummyHead.next;
    }
}

class SolutionCopy1 {
    //暴力求解
    //小根堆
    //复杂度 O(N logN)O(N)
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }
        return dummyHead.next;
    }
}

class SolutionCopy2 {
    //分治法
    //将K个链表配对，并将同一对链表合并
    //k -> k/2 -> k/4
    //直到得到最终有序链表
    //复杂度 O(N logK)O(1)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = merge(lists, lo, mid);
        ListNode l2 = merge(lists, mid + 1, hi);
        return merge2Lists(l1, l2);
    }

    //合并两条链表
    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge2Lists(l1.next, l2);
            return l1;
        }
        l2.next = merge2Lists(l1, l2.next);
        return l2;
    }
}