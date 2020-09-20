package 表示数值的字符串;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: StringOfNumber.java
 * 类的详细说明
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 * @version 1.00
 * @Date 2020.9.2
 * @label Math
 */
public class StringOfNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串：12E-2");
        while ((line = in.readLine()) != null) {
            boolean result = new Solution().isNumber(line);
            System.out.println("该字符串" + (result ? "表示数值" : "不表示数值"));
        }
    }
}

class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();//去除两边空格
        boolean numFlag = false, dotFlag = false, eFlag = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {//判定数字 标记numFlag
                numFlag = true;
            } else if (chars[i] == '.' && !dotFlag && !eFlag) {//判定 . 需要没出现过 . 且没出现过 e|E
                dotFlag = true;
            } else if ((chars[i] == 'e' || chars[i] == 'E') && numFlag && !eFlag) {//判定 e|E 需要没出现 e|E 且出现过数字
                eFlag = true;
                numFlag = false;//为了避免出现 123e
            } else if ((chars[i] == '+' || chars[i] == '-') && (i == 0 || chars[i - 1] == 'e' || chars[i - 1] == 'E')) {//出现+- 只能出现在首位或紧挨e后

            } else {//其他情况非法
                return false;
            }
        }
        return numFlag;
    }
}
