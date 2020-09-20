package 回文子串;
/**
 * FileName: PalindromicSubstrings.java
 * 类的详细说明
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even
 * they consist of same characters.
 *
 * @label String DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.8.19
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PalindromicSubstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串：abc");
        while ((line = in.readLine()) != null) {
            int result = new Solution().countSubstrings(line);
            System.out.println("子串总数为：" + result);
        }
    }
}

//暴力破解
class Solution {
    //枚举出所有的子串，然后再判断这些子串是否是回文
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (isPalindromic(chars, i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean isPalindromic(char[] chars, int start, int end) {
        while (start <= end && chars[start] == chars[end]) {
            start++;
            end--;
        }
        return start > end;
    }
}

//中心拓展
class SolutionCopy1 {
    private int num = 0;

    //枚举每一个可能的回文中心，然后用两个指针分别向左右两边拓展，当两个指针指向的元素相同的时候就拓展，否则停止拓展。
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            count(chars, i, i);//回文串为奇数串
            count(chars, i, i + 1);//回文串为偶数串
        }
        return num;
    }

    private void count(char[] chars, int start, int end) {
        while (start >= 0 && end < chars.length && chars[start] == chars[end]) {
            num++;
            start--;
            end++;
        }
    }
}

//Manacher
class SolutionCopy2 {

    public int countSubstrings(String s) {
        int n = s.length();
        StringBuilder t = new StringBuilder("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }
        return ans;
    }
}