package 最长回文子串;
/**
 * FileName: LongestPalindromeSubstring.java
 * 类的详细说明
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * @label String
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.21
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestPalindromeSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String result = new Solution().longestPalindrome(line);
            System.out.println("最长回文子串为：" + result);
        }
    }
}

class Solution {
    //中心扩散
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        //保存最长回文的左右起始位置
        int[] range = new int[2];
        char[] chars = s.toCharArray();
        //把回文看成中间部分全是同一字符，左右部分相对称
        for (int i = 0; i < chars.length - 1; i++) {
            //找到下一个字符与当前字符不同的位置
            i = findLongest(chars, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    private int findLongest(char[] chars, int low, int[] range) {
        int high = low;
        //查找中间部分
        while (high < chars.length - 1 && chars[high + 1] == chars[low])
            high++;
        //定位中间部分最后一个字符
        int last = high;
        //从中间向左右扩散
        while (low > 0 && high < chars.length - 1 && chars[low - 1] == chars[high + 1]) {
            low--;
            high++;
        }
        //记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return last;
    }
}