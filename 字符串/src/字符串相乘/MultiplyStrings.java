package 字符串相乘;
/**
 * FileName: MultiplyStrings.java
 * 类的详细说明
 * 给定两个以字符串形式表示的 非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 *
 * @label String Math
 * @author &#x8c2f;&#x535a;
 * @Date 2020.8.14
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiplyStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入两个以字符串形式表示的非负整数：123 456");
        while ((line = in.readLine()) != null) {
            String num2 = in.readLine();
            String result = new Solution().multiply(line, num2);
            System.out.printf("%s和%s的乘积为%s", line, num2, result);
        }
    }
}

class Solution {
    public String multiply(String num1, String num2) {
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        if (n1 < 0 || n2 < 0)
            return "";
        int[] mul = new int[n1 + n2 + 2];

        for (int i = n1; i >= 0; --i) {
            for (int j = n2; j >= 0; --j) {
                int bitMul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                bitMul += mul[i + j + 1];//先加低位判断是否有新进的值
                mul[i + j] += bitMul / 10;
                mul[i + j + 1] = bitMul % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;//去除前导零
        while (index < mul.length - 1 && mul[index] == 0) {
            index++;
        }
        for (; index < mul.length; index++) {
            sb.append(mul[index]);
        }
        return sb.toString();
    }
}