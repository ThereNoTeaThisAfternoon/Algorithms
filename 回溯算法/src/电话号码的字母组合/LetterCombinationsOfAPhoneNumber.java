package 电话号码的字母组合;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: LetterCombinationsOfAPhoneNumber.java
 * 类的详细说明
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @label Backtracking String
 * @Date 2020.8.19
 */

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个仅包含数字 2-9 的字符串：23");
        while ((line = in.readLine()) != null) {
            List<String> result = new Solution().letterCombinations(line);
            for (String str : result) {
                System.out.print(str + " ");
            }
            System.err.println("");
        }
    }
}

class Solution {
    // 数字到号码的映射
    private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    // 路径
    private StringBuilder sb = new StringBuilder();
    // 结果集
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        backtracking(digits, 0);
        return res;
    }

    //回溯函数
    private void backtracking(String digits, int index) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String val = map[digits.charAt(index) - '2'];
        for (char ch : val.toCharArray()) {
            sb.append(ch);
            backtracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}