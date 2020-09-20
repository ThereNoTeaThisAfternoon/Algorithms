package 最长公共前缀;
/**
 * FileName: LongestCommonPrefix.java
 * 类的详细说明
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @label String
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.25 6.15
 * @version 1.01
 */

import String公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonPrefix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String[] strs = PublicMethod.stringToStringArray(line);
            String ret = new SolutionCopy1().longestCommonPrefix(strs);
            System.out.println(ret);
        }
    }
}

class Solution {
    //横向扫描
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < len; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
        }
        return prefix;
    }

    private String longestCommonPrefix(String res, String str) {
        int len = Math.min(res.length(), str.length());
        int index = 0;
        while (index < len && res.charAt(index) == str.charAt(index)) {
            index++;
        }
        return str.substring(0, index);
    }
}

class SolutionCopy1 {
    //横向扫描简化
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String res = strs[0];

        for (String str : strs) {
            while (str.indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
}

class SolutionCopy2 {
    //纵向遍历
    public String longestCommonPrefix(String[] strs) {
        int count = strs.length;
        if (count == 0)
            return "";
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            char cur = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != cur)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
