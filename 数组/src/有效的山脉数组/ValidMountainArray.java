package 有效的山脉数组;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: ValidMountainArray.java
 * 类的详细说明
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 * -| A.length >= 3
 * ---| 在 0 < i < A.length - 1 条件下，存在 i 使得：
 * ---| A[0] < A[1] < ... A[i-1] < A[i]
 * ---| A[i] > A[i+1] > ... > A[A.length - 1]
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.3 - 下午 2:17
 * @label Array
 */
public class ValidMountainArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型一维数组：[0,2,3,4,5,2,1,0]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            boolean result = new Solution().validMountainArray(A);
            System.out.println("该数组" + (result ? "是" : "不是") + "山脉数组");
        }
    }
}

class Solution {
    public boolean validMountainArray(int[] A) {
        int n = A.length;
        int i = 0;
        // 递增扫描
        while (i + 1 < n && A[i] < A[i + 1]) {
            i++;
        }
        // 最高点不能是数组的第一个位置或最后一个位置
        if (i == 0 || i + 1 == n) {
            return false;
        }
        // 递减扫描
        while (i + 1 < n && A[i] > A[i + 1]) {
            i++;
        }
        return i + 1 == n;
    }
}

class SolutionCopy {
    public boolean validMountainArray(int[] A) {
        int n = A.length;
        int left = 0, right = n - 1;
        // 从左边往右边找，一直找到山峰为止
        while (left + 1 < n && A[left] < A[left + 1]) {
            left++;
        }
        // 从右边往左边找，一直找到山峰为止
        while (right > 0 && A[right - 1] > A[right]) {
            right--;
        }
        // 最高点不能是数组的第一个位置或最后一个位置
        // 判断从左边和从右边找的山峰是不是同一个
        return (left > 0 && right < n - 1) && left == right;
    }
}