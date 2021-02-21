package 数组的度;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: DegreeOfAnArray.java
 * 类的详细说明
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.21 - 上午 9:36
 * @label Array
 */
public class DegreeOfAnArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个非空只包含非负数的整型数组nums：[1,2,2,3,1,4,2]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new Solution().findShortestSubArray(nums);
            System.out.println("最短连续子数组长度为：" + result);
        }
    }
}

class Solution {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int degree = 0; // 当前遍历到的所有元素的出现频数最大值，众数
        int ans = 0; // 子数组的最小长度
        for (int i = 0; i < nums.length; ++i) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i});
            } else {
                map.get(nums[i])[0]++;
            }
            // map.getOrDefault(nums[i], new int[]{0, i})[0]++;
            int[] arr = map.get(nums[i]);
            int cur_sub_len = i - arr[1] + 1;
            if (degree < arr[0] || degree == arr[0] && cur_sub_len < ans) {
                degree = arr[0];
                ans = cur_sub_len;
            }
        }
        return ans;
    }
}