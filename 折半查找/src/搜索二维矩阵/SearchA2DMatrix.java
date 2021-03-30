package 搜索二维矩阵;

import Binary公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: SearchA2DMatrix.java
 * 类的详细说明
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.30 - 下午 8:32
 * @label BinarySearch Array
 */
public class SearchA2DMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维数组matrix：[[1,3,5,7],[10,11,16,20],[23,30,34,60]]");
        while ((line = in.readLine()) != null) {
            int[][] matrix = PublicMethod.stringTo2DIntegerArray(line);
            System.out.println("请输入一个待查找的目标值target：3");
            int target = Integer.parseInt(in.readLine());
            boolean result = new SolutionCopy2().searchMatrix(matrix, target);
            System.out.println((result ? "" : "不") + "存在");
        }
    }
}

class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = m - 1; i >= 0; --i) {
            if (matrix[i][0] <= target) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class SolutionCopy {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][0] > target) {
                return false;
            }
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }
}

class SolutionCopy2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cur = matrix[mid / n][mid % n];
            if (cur < target) {
                low = mid + 1;
            } else if (cur > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}