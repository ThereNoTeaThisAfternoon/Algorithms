package 数组中最长山脉;

import Pointer公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: LongestMountainInArray.java
 * 类的详细说明
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * 如果不含有 “山脉” 则返回 0。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.25 - 上午 11:04
 * @label TwoPointers
 */
public class LongestMountainInArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[2,1,4,7,3,2,5]");
        while ((line = in.readLine()) != null) {
            int[] A = PublicMethod.stringToIntegerArray(line);
            int result = new SolutionCopy().longestMountain(A);
            System.out.println("数组中最大山脉的长度为：" + result);
        }
    }
}

/**
 * 先找到比左右两侧大的数(山顶) 然后以这个数为中心 依次找到左右的长度
 */
class Solution {
    public int longestMountain(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int maxLen = 0;
        for (int i = 1; i < A.length - 1; i++) {
            // 寻找山顶
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                int l = i - 1;
                int r = i + 1;
                // 找到左边山脚
                while (0 < l && A[l - 1] < A[l]) {
                    l--;
                }
                // 找到右边山脚
                while (r < A.length - 1 && A[r] > A[r + 1]) {
                    r++;
                }
                maxLen = Math.max(maxLen, r - l + 1);
            }
        }
        return maxLen;
    }
}

/**
 * 枚举山脚，从左向右遍历整个数组 AA 时，可以使用双指针的方法，
 * 一个指针枚举左侧山脚，另一个指针不断向右移动到右侧山脚。
 */
class SolutionCopy {
    public int longestMountain(int[] A) {
        int maxLen = 0;
        int l = 0;
        int n = A.length;
        // 区间长度至少为3，才可能存在山
        while (l + 2 < n) {
            int r = l + 1;
            // l找到未爬的山的山脚
            if (A[l] < A[l + 1]) {
                // r去山顶
                while (r < n - 1 && A[r] < A[r + 1]) {
                    r++;
                }
                // 检查，是否能下山
                if (r < n - 1 && A[r] > A[r + 1]) {
                    // r去山脚
                    while (r < n - 1 && A[r] > A[r + 1]) {
                        r++;
                    }
                    maxLen = Math.max(maxLen, r - l + 1);
                } else {
                    r++;
                }
            }
            // l直接到该山的山脚，继续去寻找下一座山
            l = r;
        }
        return maxLen;
    }
}