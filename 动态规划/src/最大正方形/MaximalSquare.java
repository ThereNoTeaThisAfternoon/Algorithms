package 最大正方形;
/**
 * FileName: MaximalSquare.java
 * 类的详细说明
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.8
 * @version 1.00
 */

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximalSquare {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            char[][] matrix = new PublicMethod().stringToCharacterArray(line);
            int ret = new SolutionCopy().maximalSquare(matrix);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class Solution {
    /**
     * dp[i][j]表示以 i 行 j 列为右下角所能构成的最大正方形边长，递推公式为：
     * dp[i][j] = 1+min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1]);
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return 0;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxEdge = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    maxEdge = Math.max(maxEdge, dp[i][j]);
                }
            }
        }
        return (int) Math.pow(maxEdge, 2);
    }
}

class SolutionCopy {
    //枚举法
    /*遍历矩阵每一个元素，每次遇到1，就将该元素作为正方形左上角*/
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows < 1)
            return 0;
        int maxSide = 0;
        int temp;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    temp = maxEdge(matrix, i, j);
                    maxSide = Math.max(maxSide, temp);
                }
            }
        }
        return (int) Math.pow(maxSide, 2);
    }

    private int maxEdge(char[][] matrix, int i, int j) {
        /*根据左上角的行列确定可能最大的正方形的边长*/
        int currentMaxSide = Math.min(matrix.length - i, matrix[0].length - j);
        int temp = 1;
        for (int k = 1; k < currentMaxSide; k++) {
            if (matrix[i + k][j + k] == '0')
                return temp;
            for (int m = 0; m < currentMaxSide; m++) {
                if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0')
                    return temp;
                if (k == m) {
                    temp++;
                    break;
                }
            }
        }
        return temp;
    }
}