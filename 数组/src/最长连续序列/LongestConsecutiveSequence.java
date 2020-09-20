package 最长连续序列;
/**
 * FileName: LongestConsecutiveSequence.java
 * 类的详细说明
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * @label Array UnionFind
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.6
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个未排序整数数组：[100,4,200,1,3,2]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy().longestConsecutive(nums);
            String out = String.valueOf(result);
            System.out.println("最长连续序列长度为：" + out);
        }
    }
}

class Solution {

    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len < 2)
            return len == 0 ? 0 : 1;
        Arrays.sort(nums);
        int maxLen = 0;
        int count = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i] - nums[i - 1] == 1)
                count++;
            else if (nums[i - 1] == nums[i])
                continue;
            else {
                maxLen = Math.max(count, maxLen);
                count = 1;
            }
        }
        maxLen = Math.max(count, maxLen);
        return maxLen;
    }
}

class SolutionCopy {
    Set<Integer> set = new HashSet<>();

    public int longestConsecutive(int[] nums) {
        for (int num : nums)
            set.add(num);
        int longest = 0;
        for (int num : nums) {
            if (set.remove(num)) {
                // 向当前元素的左边搜索,eg: 当前为100, 搜索：99，98，97,...
                int currentLongest = 1;
                int temp = num;
                while (set.remove(temp - 1))
                    temp--;
                currentLongest += num - temp;
                // 向当前元素的右边搜索,eg: 当前为100, 搜索：101，102，103,...
                temp = num;
                while (set.remove(temp + 1))
                    temp++;
                currentLongest += temp - num;
                // 搜索完后更新longest.
                longest = Math.max(longest, currentLongest);
            }
        }
        return longest;
    }
}
