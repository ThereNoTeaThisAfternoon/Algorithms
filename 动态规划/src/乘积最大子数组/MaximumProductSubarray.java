package 乘积最大子数组;
/**
 * FileName: MaximumProductSubarray.java
 * 类的详细说明
 * 给定一个整数数组 nums ，请找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * @label DynamicProgramming Array
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.18
 * @version 1.00
 */

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximumProductSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个数组：[1,-2,-3,-2,4]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int ret = new SolutionCopy().maxProduct(nums);
            String out = String.valueOf(ret);
            System.out.println("最大子数组乘积为: " + out);
        }
    }
}

class Solution {
    //枚举法
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len < 1)
            return 0;
        int maxSum = 0x80000000;

        for (int i = 0; i < len; i++) {
            int curSum = 1;
            for (int j = i; j < len; j++) {
                curSum *= nums[j];
                maxSum = Math.max(curSum, maxSum);
            }
        }
        return maxSum;
    }
}

class SolutionCopy {
    //DP
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int preMax = nums[0];
        int preMin = nums[0];

        // 滚动变量
        int curMax;
        int curMin;

        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                curMax = Math.max(preMax * nums[i], nums[i]);
                curMin = Math.min(preMin * nums[i], nums[i]);
            } else {
                curMax = Math.max(preMin * nums[i], nums[i]);
                curMin = Math.min(preMax * nums[i], nums[i]);
            }
            res = Math.max(res, curMax);
            // 赋值滚动变量
            preMax = curMax;
            preMin = curMin;
        }
        return res;
    }
}