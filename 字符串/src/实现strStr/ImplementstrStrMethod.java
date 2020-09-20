package 实现strStr;
/**
 * FileName: ImplementstrStrMethod.java
 * 类的详细说明
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，
 * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 当needle为空时应该返回 0
 *
 * @label String TwoPointer
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.21
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImplementstrStrMethod {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串haystack：hello");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入一个待匹配字符串needle：ll");
            String needle = in.readLine();
            int result = new Solution().strStr(line, needle);
            System.out.println("needle在haystack第一次出现的位置为：" + result);
        }
    }
}

class Solution {

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int left = i, right = i + needle.length() - 1;
            while (left < right) {
                if (haystack.charAt(left) != needle.charAt(left - i) || haystack.charAt(right) != needle.charAt(right - i))
                    break;
                left++;
                right--;
            }
            if (needle.length() % 2 == 0 && left > right)
                return i;
            if (needle.length() % 2 != 0 && left == right && haystack.charAt(left) == needle.charAt(right - i))
                return i;
        }
        return -1;
    }
}