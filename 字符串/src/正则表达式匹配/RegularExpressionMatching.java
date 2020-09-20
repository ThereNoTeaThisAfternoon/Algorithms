package 正则表达式匹配;
/**
 * FileName: RegularExpressionMatching
 * 类的详细说明
 * 给一个字符串 s 和一个字符规律 p，请来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * @label String DynamicProgramming Backtracking
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.20
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegularExpressionMatching {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串s：aab");
        while ((line = in.readLine()) != null) {
            String s = line;
            System.out.println("输入一个字符规律p:c*a*b");
            String p = in.readLine();
            boolean result = new Solution().isMatch(s, p);
            String out = result ? "能" : "不能";
            System.out.printf("字符规律 %s %s匹配 %s\n", p, out, s);
        }
    }
}

class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        //第一个字符是否匹配 当p的第一个元素为'.'时，也代表是匹配的
        boolean first_match = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        //接下来分两种情况
        //1、'*'匹配0个字符，此时我们保持s不变，减去p的前两个元素继续比较
        //2、'*'匹配1个或多个字符。我们保持p不变，减去s的第一个元素继续比较
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2))) || (first_match && isMatch(s.substring(1), p));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
}

class SolutionCopy {
    //DP
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}