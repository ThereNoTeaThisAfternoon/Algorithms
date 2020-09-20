package 有序矩阵中第K小的元素;
/**
 * FileName: KthSmallestElementInASortedMatrix.java
 * 类的详细说明
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * @label Heap BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.7.02
 * @version 1.00
 */

import Binary公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型二维矩阵：[[1,5,9],[10,11,13],[12,13,15]]");
        while ((line = in.readLine()) != null) {
            int[][] matrix = PublicMethod.stringTo2DIntegerArray(line);
            System.out.println("请输入矩阵第k小的元素：8");
            int k = Integer.parseInt(in.readLine());
            int result = new Solution().kthSmallest(matrix, k);
            System.out.printf("第%d小的元素为: %d", k, result);
        }
    }
}

class Solution {
    //优先队列
    public int kthSmallest(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[row - 1].length;
        if (k < 1 || row * col <= k)
            return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int[] i : matrix)
            for (int j : i) {
                queue.add(j);
                if (queue.size() > k)
                    queue.poll();
            }
        return queue.peek();
    }
}

class SolutionCopy1 {
    //二分查找
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length - 1;
        int left = matrix[0][0], right = matrix[n][n];
        while (left < right) {
            int middle = left + (right - left) / 2;
            int count = countNoMoreThanMid(matrix, middle, n);
            if (count < k)
                left = middle + 1;
            else
                right = middle;
        }
        return right;
    }

    private int countNoMoreThanMid(int[][] matrix, int mid, int n) {
        int x = n, y = 0, count = 0;
        while (x >= 0 && y <= n) {
            if (matrix[x][y] <= mid) {
                count += x + 1;
                ++y;
            } else
                --x;
        }
        return count;
    }
}

class SolutionCopy2 {
    //暴力法
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        Arrays.sort(sorted);
        return sorted[k - 1];
    }
}

