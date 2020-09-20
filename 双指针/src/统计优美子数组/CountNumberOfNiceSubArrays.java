package 统计优美子数组;
/**
 * FileName: CountNumberOfNiceSubArrays.java
 * 类的详细说明
 * 给一个整数数组 nums 和一个整数 k。
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中「优美子数组」的数目。
 *
 * @label TwoPointer
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.21
 * @version 1.00
 */

import Pointer公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountNumberOfNiceSubArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);
            int ret = new SolutionCopy1().numberOfSubArrays(nums, k);
            String s = String.valueOf(ret);
            System.out.println("最优美子数数量为：" + s);
        }
    }
}

class Solution {
    //超时不用说
    public int numberOfSubArrays(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            nums[i] = (nums[i] & 1) == 1 ? 1 : 0;
        }
        int sum;
        int res = 0;
        for (int i = 0; i < len - k + 1; i++) {
            sum = 0;
            for (int j = i; j < len; j++) {
                if (nums[j] == 1) sum++;
                if (sum == k) {
                    res++;
                }
            }
        }
        return res;
    }
}

class SolutionCopy1 {
    public int numberOfSubArrays(int[] nums, int k) {
        int preEven = 0;
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            if ((i & 1) == 0) {
                preEven++;
            } else {
                list.add(preEven + 1);
                preEven = 0;
            }
        }
        list.add(preEven + 1);
        list.forEach(System.out::println);
        int count = 0;
        for (int i = 0; i < list.size() - k; i++) {
            count += (list.get(i) * list.get(i + k));
        }
        return count;
    }
}

class SolutionCopy2 {
    //Two Pointer// 双指针
    public int numberOfSubArrays(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) return 0;
        int left = 0, right = 0;
        int count = 0; // 连续子数组中奇数的个数
        int res = 0;
        int preEven = 0; // 记录第一个奇数前面的偶数个数
        while (right < nums.length) {
            // 连续子数组中奇数个数不够
            if (count < k) {
                if (nums[right] % 2 != 0) count++;
                right++; // 移动右侧指针
            }
            // 连续子数组中奇数个数够了，看第一个奇数前面有多少个偶数
            if (count == k) {
                preEven = 0;
                while (count == k) {
                    res++;
                    if (nums[left] % 2 != 0) count--;
                    left++;
                    preEven++;
                }
            } else res += preEven; // 每次遇到 right 为偶数的时候就进行累加 相当于区间前面偶数个数 * 后面偶数个数
        }
        return res;
    }
}