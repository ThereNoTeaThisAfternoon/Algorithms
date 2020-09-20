package 计算X的N次幂;
/**
 * FileName: PowXY.java
 * 类的详细说明
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * n是32位有符号整数
 *
 * @label Math BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.11
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowXY {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个双精度浮点数[-100.0,100.0]：2.00000");
        while ((line = in.readLine()) != null) {
            double x = Double.parseDouble(line);
            System.out.println("请输入一个整数[-2^31,2^31-1]：100");
            line = in.readLine();
            int n = Integer.parseInt(line);
            double ret = new SolutionCopy2().pow(x, n);
            String out = Double.toString(ret);
            System.out.println(out);
        }
    }
}

class Solution {
    //暴力破解
    //复杂度 O(N)O(1)
    public double pow(double x, int n) {
        //return Math.pow(x,n);
        if (n == 0 || x == 1)
            return 1;
        long N = n;
        if (n < 0) {
            N = -N;
            x = 1 / x;
        }
        double ans = 1;
        for (long i = 1; i <= N; i++) {
            ans *= x;
        }
        return ans;
    }
}

class SolutionCopy1 {
    //分治法，递归
    //复杂度 O(logN)O(logN)
    public double pow(double x, int n) {
        if (x == 0 || n == 1)
            return 1;
        long N = n;
        return N > 0 ? quickPower(x, N) : 1.0 / quickPower(x, -N);
    }

    private double quickPower(double x, long n) {
        if (n == 0)
            return 1;
        double half = quickPower(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }
}

class SolutionCopy2 {
    //快速幂，迭代
    //0的任何次方都是1，任何数的0次方都是1
    //不为0的数的-n次方，为其倒数的n次方
    public double pow(double x, int n) {
        if (x == 0 || n == 1)
            return 1;
        long N = n;
        return N > 0 ? quickPower(x, N) : 1.0 / quickPower(x, -N);
    }

    private double quickPower(double x, long N) {
        double ans = 1.0;
        //贡献的初始值为x
        double contributeX = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 != 0)
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= contributeX;
            contributeX *= contributeX;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }
}

