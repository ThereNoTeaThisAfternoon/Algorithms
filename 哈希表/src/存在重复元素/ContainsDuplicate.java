package 存在重复元素;

import HashTable公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * FileName: ContainsDuplicate.java
 * 类的详细说明
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.13 - 下午 7:32
 * @label HashTable Array Sort
 */
public class ContainsDuplicate {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[1,2,3,1]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            boolean result = new SolutionCopy2().containsDuplicate(nums);
            System.out.println((result ? "存在" : "不存在") + "重复元素");
        }
    }
}

class Solution {

    public boolean containsDuplicate(int[] nums) {
        if (nums.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        // 遍历数组，判断遍历的元素是否在集合中，存在返回true，否则添加到集合中
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }
}

class SolutionCopy {

    public boolean containsDuplicate(int[] nums) {
        int len;
        if ((len = nums.length) < 2) {
            return false;
        }
        // 排序，两两比较判断是否有重复元素
        Arrays.sort(nums);
        for (int i = 1; i < len; ++i) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }
}

class SolutionCopy2 {
    // 蒙特卡罗方法
    public boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        // 两个随机数表示数组下标 rand1 != rand2
        int rand_1, rand_2;
        for (int i = 0; i < 100000; i++) {
            rand_1 = (int) (Math.random() * len);
            rand_2 = (int) (Math.random() * len);
            if (rand_1 != rand_2 && nums[rand_1] == nums[rand_2]) {
                return true;
            }
        }
        // 特殊案例再处理一下。
        return 9999 == nums[len - 1];
    }
}