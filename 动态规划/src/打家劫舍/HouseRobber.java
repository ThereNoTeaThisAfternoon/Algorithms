package 打家劫舍;
/**
 * FileName: HouseRobber.java
 * 类的详细说明
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.5.29
 */

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HouseRobber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[2,7,9,3,1]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy1().rob(nums);
            String out = String.valueOf(result);
            System.out.println("偷窃的最高金额为：" + out);
        }
    }
}

class Solution {
    //dp[]
    //O(N)O(N)
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return len == 0 ? 0 : nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[len - 1];
    }
}

class SolutionCopy1 {
    //dp优化，滚动数组
    //O(N)O(1)
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return len == 0 ? 0 : nums[0];
        int a = 0, b = 0;
        for (int num : nums) {
            int c = Math.max(a + num, b);
            a = b;
            b = c;
        }
        return b;
    }
}
