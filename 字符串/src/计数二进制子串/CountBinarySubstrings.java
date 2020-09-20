package 计数二进制子串;
/**
 * FileName: CountBinarySubstrings.java
 * 类的详细说明
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 *
 * @label String
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.8.11
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountBinarySubstrings {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个仅由0和1组成的字符串：00110011");
        while ((line = in.readLine()) != null) {
            int result = new Solution().countBinarySubstrings(line);
            System.out.println("重复出现的组合子串的出现的次数为：" + result);
        }
    }
}

class Solution {

    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0)
            return 0;
        // pre 前一个数字连续出现的次数，cur 当前数字连续出现的次数，ans 结果子串个数
        int ans = 0, pre = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            // 判断当前数字是否与后一个数字相同
            if (s.charAt(i) == s.charAt(i - 1))
                cur++;
            else {// 不同，则当前数字事实上变成了前一个数字，当前数字的次数重置为1
                pre = cur;
                cur = 1;
            }
            if (pre >= cur)// 前一个数字出现的次数 >= 后一个数字出现的次数，则一定包含满足条件的子串
                ans++;
        }
        return ans;
    }
}