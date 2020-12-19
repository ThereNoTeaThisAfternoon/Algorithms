package 找不同;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: FindTheDifference.java
 * 类的详细说明
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.19 - 上午 11:36
 * @label BitManipulation HashTable
 */
public class FindTheDifference {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串s：abcd");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入的字符串t：abcde");
            String t = in.readLine();
            char result = new Solution().findTheDifference(line, t);
            System.out.println("被添加的字母为：" + result);
        }
    }
}

// 位运算
class Solution {

    public char findTheDifference(String s, String t) {
        int target = 0;
        for (char ch : s.toCharArray()) {
            target ^= ch;
        }
        for (char ch : t.toCharArray()) {
            target ^= ch;
        }
        return (char) target;
    }
}

class SolutionCopy {
    public char findTheDifference(String s, String t) {
        int[] letters = new int[26];

        for(char ch : s.toCharArray()){
            letters[ch - 'a'] += 1;
        }
        for(int i = 0; i < t.length(); ++i){
            char ch = t.charAt(i);
            letters[ch - 'a'] -= 1;
            if(letters[ch - 'a'] < 0){
                return (char)(97 + i);
            }
        }
        return ' ';
    }
}

// 将所给的字符串转换为字符数组，求字符数组的int和，作差，再转回char，返回
class SolutionCopy2{
    public char findTheDifference(String s, String t){
        int sum = 0;
        for(char ch : t.toCharArray()){
            sum += ch;
        }
        for(char ch : s.toCharArray()){
            sum -= ch;
        }
        return (char) sum;
    }
}