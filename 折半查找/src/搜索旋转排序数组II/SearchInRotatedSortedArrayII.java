package 搜索旋转排序数组II;

import Binary公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * FileName: SearchInRotatedSortedArrayII.java
 * 类的详细说明
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * <p>
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.7 - 下午 8:42
 * @label Array BinarySearch
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[2,5,6,0,0,1,2]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入一个目标值target：0");
            boolean result = new Solution().search(nums, Integer.parseInt(in.readLine()));
            System.out.println((result ? "" : "不") + "存在");
        }
    }
}

class Solution {
    // BinarySearch
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            // 处理重复数字
            while (l < r && nums[l] == nums[l + 1]) {
                l++;
            }
            while (l < r && nums[r - 1] == nums[r]) {
                r--;
            }
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] <= nums[mid]) { // 左半部分有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}

class SolutionCopy {
    // streamAPI
    public boolean search(int[] nums, int target) {
        return Arrays.stream(nums).boxed().collect(Collectors.toList()).contains(target);
    }
}

class SolutionCopy2 {
    // Enum
    public boolean search(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
}