package 将X减到0的最小操作;

import Pointer公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: MinimumOperationsToReduceXToZero.java
 * 类的详细说明
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，
 * 然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.16 - 上午 8:17
 * @label GreedyAlgorithm TwoPointers
 */
public class MinimumOperationsToReduceXToZero {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[1,1,4,2,3]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入一个整数x：5");
            int x = Integer.parseInt(in.readLine());
            int result = new Solution().minOperations(nums, x);
            System.out.println("result = " + result);
        }
    }
}

class Solution {
    public int minOperations(int[] nums, int x) {
        // 使用滑动窗口找中间最长的片段使得sum(片段)=sum(nums)-x
        int maxPart = -1;
        int sum = Arrays.stream(nums).sum();
        int currentSum = 0;
        int left = 0;
        int right = 0;
        while (left < nums.length) {
            // 如果右边未到尽头，不断先向右探测片段，如果大于目标sum-x则左边移动直到结束
            if (right < nums.length) {
                currentSum += nums[right++];
            }
            while (currentSum > sum - x && left < nums.length) {
                currentSum -= nums[left++];
            }
            if (currentSum == sum - x) {
                maxPart = Math.max(maxPart, right - left);
            }
            if (right == nums.length) {
                left++;
            }
        }
        return maxPart == -1 ? -1 : nums.length - maxPart;
    }
}