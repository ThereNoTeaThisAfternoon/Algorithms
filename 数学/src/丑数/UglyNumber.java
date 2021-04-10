package 丑数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: UglyNumber.java
 * 类的详细说明
 * Given an integer n, return true if n is an ugly number.
 * Ugly number is a positive number whose prime factors only include 2, 3, and/or 5.
 * <p>
 * -231 <= n <= 231 - 1
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.10 - 下午 12:49
 * @label Math
 */
public class UglyNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整数n：100");
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            boolean result = new SolutionCopy2().isUgly(n);
            System.out.println((result ? "" : "不") + "是丑数");
        }
    }
}

class Solution {
    // 150 = 1 * 2 * 3 * 5 * 5
    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        } else if (n % 2 == 0) {
            return isUgly(n / 2);
        } else if (n % 3 == 0) {
            return isUgly(n / 3);
        } else if (n % 5 == 0) {
            return isUgly(n / 5);
        } else {
            return false;
        }
    }
}

class SolutionCopy {
    // 丑数去除质因数后只剩下1
    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 2 == 0) {
            n >>= 1;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}

class SolutionCopy2 {
    // 改进
    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        for (int i : new int[]{2, 3, 5}) {
            while (n % i == 0) {
                n /= i;
            }
        }
        return n == 1;
    }
}