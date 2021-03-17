package 不同的子序列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: DistinctSubsequences.java
 * 类的详细说明
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.17 - 下午 9:49
 * @label DynamicProgramming String
 */
public class DistinctSubsequences {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入字符串s：rabbbit");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入字符串t：rabbit");
            String t = in.readLine();
            int result = new Solution().numDistinct(line, t);
            System.out.println("s的子序列中t出现个数为：" + result);
        }
    }
}

/**
 * 自顶而下
 */
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j > i)
                    continue;
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}

/**
 * 自底而上
 */
class SolutionCopy {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; --i) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; --j) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }
}

/**
 * 一维DP
 */
class SolutionCopy2 {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0) return 0;
        if (t == null || t.length() == 0) return 1;
        int m = t.length();
        int[] dp = new int[m + 1];
        dp[0] = 1;

        for (int i = 0; i < s.length(); i++) {
            for (int j = m - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j))
                    dp[j + 1] += dp[j];
            }
        }
        return dp[m];
    }
}

/**
 * 暴力递归
 */
class SolutionCopy3 {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0) return 0;
        if (t == null || t.length() == 0) return 1;

        return dfs(s, t, 0, 0);
    }

    private int dfs(String s, String t, int i, int j) {
        if (j == t.length()) return 1;
        if (i == s.length()) return 0;

        int a = 0, b = 0;
        if (s.charAt(i) == t.charAt(j))
            a = dfs(s, t, i + 1, j + 1) + dfs(s, t, i + 1, j); // 选 i + 不选 i
        else
            b = dfs(s, t, i + 1, j); // 不选 i

        return a + b;
    }
}

/**
 * 记忆化递归
 */
class SolutionCopy4 {
    public int numDistinct(String s, String t) {
        if (s == null || s.length() == 0) return 0;
        if (t == null || t.length() == 0) return 1;

        return dfs(s, t, 0, 0, new Integer[s.length()][t.length()]);
    }

    private int dfs(String s, String t, int i, int j, Integer[][] dp) {
        if (j == t.length()) return 1;
        if (i == s.length()) return 0;

        if (dp[i][j] != null) return dp[i][j];  // 之前算过了，存在dp里呢，那就直接拿来用

        int a = 0, b = 0;
        if (s.charAt(i) == t.charAt(j))
            a = dfs(s, t, i + 1, j + 1, dp) + dfs(s, t, i + 1, j, dp); // 选 i + 不选 i
        else
            b = dfs(s, t, i + 1, j, dp);  // 不选 i

        dp[i][j] = a + b;  // 答案存进dp后再返回。让 dp ”记住”
        return dp[i][j];
    }
}
