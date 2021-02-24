package 翻转图像;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: FlippingAnImage.java
 * 类的详细说明
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.24 - 下午 7:07
 * @label Array TwoPointers
 */
public class FlippingAnImage {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维整型数组A：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]");
        while ((line = in.readLine()) != null) {
            int[][] A = PublicMethod.stringTo2DIntegerArray(line);
            int[][] result = new Solution().flipAndInvertImage(A);
            Arrays.stream(result).forEach((e) -> System.out.println(Arrays.toString(e)));
        }
    }
}

class Solution {

    public int[][] flipAndInvertImage(int[][] A) {
        int len = A.length;
        for (int i = 0; i < len; ++i) {
            // 翻转
            for (int j = 0; j < len >> 1; ++j) {
                A[i][j] = A[i][j] + A[i][len - j - 1] - (A[i][len - j - 1] = A[i][j]);
            }
            // 反转
            for (int j = 0; j < len; ++j) {
                A[i][j] ^= 1;
            }
        }
        return A;
    }
}

/**
 * 一次遍历完成翻转反转
 */
class Solution1 {

    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        for (int i = 0; i < row; i++) {
            int l = 0, r = row - 1;
            while (l < r) {
                if (A[i][l] == A[i][r]) {
                    A[i][l] ^= 1;
                    A[i][r] ^= 1;
                }
                l++;
                r--;
            }
            if (l == r) {
                A[i][l] ^= 1;
            }
        }
        return A;
    }
}

/**
 * 使用额外空间完成翻转反转
 */
class SolutionCopy2 {
    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        int[][] ans = new int[row][row];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < row; ++j) {
                ans[i][j] = 1 ^ A[i][row - j - 1];
            }
        }
        return ans;
    }
}

/**
 * 整点花里胡哨的
 */
class SolutionCopy3 {
    public int[][] flipAndInvertImage(int[][] A) {
        int[][] result = new int[A.length][];
        for (int i = 0; i < A.length; i++) {
            String str = new StringBuilder(Arrays.toString(A[i])).reverse().toString();
            str = str.replace("[", "");
            str = str.replace("]", "");
            str = str.replace("0", "2");
            str = str.replace("1", "0");
            str = str.replace("2", "1");
            str = str.replace(" ", "");
            String[] strs = str.split(",");
            result[i] = getNumbs(strs);
        }
        return result;
    }

    private int[] getNumbs(String[] strs) {
        int[] numbs = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            numbs[i] = Integer.parseInt(strs[i]);
        }
        return numbs;
    }

}