package 查找常用字符;

import String公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: FindCommonCharacters.java
 * 类的详细说明
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.15 - 上午 8:17
 * @label String Array HashTable
 */
public class FindCommonCharacters {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串数组：[\"bella\",\"label\",\"roller\"]");
        while ((line = in.readLine()) != null) {
            String[] A = PublicMethod.stringToStringArray(line);
            List<String> result = new Solution().commonChars(A);
            System.out.println("所有字符串的交集为：" + result);
        }
    }
}

class Solution {
    // 求每个字符串之间字符数量的交集
    public List<String> commonChars(String... A) {
        List<String> list = new ArrayList<>();
        int[] res = new int[26];
        for (char c : A[0].toCharArray()) {
            res[c - 'a']++;
        }
        // 两两比较，获取交集
        for (int i = 1; i < A.length; i++) {
            int[] temp = new int[26];
            for (char c : A[i].toCharArray()) {
                temp[c - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                res[j] = Math.min(res[j], temp[j]);
            }
        }
        // 添加到集合
        for (int i = 0; i < res.length; i++) {
            if (res[i] > 0) {
                for (int j = 0; j < res[i]; j++) {
                    list.add(((char) ('a' + i) + ""));
                }
            }
        }
        return list;
    }
}