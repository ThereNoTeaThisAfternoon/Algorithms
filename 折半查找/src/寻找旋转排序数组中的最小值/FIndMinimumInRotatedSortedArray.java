package 寻找旋转排序数组中的最小值;

import Binary公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: FindMinimumInRotatedSortedArray.java
 * 类的详细说明
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 3 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 * 请你找出并返回数组中的 最小元素 。
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.8 - 下午 7:17
 * @label BinarySearch Array
 */
public class FIndMinimumInRotatedSortedArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[4,5,6,7,0,1,2]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            int result = new Solution().findMin(nums);
            System.out.println("数组中最小值为：" + result);
        }
    }
}

class Solution {

    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}

class SolutionCopy {
    // 升序排序，在取第一个值
    public int findMin(int[] nums) {
        return Arrays.stream(nums).sorted().toArray()[0];
    }
}

class SolutionCopy2 {
    // Enum
    public int findMin(int[] nums) {
        int ret = Integer.MAX_VALUE;
        for (int num : nums) {
            ret = Math.min(ret, num);
        }
        return ret;
    }
}
