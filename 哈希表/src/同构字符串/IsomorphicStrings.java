package 同构字符串;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: IsomorphicStrings.java
 * 类的详细说明
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。
 * 两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.27 - 下午 3:44
 * @label Hashtable
 */
public class IsomorphicStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        System.out.println("请输入一个字符串s：eggs");
        while ((s = in.readLine()) != null) {
            System.out.println("请输入一个字符串t：addr");
            String t = in.readLine();
            boolean result = new Solution().isIsomorphic(s, t);
            System.out.println((result ? "" : "不") + "是同构字符串");
        }
    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        // 失败情况
        // 1、key存在，查找该字母对应的单词和这个单词不匹配
        // 2、key不存在，但该单词被存储
        for (int i = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }
}