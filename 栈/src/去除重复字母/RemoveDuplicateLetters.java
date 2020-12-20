package 去除重复字母;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * FileName: RemoveDuplicateLetters.java
 * 类的详细说明
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.20 - 下午 1:48
 * @label Stack String
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串s：cbacdcbc");
        while ((line = in.readLine()) != null) {
            String result = new Solution().removeDuplicateLetters(line);
            System.out.println(result);
        }
    }
}

class Solution {
    // 遇到一个新字符 如果比栈顶小 并且在新字符后面还有和栈顶一样的 就把栈顶的字符抛弃了
    public String removeDuplicateLetters(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i) {
            Character c = s.charAt(i);
            // 去重
            if (stack.contains(c)) {
                continue;
            }
            // 如果比栈顶小，且带入栈字符后面还有栈顶字符，则出栈
            while (!stack.isEmpty() && stack.peek() > c && s.indexOf(stack.peek(), i) != -1) {
                stack.pop();
            }
            stack.push(c);
        }
        StringBuilder ans = new StringBuilder();
        for (char c : stack) {
            ans.insert(0, c);
        }
        return ans.toString();
    }
}