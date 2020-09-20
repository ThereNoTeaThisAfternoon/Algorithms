package 寻找两个正序数组中位数;
/**
 * FileName: MedianOfTwoSortedArrays.java
 * 类的详细说明
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 可以假设 nums1 和 nums2 不会同时为空。
 *
 * @label Array BinarySearch DivideAndConquer
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.24
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = new PublicMethod().stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = new PublicMethod().stringToIntegerArray(line);
            double result = new SolutionCopy().findMedianSortedArrays(nums1, nums2);
            String out = String.valueOf(result);
            System.out.println("中位数为：" + out);
        }
    }
}

class Solution {
    //枚举法
    //复杂度O(m+n)O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0, index2 = 0;
        int[] ans = new int[len1 + len2];
        for (int i = 0; i < ans.length; i++) {
            if (index1 < len1 && index2 < len2) {
                ans[i] = nums1[index1] <= nums2[index2] ? nums1[index1++] : nums2[index2++];
            } else {
                ans[i] = index1 < len1 ? nums1[index1++] : nums2[index2++];
            }
        }
        int middle = ans.length / 2;
        return ans.length % 2 == 0 ? (ans[middle - 1] + ans[middle]) / 2.0 : ans[middle] / 1.0;
    }
}

class SolutionCopy {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 2) / 2;
        int right = (m + n + 1) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    //i：nums1的起始位置，j：nums2的起始位置
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];//nums1为空数组
        if (j >= nums2.length) return nums1[i + k - 1];//nums2为空数组
        if (k == 1)
            return Math.min(nums1[i], nums2[j]);
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}