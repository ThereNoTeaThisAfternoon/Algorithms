package 最小覆盖串;
/**
 * FileName: MinimumWindowSubstring.java
 * 类的详细说明
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * S = "ADOBECODEBANC", T = "ABC"
 *
 * @label String SlidingWindow HashTable TwoPointer
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.23
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumWindowSubstring {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入字符串S：ADOBECODEBANC");
        System.out.println("请输入字符串T：ABC");
        while ((line = in.readLine()) != null) {
            String s = line;
            String t = in.readLine();
            String result = new Solution().minWindow(s, t);
            System.out.println("这样的字串为：" + result);
        }
    }
}

class Solution {
    public String minWindow(String s, String t) {
        //创建一个整型数据，下标表示该位置字符的ASCII，索引值为该字符的数量
        int[] mq = new int[128];
        //将字符串 t 中每一个字符存入 mq 数组中
        for (char c : t.toCharArray())
            mq[c] += 1;
        int left = 0, right = 0;//s的子串的开头 结尾位置
        int sLen = s.length(), tLen = t.length();
        int count = 0;//计算 s 的字串中包含 t 串中字符数量
        int res = -1;//当前包含串t的子串长度
        String ans = "";

        while (right < sLen) {//子串不包含t时，窗口开始扩大
            char c = s.charAt(right);
            mq[c] -= 1;
            if (mq[c] >= 0)
                count++;
            while (count == tLen) {//当字串中包含t时，窗口开始缩小
                if (res == -1 || res > right - left + 1) {
                    ans = s.substring(left, right + 1);
                    res = right - left + 1;//保留s的子串当前长度
                }
                c = s.charAt(left);
                mq[c] += 1;
                if (mq[c] >= 1)
                    count--;
                left++;
            }
            right++;
        }
        return ans;
    }
}









