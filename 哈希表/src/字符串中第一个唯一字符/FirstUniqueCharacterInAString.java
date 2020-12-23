package 字符串中第一个唯一字符;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: FirstUniqueCharacterInAString.java
 * 类的详细说明
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.23 - 下午 8:02
 * @label HashTable String
 */
public class FirstUniqueCharacterInAString {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串s：leetcode");
        while ((line = in.readLine()) != null) {
            int result = new SolutionCopy2().firstUniqChar(line);
            System.out.println("第一个不重复字符为：" + result);
        }
    }
}

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>(26);
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}

class SolutionCopy {
    // 暴力破解 双重循环，用每一个字符尝试匹配后面的字符，查看是否有重复字符出现
    public int firstUniqChar(String s) {
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < len; ++j) {
                if (s.charAt(i) == s.charAt(j) && i != j) {
                    break;
                } else if (j == len - 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}

class SolutionCopy2 {
    // 用数组代替哈希
    public int firstUniqChar(String s) {
        int[] map = new int[26];
        for (char ch : s.toCharArray()) {
            map[ch - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); ++i) {
            if (map[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}