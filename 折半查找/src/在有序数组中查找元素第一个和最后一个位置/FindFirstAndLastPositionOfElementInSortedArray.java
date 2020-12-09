package 在有序数组中查找元素第一个和最后一个位置;

import Binary公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: FindFirstAndLastPositionOfElementInSortedArray.java
 * 类的详细说明
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.1 - 下午 9:12
 * @label BinarySearch
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个有序整型数组nums：[5,7,7,8,8,10]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入目标值target：8");
            int target = Integer.parseInt(in.readLine());
            int[] result = new SolutionCopy().searchRange(nums, target);
            System.out.println(Arrays.toString(result));
        }
    }
}

class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 二分查找到该目标值，再向左向右延申查找该目标值开始位置和结束位置
        int left = 0, right = nums.length - 1;
        int[] ans = new int[]{-1, -1};
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                findStartEnd(nums, mid, ans);
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private void findStartEnd(int[] nums, int mid, int[] ans) {
        int tmp = mid;
        // 向左延申查找开始位置
        while (tmp > 0 && nums[tmp - 1] == nums[tmp]) {
            tmp -= 1;
        }
        ans[0] = tmp;
        tmp = mid;
        // 向右延申查找结束位置
        while (tmp < nums.length - 1 && nums[tmp] == nums[tmp + 1]) {
            tmp += 1;
        }
        ans[1] = tmp;
    }

}

class SolutionCopy {

    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
