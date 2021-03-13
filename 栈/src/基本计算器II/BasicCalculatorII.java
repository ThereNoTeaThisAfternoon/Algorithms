package 基本计算器II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * FileName: BasicCalculatorII.java
 * 类的详细说明
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.11 - 下午 8:45
 * @label Stack String
 */
public class BasicCalculatorII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串表达式s：3+2*2");
        while ((line = in.readLine()) != null) {
            int result = new Solution().calculate(line);
            System.out.println("计算结果为：" + result);
        }
    }
}

class Solution {
    public int calculate(String s) {
        int res = 0, d = 0;
        char sign = '+';
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= '0') {//加减乘除和空格ASCII码都小于'0'
                d = d * 10 - '0' + s.charAt(i);//进位(先减法)
            }
            if ((s.charAt(i) < '0' && s.charAt(i) != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    nums.push(d);
                } else if (sign == '-') {
                    nums.push(-d);
                } else if (sign == '*' || sign == '/') {
                    int tmp = sign == '*' ? nums.peek() * d : nums.peek() / d;
                    nums.pop();
                    nums.push(tmp);
                }
                sign = s.charAt(i); //保存当前符号
                d = 0;
            }
        }
        for (; !nums.empty(); nums.pop()) {
            res += nums.peek();
        }
        return res;
    }
}

class SolutionCopy {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        // sign 代表正负
        int sign = 1, res = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int cur = ch - '0';
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                    cur = cur * 10 + s.charAt(++i) - '0';
                res = res + sign * cur;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (ch == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }
}