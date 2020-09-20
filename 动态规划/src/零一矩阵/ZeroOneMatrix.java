package 零一矩阵;
/**
 * FileName: ZeroOneMatrix.java
 * 类的详细说明
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.16
 * @version 1.00
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ZeroOneMatrix {
    public static void main(String[] args) {
        System.out.println("请输入Matrix行数和列数");
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        Random random = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matrix[i][j] = random.nextInt(2);
            }
        }
        for(int[] item:matrix){
            System.out.println(Arrays.toString(item));
        }
        int[][] ret = new Solution().updateMatrix(matrix);
        String out = int2dArrayToString(ret);
        System.out.println(out);
    }

    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
            sb.append(Arrays.toString(item));
            sb.append(",");
        }
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
}

class Solution {
    /*DP*/
    /**
     * MethodName: updateMatrix
     * 类方法详细说明
     *
     * @param matrix 二维01矩阵
     * @return 找到做最短距离的矩阵
     */
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 第一次遍历，正向遍历，根据相邻左元素和上元素得出当前元素的对应结果
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = row + col;
                    if (i > 0) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
                    }
                }
            }
        }
        // 第二次遍历，反向遍历，根据相邻右元素和下元素及当前元素的结果得出最终结果
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    if (i < row - 1) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                    }
                    if (j < col - 1) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                    }
                }
            }
        }
        return matrix;
    }
}