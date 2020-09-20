package 整数反转;
/**
 * FileName: ReverseInteger.java
 * 类的详细说明
 * 给出一个 32 位的有符号整数，需要将这个整数中每位上的数字进行反转。
 * int 32bits [0x80000000,0x7fffffff]
 * 反转后整数溢出那么就返回 0。
 *
 * @label Math
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.19
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReverseInteger {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);
            int result = new Solution().reverse(num);
            String out = String.valueOf(result);
            System.out.println(out);
        }
    }
}

class Solution {
    public int reverse(int num) {
        long n = 0;
        while (num != 0) {
            n = n * 10 + num % 10;
            num /= 10;
        }
        return (int) n == n ? (int) n : 0;
    }
}