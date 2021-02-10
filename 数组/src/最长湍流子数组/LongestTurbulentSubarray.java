package 最长湍流子数组;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: LongestTurbulentSubarray.java
 * 类的详细说明
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * |--若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * |--或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 * <p>
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.8 - 下午 9:08
 * @label Array SlidingWindow DynamicProgramming
 */
public class LongestTurbulentSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组arr：[9,4,2,10,7,8,8,1,9]");
        while ((line = in.readLine()) != null) {
            int[] arr = new PublicMethod().stringToIntegerArray(line);
            int result = new Solution().maxTurbulenceSize(arr);
            System.out.println("最长湍流子数组长度为：" + result);
        }
    }
}

class Solution {

    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return 1;
        }
        // 当出现up或者down的时候，对应的down或者up要恢复到初始状态
        int res = 0;
        int up = 1, down = 1;
        for (int i = 1; i < len; ++i) {
            if (arr[i - 1] < arr[i]) {
                up = down + (down = 1);
            } else if (arr[i - 1] > arr[i]) {
                down = up + (up = 1);
            } else {
                up = down = 1;
            }
            res = Math.max(res, Math.max(up, down));
        }
        return res;
    }
}

class SolutionCopy {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }
}
