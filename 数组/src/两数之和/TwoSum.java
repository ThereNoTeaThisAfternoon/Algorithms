package 两数之和;


import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: TwoSum.java
 * 类的详细说明
 * 给定一个整数数组 nums 和一个目标值 target，
 * 在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 不能重复利用这个数组中同样的元素。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @label Array HashTable
 * @Date 2020.4.9 2020.10.3
 */
public class TwoSum {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[2,7,11,15]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个目标值target：9");
            int target = Integer.parseInt(in.readLine());
            int[] ret = new Solution().twoSum(nums, target);
            System.out.println(Arrays.toString(ret));
        }
    }
}

/**
 * HashTable
 */
class Solution {
    /**
     * MethodName: twoSum
     * 类方法详细使用说明
     * 获取数组长度，创建一个map，每次循环获取差值key = target-nums[i]
     * 若key不存在，则map.put(nums[i],i)
     * 如果两数之和不存在，抛出一个异常
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return 数组下标
     * @throws java.lang.IllegalArgumentException: No two sum Solution
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum Solution");
    }
}

/**
 * 枚举
 */
class SolutionCopy {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum Solution");
    }
}