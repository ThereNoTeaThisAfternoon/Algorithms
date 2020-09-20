package 单词拆分;
/**
 * FileName: WordBreak.java
 * 类的详细说明
 *给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.25
 * @version 1.00
 */
import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串：helloworld");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入一个字符串列表：[\"hello\",\"world\"]");
            List<String> wordDict = new PublicMethod().stringToStringList(in.readLine());
            boolean result = new Solution().wordBreak(line, wordDict);
            String out = result ? "" : "不";
            System.out.printf("s%s可以被空格拆分为一个或多个在字典中出现的单词。\n", out);
        }
    }
}

class Solution {
    /*
        动态规划算法，dp[i]表示s前i个字符能否拆分
        转移方程：dp[j] = dp[i] && check(s[i+1, j]);
        check(s[i+1, j])就是判断i+1到j这一段字符是否能够拆分
        其实，调整遍历顺序，这等价于s[i+1, j]是否是wordDict中的元素
        这个举个例子就很容易理解。
        假如wordDict=["apple", "pen", "code"],s = "applepencode";
        dp[8] = dp[5] + check("pen")
        翻译一下：前八位能否拆分取决于前五位能否拆分，加上五到八位是否属于字典
        （注意：i的顺序是从j-1 -> 0哦~
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //memo[i] 表示 s 中 以 i-1结尾的字符串是否可被 wordDict 拆分
        boolean[] memo = new boolean[len + 1];
        memo[0] = true;

        for (int i = 1; i <= len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[len];
    }
}