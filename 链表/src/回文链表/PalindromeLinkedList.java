package 回文链表;

import ListNode公共方法.ListNode;
import ListNode公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: PalindromeLinkedList.java
 * 类的详细说明
 * 请判断一个链表是否为回文链表
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.23 - 下午 4:02
 * @label LinkedList TwoPointers Math Recursive
 */
public class PalindromeLinkedList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个链表：[1,2,2,1]");
        while ((line = in.readLine()) != null) {
            ListNode head = PublicMethod.stringToListNode(line);
            boolean result = new Solution().isPalindrome(head);
            System.out.println("该链表" + (result ? "是" : "不是") + "回文链表");
        }
    }
}

/**
 * 使用集合
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        // 链表为空或仅有一个节点
        if (head == null || head.next == null) {
            return true;
        }
        // 将链表中每一个节点放入集合中
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        // compare
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                break;
            }
            left++;
            right--;
        }
        return left >= right;
    }
}

/**
 * 数学
 */
class SolutionCopy {
    public boolean isPalindrome(ListNode head) {
        float s1 = 0, s2 = 0, t = 1;
        while (head != null) {
            s1 = s1 * 10 + head.val;
            s2 = head.val * t + s2;
            t *= 10;
            head = head.next;
        }
        return s1 == s2;
    }
}

/**
 * 快慢指针
 */
class SolutionCopy2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;

        //找到链表中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //将slow之后链表反转
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        //前后端链表进行比较
        while (pre != null) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }

    // 递归方式反转后半段链表
    private ListNode reverse(ListNode head) {
        // 递归到最后一个节点，返回新的新的头结点
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

/**
 * Recursive
 */
class SolutionCopy3 {

    ListNode temp;

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        temp = head;
        return dfs(head);
    }

    public boolean dfs(ListNode head) {
        if (head == null) return true;
        boolean res = dfs(head.next) && temp.val == head.val;
        temp = temp.next;
        return res;
    }
}