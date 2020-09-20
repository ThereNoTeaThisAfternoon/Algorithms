package 把数字翻译成字符串的个数;
/**
 * FileName: TranslateNumbersIntoStrings.java
 * 类的详细说明
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * @label Depth-firstSearch DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.9
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TranslateNumbersIntoStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个数[0<= n <= 2^31]：");
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);
            int result = new SolutionCopy().translateNum(num);
            String out = String.valueOf(result);
            System.out.println("数字的不同种翻译方法有：" + out);
        }
    }
}

class Solution {
    //DFS
    public int translateNum(int num) {
        if (num == 0)
            return 1;
        int part = num % 100;
        if (part <= 9 || 26 <= part)
            return translateNum(num / 10);
        return translateNum(num / 10) + translateNum(num / 100);
    }
}

class SolutionCopy {
    //DP
    public int translateNum(int num) {
        String src = String.valueOf(num);
        int p, q = 0, r = 1;
        for (int i = 0; i < src.length(); i++) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0)
                continue;
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0)
                r += p;
        }
        return r;
    }
}