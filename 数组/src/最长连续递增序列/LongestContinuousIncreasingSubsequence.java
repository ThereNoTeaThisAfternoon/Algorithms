package 最长连续递增序列;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: LongestContinuousIncreasingSubsequence.java
 * 类的详细说明
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * <p>
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.24 - 下午 8:05
 * @label Array GreedyAlgorithm
 */
public class LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个一维整型数组nums：[1,3,5,4,7]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy().findLengthOfLCIS(nums);
            System.out.println("最长连续递增子序列长度为：" + result);
        }
    }
}

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int len = 1;
        int begin = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                len = Math.max(len, i - begin + 1);
            } else {
                begin = i;
            }
        }
        return len;
    }
}

class SolutionCopy {

    public int findLengthOfLCIS(int[] nums) {
        int ans = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) {
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }
}
