package 爬楼梯;
/**
 * FileName: ClimbingStairs.java
 * 类的详细说明
 * 假设正在爬楼梯。需要 n 阶才能到达楼顶。
 * 每次可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个Integer类型的正整数。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.13
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClimbingStairs {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            int result = new SolutionCopy().climbStairs(n);
            String out = String.valueOf(result);
            System.out.printf("爬%d层的楼梯，有%s种爬法\n", n, out);
        }
    }
}

class Solution {
    //DP
    public int climbStairs(int n) {
        if (n < 3)
            return n;
        int a = 1;
        int b = 2;
        for (int i = 3; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}

class SolutionCopy {
    //长度较短的有限集合的解，可直接返回值，自己学习算法最终的目的还是为了更好地解决问题。
    //警醒自己不要沉迷于算法的精妙而忽视实际情况。
    public int climbStairs(int n) {
        if (n < 1)
            throw new IllegalArgumentException("请输入一个正整数！");
        int[] result = new int[]{1, 2, 3, 5, 8, 13, 21, 34, 55, 89
                , 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946
                , 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269
                , 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141
                , 267914296, 433494437, 701408733, 1134903170, 1836311903};
        return result[n - 1];
    }
}