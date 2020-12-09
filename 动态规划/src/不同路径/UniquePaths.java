package 不同路径;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: UniquePaths.java
 * 类的详细说明
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.9 - 下午 1:19
 * @label DynamicProgramming Array
 */
public class UniquePaths {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入m和n：3 3");
        while ((line = in.readLine()) != null) {
            int m = Integer.parseInt(line.trim().split(" ")[0]);
            int n = Integer.parseInt(line.trim().split(" ")[1]);
            int result = new SolutionCopy().uniquePaths(m, n);
            System.out.println("总共有" + result + "条不同的路径");
        }
    }
}

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

class SolutionCopy {

    public int uniquePaths(int m, int n) {
        int[] paths = new int[n];
        for (int i = 0; i < n; ++i) {
            paths[i] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                paths[j] += paths[j - 1];
            }
        }
        return paths[n - 1];
    }
}


class SolutionCopy2 {
    public int uniquePaths(int m, int n) {
        long res = 1;
        for (int x = n, y = 1; y < m; x++, y++) {
            res = res * x / y;
        }
        return (int) res;
    }
}
