package 回文数;
/**
 * FileName: Palindrome.java
 * 类的详细说明
 * 判断一个整数(负数，零，正数）是否是回文数。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @libel Math
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.13
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);
            boolean result = new SolutionCopy4().isPalindrome(x);
            String out = result ? "true" : "false";
            System.out.println(out);
        }
    }
}

class Solution {
    /**
     * MethodName: isPalindrome
     *
     * @param x int
     * @return boolean
     */
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int temp, y = 0;
        int quo = x;
        while (quo != 0) {
            temp = quo % 10;
            y = y * 10 + temp;
            quo /= 10;
        }
        return x == y;
    }
}

class SolutionCopy1 {
    public boolean isPalindrome(int x) {
        return x == reverse(x);
    }

    private int reverse(int x) {
        int max = 0x7fffffff, min = 0;
        int rs = 0;
        for (; x != 0; rs = rs * 10 + x % 10, x /= 10) ;
        return rs < min ? 0 : rs;
    }
}

class SolutionCopy2 {
    public boolean isPalindrome(int x) {
        String reverseStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reverseStr);
    }
}

class SolutionCopy3 {
    /**
     * 通过取整和取余操作获取整数中对应的数字进行比较。
     * 举个例子：1221 这个数字。
     * 通过计算 1221 / 1000， 得首位1
     * 通过计算 1221 % 10， 可得末位 1
     * 进行比较
     * 再将 22 取出来继续比较
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }
}

class SolutionCopy4 {
    /*取出后半段数字进行翻转*/
    public boolean isPalindrome(int x) {
        //末尾是零直接返回false
        if (x < 0 || (x % 10) == 0 && x != 0) return false;
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }
}