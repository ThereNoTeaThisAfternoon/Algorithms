package 重新排列句子中的单词;
/**
 * FileName: RearrangeWordsInASentence.java
 * 类的详细说明
 * text = "Hello world everyday are beautiful days"
 * 句子的首字母大写，text 中的每个单词都用单个空格分隔。
 * 重新排列 text 中的单词，使所有单词按其长度的升序排列。
 * 如果两个单词的长度相同，则保留其在原句子中的相对顺序。
 *
 * @label String Sort
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.19
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RearrangeWordsInASentence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串：Hello world everyday are beautiful days");
        while ((line = in.readLine()) != null) {
            String text = line;
            String ret = new SolutionCopy().arrangeWords(text);
            System.out.println("重新排列后字符串为：" + ret);
        }
    }
}

class Solution {
    //冒泡排序
    public String arrangeWords(String text) {
        text = text.trim();
        String[] strings = text.split(" ");
        strings[0] = (char) (strings[0].charAt(0) + 32) + strings[0].substring(1);

        for (int i = 0; i < strings.length - 1; i++) {
            for (int j = 0; j < strings.length - 1; j++) {
                if (strings[j].length() > strings[j + 1].length()) {
                    String temp = strings[j];
                    strings[j] = strings[j + 1];
                    strings[j + 1] = temp;
                }
            }
        }
        strings[0] = (char) (strings[0].charAt(0) - 32) + strings[0].substring(1);
        StringBuilder ans = new StringBuilder();
        for (String s : strings)
            ans.append(s).append(" ");
        return ans.toString().substring(0, ans.toString().length() - 1);
    }
}

class SolutionCopy {
    //HashMap
    public String arrangeWords(String text) {
        text = (char) (text.substring(0, 1).charAt(0) + 32) + text.substring(1);
        Map<Integer, List<String>> map = new HashMap<>();
        for (String string : text.split(" ")) {
            int len = string.length();
            List<String> list = map.computeIfAbsent(len, k -> new ArrayList<>());
            list.add(string);
        }
        StringBuilder sB = new StringBuilder(text.length());
        for (List<String> list : map.values()) {
            for (String s : list) {
                sB.append(s).append(" ");
            }
        }
        sB.setCharAt(0, Character.toUpperCase(sB.charAt(0)));
        sB.substring(0, sB.length() - 1);
        return sB.toString();
    }
}