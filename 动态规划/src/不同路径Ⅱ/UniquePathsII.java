package 不同路径Ⅱ;

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: UniquePathsII.java
 * 类的详细说明
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * （网格中的障碍物和空位置分别用 1 和 0 来表示。说明：m 和 n 的值均不超过 100。）
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @label DynamicProgramming Array
 * @date 2020.7.06 2020.12.9
 */
public class UniquePathsII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入机器人行走的网格：[[0,0,0],[0,1,0],[0,0,0]]");
        while ((line = in.readLine()) != null) {
            int[][] matrix = new PublicMethod().stringTo2DIntegerArray(line);
            int count = new Solution().uniquePathsWithObstacles(matrix);
            System.out.println(0x8fffffff);
            System.out.println("机器人到达目标地点的路径有：" + count + "条");
        }
    }
}

class Solution {
    //动态规划
    public int uniquePathsWithObstacles(int[][] matrix) {
        if (matrix.length == 0 || matrix[matrix.length - 1].length == 0) {
            return 0;
        }
        int columns = matrix[0].length;
        int[] dp = new int[columns];
        dp[0] = 1;
        for (int[] row : matrix) {
            for (int j = 0; j < columns; j++) {
                if (row[j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[columns - 1];
    }
}