package 上升下降字符串;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: IncreasingDecreasingString.java
 * 类的详细说明
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：
 * |--从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
 * |--从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
 * |----重复步骤 2 ，直到你没法从 s 中选择字符。
 * |--从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
 * |--从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
 * |----重复步骤 5 ，直到你没法从 s 中选择字符。
 * |--重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。
 * 请你返回将 s 中字符重新排序后的 结果字符串 。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.25 - 下午 2:31
 * @label String Sort
 */
public class IncreasingDecreasingString {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串s：aaabbbccc");
        while ((line = in.readLine()) != null) {
            String s = new Solution().sortString(line);
            System.out.println("排序后：" + s);
        }
    }
}

class Solution {
    public String sortString(String s) {
        int[] map = new int[26];
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            map[c - 'a'] += 1;
        }
        while (sb.length() < len) {
            // 从小往大添加
            for (int i = 0; i < 26; ++i) {
                if (map[i] > 0) {
                    sb.append('a' + i);
                    map[i] -= 1;
                }
            }
            // 从大往小添加
            for (int i = 25; i >= 0; --i) {
                if (map[i] > 0) {
                    sb.append('a' + i);
                    map[i] -= 1;
                }
            }
        }
        return sb.toString();
    }
}