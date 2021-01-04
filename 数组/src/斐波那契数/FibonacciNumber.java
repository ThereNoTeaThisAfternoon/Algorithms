package 斐波那契数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName:
 * 类的详细说明
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * |--F(0) = 0，F(1) = 1
 * |--F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * 0 <= n <= 30
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.4 - 下午 7:34
 * @label
 */
public class FibonacciNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入n：2");
        while ((line = in.readLine()) != null) {
            int result = new SolutionCopy().fib(Integer.parseInt(line));
            System.out.println("F(n)为：" + result);
        }
    }
}

/**
 * DP
 */
class Solution {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1;
        while (n-- > 1) {
            b = a + (a = b);
        }
        return b;
    }
}

/**
 * Recursive
 */
class SolutionCopy {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}

/**
 * 面向测试用例编程，结果集不是很大，用一个数组足以搞定。
 */
class SolutionCopy1 {

    public int fib(int n) {
        int[] nums = {
                0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040
        };
        return nums[n];
    }
}