package 二进制求和;
/**
 * FileName: AddBinary.java
 * 类的详细说明
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 * @label Math String
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.24
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddBinary {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        System.out.println("请输入一个二进制字符串：1101");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入一个二进制字符串：1101");
            String b = in.readLine();
            String result = new SolutionCopy1().addBinary(line, b);
            System.out.println("输出的结果为" + result);
        }
    }
}

class Solution {
    //先将 aa 和 bb 转化成十进制数，求和后再转化为二进制数。
    //如果字符串超过 33 位，不能转化为 Integer
    //如果字符串超过 65 位，不能转化为 Long
    //如果字符串超过 500000001 位，不能转化为 BigInteger
    //因此，为了适用于长度较大的字符串计算，应该使用更加健壮的算法。
    //O(n+m)
    public String addBinary(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }
}

class SolutionCopy1 {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = n - 1; i >= 0; --i) {
            carry += i < a.length() ? (a.charAt(i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(i) - '0') : 0;
            sb.append((char) (carry % 2 + '0'));
            carry /= 2;
        }
        if (carry > 0)
            sb.append('1');
        return sb.reverse().toString();
    }
}