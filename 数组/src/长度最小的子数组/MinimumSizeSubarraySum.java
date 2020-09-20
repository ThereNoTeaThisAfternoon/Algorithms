package 长度最小的子数组;
/**
 * FileName: MinimumSizeSubarraySum.java
 * 类的详细说明
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，
 * 并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * @label Array TwoPointer BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.28
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MinimumSizeSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入目标值：7");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入一个整型数组：[2,3,1,2,4,3]");
            int[] nums = new PublicMethod().stringToIntegerArray(in.readLine());
            int result = new SolutionCopy1().minSubarrayLen(Integer.parseInt(line), nums);
            System.out.println("长度最小子数组大小为：" + result);
        }
    }
}

class Solution {
    //双指针
    public int minSubarrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int sum = 0;
        int minSize = len + 1;
        for (int i = 0, j = 0; j < nums.length; ++j) {
            sum += nums[j];
            while (i <= j && sum >= s) {
                minSize = Math.min(minSize, j - i + 1);
                sum -= nums[i--];
            }
        }
        return minSize < len + 1 ? minSize : 0;
    }
}

class SolutionCopy1 {
    //枚举
    public int minSubarrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int minSize = len + 1;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                if (sum >= s && (j - i + 1) < minSize)
                    minSize = j - i + 1;
            }
        }
        return minSize < len + 1 ? minSize : 0;
    }
}

class SolutionCopy2 {
    //前缀和 + 二分查找
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}