package 绝对差不超过限制的最长连续子数组;
/**
 * FileName: LongestContinuousSubarray.java
 * 类的详细说明
 * 给一个整数数组 nums ，和一个表示限制的整数 limit，返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 * 1 <= nums.length <= 10^5
 *
 * @label Array SlidingWindow
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.9
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestContinuousSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个正整数数组：[8,2,4,7]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个limit整数：4");
            int limit = Integer.parseInt(in.readLine());
            int ret = new SolutionCopy().longestSubarray(nums, limit);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class Solution {
    //暴力破解
    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length;
        if (len < 1)
            return 0;
        int maxSubLen = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                boolean flag = maxSub(nums, i, j, limit);
                if (flag)
                    maxSubLen = Math.max(maxSubLen, j - i + 1);
            }
        }
        return maxSubLen;
    }

    private boolean maxSub(int[] nums, int left, int right, int limit) {
        while (left <= right) {
            for (int i = left; i <= right; i++) {
                if (Math.abs(nums[left] - nums[i]) > limit)
                    return false;
            }
            left++;
        }
        return true;
    }
}

class SolutionCopy {
    //Siding Window
    public int longestSubarray(int[] nums, int limit) {
        //建立两个优先队列
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int left = 0;
        int right = 0;
        int ans = 0;
        while (left < nums.length && right < nums.length) {
            minQueue.add(nums[right]);
            maxQueue.add(nums[right]);
            if (maxQueue.peek() - minQueue.peek() <= limit) {
                ans = Math.max(ans, right - left + 1);
                right++;
                continue;
            }
            minQueue.remove(nums[left]);
            maxQueue.remove(nums[left]);
            left++;
            right++;
        }
        return ans;
    }
}