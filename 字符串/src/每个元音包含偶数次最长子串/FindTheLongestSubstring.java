package 每个元音包含偶数次最长子串;
/**
 * FileName: FindTheLongestSubstring
 * 类的详细说明
 * 给一个字符串 s ，请返回满足以下条件的最长子字符串的长度：
 * 每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。包括零次
 *
 * @label String TwoPointers
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.20
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindTheLongestSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串：background");
        while ((line = in.readLine()) != null) {
            int ret = new SolutionCopy1().findTheLongestSubstring(line);
            String out = Integer.toString(ret);
            System.out.println("最长元音字母出现偶数次字串长度为：" + out);
        }
    }
}

class Solution {
    //枚举法
    //枚举所有子串，遍历子串中的所有字符，统计元音字母出现的个数。如果符合条件，就更新答案
    public int findTheLongestSubstring(String s) {
        int len = s.length();
        int maxSubLen = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                boolean flag = find(s, i, j);
                maxSubLen = flag ? Math.max(maxSubLen, j - i + 1) : maxSubLen;
            }
        }
        return maxSubLen;
    }

    private boolean find(String s, int left, int right) {
        int a = 0, e = 0, i = 0, o = 0, u = 0;
        while (left <= right) {
            switch (s.charAt(left)) {
                case 'a':
                    a++;
                    break;
                case 'e':
                    e++;
                    break;
                case 'i':
                    i++;
                    break;
                case 'o':
                    o++;
                    break;
                case 'u':
                    u++;
                    break;
            }
            left++;
        }
        return a % 2 == 0 && e % 2 == 0 && i % 2 == 0 && o % 2 == 0 && u % 2 == 0;
    }
}

class SolutionCopy {
    public int findTheLongestSubstring(String s) {
        int[] f = new int[26];
        f[4] = 1;
        f[8] = 2;
        f[14] = 3;
        f[20] = 4;
        int[] dp = new int[32];
        dp[0] = 1;
        char[] ss = s.toCharArray();
        int len = ss.length;
        int lst = 0;
        int r = 0;
        int fk = -1;
        for (int i = 0; i < len; ++i) {
            int c = ss[i] - 'a';
            if (f[c] > 0 || c == 0) {
                if (fk != i - 1) {
                    r = Math.max(i + 1 - dp[lst], r);
                }
                lst ^= 1 << f[c];
                if (dp[lst] != 0) {
                    r = Math.max(i + 2 - dp[lst], r);
                    fk = i;
                } else {
                    dp[lst] = i + 2;
                }
            }
        }
        if (dp[lst] != 0) {
            r = Math.max(len + 1 - dp[lst], r);
        }
        return r;
    }
}

class SolutionCopy1 {
    //DP 位运算 状态压缩
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];//pos.length = 32
        Arrays.fill(pos, -1);//pos[i] = -1
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {//s[0] -> s[s.length()-1]
            char ch = s.charAt(i);//字符串 s 的 i 位置处的字符
            //一个数 ^ 自己值为零
            if (ch == 'a') {
                status ^= (1);// status^1
            } else if (ch == 'e') {
                status ^= (1 << 1);// status^2
            } else if (ch == 'i') {
                status ^= (1 << 2);// status^4
            } else if (ch == 'o') {
                status ^= (1 << 3);// status^8
            } else if (ch == 'u') {
                status ^= (1 << 4);// status^16
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }
}