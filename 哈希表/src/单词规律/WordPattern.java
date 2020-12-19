package 单词规律;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: WorkPattern.java
 * 类的详细说明
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.16 - 上午 11:49
 * @label HashTable
 */
public class WordPattern {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一种规律pattern：abba");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入字符串str：dog cat cat dog");
            String str = in.readLine();
            boolean result = new Solution().wordPattern(line, str);
            System.out.println("str" + (result ? "" : "不") + "遵守规律");
        }
    }
}

class Solution {

    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            return false;
        }
        String[] words = s.split(" ");
        // 长度不相等
        if (pattern.length() != words.length) {
            return false;
        }
        // 集合 map将 key(a) - value(dog)存在一起，遇到字母则去查看
        Map<Character, String> map = new HashMap<>();

        // 失败情况
        // 1、key存在，查找该字母对应的单词和这个单词不匹配
        // 2、key不存在，但该单词被存储
        for (int i = 0; i < pattern.length(); ++i) {
            char cur = pattern.charAt(i);
            // key存在
            if (map.containsKey(cur)) {
                if (!map.get(cur).equals(words[i])) {
                    return false;
                }
            } else { // key不存在
                if (map.containsValue(words[i])) {
                    return false;
                } else {
                    map.put(cur, words[i]);
                }
            }
        }
        return true;
    }
}