package 字符串解码;
/**
 * FileName: DecodeString.java
 * 类的详细说明
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * @label String Depth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.5.28
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个编码的字符串：3[a2[bc]]");
        while ((line = in.readLine()) != null) {
            String result = new Solution().decodeString(line);
            System.out.println("解码后的字符串为：" + result);
        }
    }
}

class Solution {
    //如果遇到 ']'，就一直在栈中找到 '['，将之间的字符连接起来，将 '['前面的数字连接起来作为出现次数再次压栈，
    // 遇到数字、字母、'['就直接压栈，最后将栈里的字符串弹出连接起来
    Deque<String> stack = new ArrayDeque<>();

    public String decodeString(String s) {
        for (char c : s.toCharArray()) {
            if (c == ']') {
                //获取子串
                StringBuilder subString = new StringBuilder();
                while (!stack.peek().equals("["))
                    subString.insert(0, stack.pop());
                stack.pop();
                //获取子串数目
                StringBuilder countString = new StringBuilder();
                while (!stack.isEmpty() && stack.peek().charAt(0) >= '0' && stack.peek().charAt(0) <= '9')
                    countString.insert(0, stack.pop());
                int count = Integer.parseInt(countString.toString());
                StringBuilder retString = new StringBuilder();
                //展开子串后入栈
                for (int j = 0; j < count; j++)
                    retString.insert(0, subString.toString());
                stack.push(retString.toString());
            } else
                stack.push(String.valueOf(c));
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty())
            ans.insert(0, stack.pop());
        return ans.toString();
    }
}