package 有序数组的平方;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: SquaresOfSortedArray.java
 * 类的详细说明
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.17 - 上午 8:34
 * @label Array TwoPointers
 */
public class SquaresOfASortedArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个有序数组：[-7,-3,2,3,11]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            int[] result = new SolutionCopy().sortedSquares(A);
            System.out.println(Arrays.toString(result));
        }
    }
}

/**
 * 最简单的方法就是将数组 A 中的数平方后直接排序
 */
class Solution {
    public int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = A[i] * A[i];
        }
        Arrays.sort(ans);
        return ans;
    }
}

/**
 * 使用两个指针分别指向位置 0 和 n-1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
 */
class SolutionCopy {
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] ans = new int[A.length];
        for (int l = 0, r = len - 1, pos = len - 1; l <= r; ) {
            if (A[l] * A[l] > A[r] * A[r]) {
                ans[pos] = A[l] * A[l];
                ++l;
            } else {
                ans[pos] = A[r] * A[r];
                --r;
            }
            pos--;
        }
        return ans;
    }
}