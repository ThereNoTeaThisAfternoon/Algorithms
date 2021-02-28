package 单调数列;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: MonotonicArray.java
 * 类的详细说明
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * An array A is monotone increasing if for all i <= j, A[i] <= A[j]. 
 * An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
 * Return true if and only if the given array A is monotonic.
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.28 - 上午 11:28
 * @label Array
 */
public class MonotonicArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组A：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            boolean result = new SolutionCopy().isMonotonic(A);
            System.out.println((result ? "是" : "") + "单调数组");
        }
    }
}

/**
 * 两次遍历
 */
class Solution {

    public boolean isMonotonic(int[] A) {
        return isSorted(A, true) || isSorted(A, false);
    }

    private boolean isSorted(int[] A, boolean increasing) {
        int len = A.length;
        if (increasing) { // 单调递增
            for (int i = 1; i < len; ++i) {
                if (A[i - 1] > A[i]) {
                    return false;
                }
            }
        } else { // 单调递减
            for (int i = 1; i < len; ++i) {
                if (A[i - 1] < A[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * 一次遍历
 */
class SolutionCopy {

    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len < 2) {
            return true;
        }
        boolean ins = true, des = true;
        for (int i = 1; i < len; ++i) {
            if (A[i - 1] > A[i]) { // 单调递增，出现递减情况
                ins = false;
            }
            if (A[i - 1] < A[i]) { // 单调递减，出现递增情况
                des = false;
            }
        }
        return ins || des;
    }
}