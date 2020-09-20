package 切皮萨的方案数;
/**
 * FileName: NumberOfWaysOfCuttingAPizza.java
 * 类的详细说明
 * 给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。
 * 你需要切披萨 k-1 次，得到 k 块披萨并送给别人。
 * 切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。
 * 如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。
 * 在切完最后一刀后，需要把剩下来的一块送给最后一个人。
 * 请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.16
 * @version 1.00
 */

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOfWaysOfCuttingAPizza {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个StringArray表示一个方形pizza：[\"A..\",\"AAA\",\"...\"]");
        while ((line = in.readLine()) != null) {
            String[] pizza = new PublicMethod().stringToStringArray(line);
            System.out.println("请输入k表示pizza切的块数：3");
            line = in.readLine();
            int k = Integer.parseInt(line);
            int ret = new Solution().ways(pizza, k);
            String out = String.valueOf(ret);
            System.out.println("切pizza的方案数为：" + out);
        }
    }
}

class Solution {

    private int rows, cols;
    private int[][][] dp;
    private int[][] appleNum;
    private static final int MOD = 1000000007;

    public int ways(String[] pizza, int k) {
        rows = pizza.length;
        cols = pizza[0].length();
        char[][] matrix = new char[rows][];
        for (int r = 0; r < rows; ++r) {
            matrix[r] = pizza[r].toCharArray();
        }
        dp = new int[k + 1][rows][cols];
        appleNum = new int[rows + 1][cols + 1];
        for (int r = rows - 1; r >= 0; --r) {
            for (int c = cols - 1; c >= 0; --c) {
                // 当前部分的左上角的一块
                appleNum[r][c] += matrix[r][c] == 'A' ? 1 : 0;
                // 横着切，竖着切，中间重合部分
                appleNum[r][c] += appleNum[r][c + 1] + appleNum[r + 1][c] - appleNum[r + 1][c + 1];
            }
        }
        return dp(0, 0, k);
    }

    private int dp(int row, int col, int k) {
        if (k == 1) {
            dp[k][row][col] = appleNum[row][col] > 0 ? 1 : 0;
            return dp[k][row][col];
        }
        if (dp[k][row][col] > 0) {
            return dp[k][row][col];
        }
        // 水平切
        for (int r = row + 1; r < rows; ++r) {
            // 判断切掉的部分（上部分）是否至少有1个苹果
            if (appleNum[row][col] > appleNum[r][col]) {
                dp[k][row][col] += dp(r, col, k - 1);
                dp[k][row][col] %= MOD;
            }
        }
        // 垂直切
        for (int c = col + 1; c < cols; ++c) {
            // 判断切掉的部分（左部分）是否至少有1个苹果
            if (appleNum[row][col] > appleNum[row][c]) {
                dp[k][row][col] += dp(row, c, k - 1);
                dp[k][row][col] %= MOD;
            }
        }
        return dp[k][row][col];
    }
}