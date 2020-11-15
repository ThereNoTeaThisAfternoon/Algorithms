package 移掉K位数字;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * FileName: RemoveKDigits.java
 * 类的详细说明
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * |--num 的长度小于 10002 且 ≥ k。
 * |--num 不会包含任何前导零。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.15 - 下午 6:36
 * @label GreedyAlgorithm Stack
 */
public class RemoveKDigits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串nums：1432219");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入需要移除几位数字k：3");
            int k = Integer.parseInt(in.readLine());
            String result = new SolutionCopy().removeKDigits(line, k);
            System.out.println("余下数字为：" + result);
        }
    }
}

/**
 * Enum GreedyAlgorithm
 */
class Solution {
    public String removeKDigits(String num, int k) {
        char[] ch = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 删除k位数字
        while (k-- > 0) {
            int index = 0;
            int next = 0;
            // 找到删除后使剩下数字最小的目标数字的索引
            while (next + 1 < ch.length && (ch[next + 1] == '-' || ch[index] <= ch[next + 1])) {
                next++;
                if (ch[next] != '-') {
                    index = next;
                }
            }
            ch[index] = '-';
        }
        // 依序组合余下数字
        for (char c : ch) {
            if (c != '-' && sb.length() != 0 && c != '0') {
                sb.append(c);
            }
        }
        return sb.toString().length() == 0 ? "0" : sb.toString();
    }
}

/**
 * Stack GreedyAlgorithm
 */
class SolutionCopy {

    public String removeKDigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        /*
         * 对于每个数字，如果该数字小于栈顶元素，就不断地弹出栈顶元素，直到
         * 栈为空
         * 或者新的栈顶元素不大于当前数字
         * 或者我们已经删除了 k 位数字
         */
        for (int i = 0; i < num.length(); i++) {
            char cur = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > cur) {
                stack.pollLast();
                k--;
            }
            stack.offerLast(cur);
        }
        // 如果删除了 m 个数字且 m<k，这种情况下需要从序列尾部删除额外的 k−m 个数字。
        while (k > 0) {
            stack.pollLast();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        /*
         * 如果最终的数字序列存在前导零，要删去前导零。
         * 如果最终数字序列为空，返回 0。
         * 使用双端队列，避免翻转操作
         */
        while (!stack.isEmpty()) {
            Character digit = stack.pollFirst(); // 从栈底依次输出
            if (sb.length() != 0 || digit != '0') { // 过滤开头数字
                sb.append(digit);
            }
        }
        return sb.toString().length() == 0 ? "0" : sb.toString();
    }
}