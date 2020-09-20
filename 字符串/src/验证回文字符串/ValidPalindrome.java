package 验证回文字符串;
/**
 * FileName: ValidPalindrome.java
 * 类的详细说明
 * case 1:给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。"aba"
 * case 2:给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。"A man, a plan, a canal: Panama"
 *
 * @label String TwoPointers RegularExpression
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.19 6.19
 * @version 1.01
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ValidPalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("case1:请输入一个待验证的回文字符串：cuucu");
        System.out.println("case2:请输入一个待验证的回文字符串：A man, a plan, a canal: Panama");
        while ((line = in.readLine()) != null) {
            boolean ret = new SolutionCopy().validPalindrome(line);
            String out = ret ? "是" : "不是";
            System.out.println("该字符串" + out + "回文！");
        }
    }
}

class Solution {
    //子串范围为(i+1, j)或(i, j-1)的俩子串只要有任意一个是回文串，则结果就是回文串，否则就不是。
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return isValid(s, i + 1, j) || isValid(s, i, j - 1);
            i++;
            j--;
        }
        return true;
    }

    //判断子串是不是回文
    private boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}

//字母大小写转换
//统一转成大写：ch & 0b11011111 简写：ch & 0xDF
//统一转成小写：ch | 0b00100000 简写：ch | 0x20
//位运算优先级比较低，注意加括号。
class SolutionCopy {
    //正则表达式
    public boolean validPalindrome(String s) {
        if (s.length() == 0) return true;
        StringBuilder sb = new StringBuilder(s.toLowerCase().replaceAll("[^a-z0-9]", ""));
        return sb.toString().equals(sb.reverse().toString());
    }
}

class SolutionCopy1 {
    //在原字符串上直接判断
    //双指针
    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }
}