package 对链表进行插入排序;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * FileName: InsertionSortList.java
 * 类的详细说明
 * 对链表进行插入排序
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.20 - 下午 7:59
 * @label LinkedList Sort
 */
public class InsertionSortList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型链表head：[6,5,3,1,8,7,2,4]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            ListNode result = new SolutionCopy().insertionSortList(head);
            String output = PublicMethod.listNodeToString(result);
            System.out.println(output);
        }
    }
}

/**
 * 想要排序块，就要尽可能少的做比较
 * 需要一个指针指向当前已排序的最后一个位置，这里用的是head指针
 * 需要另外一个指针pre,每次从表头循环，这里用的是dummy表头指针
 * 每次拿出未排序的节点，先和前驱(head)比较，如果大于或者等于前驱，就不用排序了，直接进入下一次循环
 * 如果前驱小，则进入内层循环，依次和pre.next指针比较，插入对应位置即可。
 */
class Solution {

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1), pre;
        dummy.next = head;
        while (head != null && head.next != null) {
            // 判断是否需要插入
            if (head.val < head.next.val) {
                head = head.next;
                continue;
            }
            // 找到待插入节点的待插入位置
            pre = dummy;
            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }
            // 取出节点插入指定位置
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
        }
        return dummy.next;
    }
}

/**
 * Enum，将链表的每一个节点值存入集合，然后排序，再依序放入链表
 */
class SolutionCopy {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<Integer> list = new ArrayList<>();
        ListNode next = head;
        while (next != null) {
            list.add(next.val);
            next = next.next;
        }
        // 插入排序
        insertSort(list);
        Iterator<Integer> iterator = list.iterator();
        next = head;
        while (iterator.hasNext()) {
            next.val = iterator.next();
            next = next.next;
        }
        return head;
    }

    private void insertSort(List<Integer> list) {
        for (int i = 1; i < list.size(); ++i) {
            for (int j = i; j > 0; --j) {
                if (list.get(j) < list.get(j - 1)) {
                    int tmp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, tmp);
                }
            }
        }
    }

}