package 最大子序和;
/**
 * FileName: MaximumSubarray.java
 * 类的详细说明
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 *
 * @label Array DivideAndConquer DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.3
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaximumSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums =new PublicMethod().stringToIntegerArray(line);
            int res = new SolutionCopy3().maxSubArray(nums);
            String out = res + "";
            System.out.println(out);
        }
    }
}

class Solution {
    //枚举法
    //复杂度 O(N^3)O(1)
    //找出所有连续子串,返回当前子串数组值的和，两两比较
    public int maxSubArray(int[] nums) {
        int len = nums.length, ans = 0x8fffffff;
        if (nums == null)
            return 0;
        if (len == 1)
            return nums[0];

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int temp = sumSub(nums, i, j);
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }

    private int sumSub(int[] nums, int start, int end) {
        int sum = 0;
        while (start < end) {
            sum += nums[start++];
        }
        return sum;
    }
}

class SolutionCopy1 {
    //贪心
    //复杂度 O(N)O(1)
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }
}

class SolutionCopy2 {
    //DP
    //若前一个元素大于零,则将其加到当前元素上
    //复杂度 O(N)O(1)
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i - 1] > 0)
                nums[i] += nums[i - 1];
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}

class SolutionCopy3 {
    //分治法
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
//  二分搜索
        //先使用遍历把
        // int max = nums[0];
        // for(int i=1;i<nums.length;i++){
        //     if(nums[i] + nums[i-1] > nums[i]){
        //         nums[i] = nums[i] + nums[i-1];
        //     }
        //     max = Math.max(max,nums[i]);
        // }
        // return max;

        return pushUp(nums, 0, nums.length - 1).mMax;
    }

    //[l,r]
    public Node pushUp(int[] nums, int l, int r) {
        if (l == r) return new Node(nums[l], nums[l], nums[l], nums[l]);

        int mid = l + (r - l) / 2;
        Node left = pushUp(nums, l, mid);
        Node right = pushUp(nums, mid + 1, r);

        int lMax = Math.max(left.sum + right.lMax, left.lMax);
        int rMax = Math.max(right.sum + left.rMax, right.rMax);
        int mMax = Math.max(left.sum + right.sum, Math.max(left.rMax + right.lMax, Math.max(left.mMax, right.mMax)));
        int sum = left.sum + right.sum;
        return new Node(lMax, rMax, mMax, sum);
    }

    private static class Node {
        int lMax;// 左侧连续子数组最值
        int rMax;// 右侧
        int mMax;// 最大连续子数组
        int sum;// 区间和

        public Node(int lMax, int rMax, int mMax, int sum) {
            this.lMax = lMax;
            this.rMax = rMax;
            this.mMax = mMax;
            this.sum = sum;
        }
    }
}