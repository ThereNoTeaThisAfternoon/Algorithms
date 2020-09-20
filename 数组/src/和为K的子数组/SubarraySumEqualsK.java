package 和为K的子数组;
/**
 * FileName: SubarraySumEqualsK.java
 * 类的详细说明
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * @label Array HashTable
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.15
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[1,1,1,1]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入子数组应该满足的和：");
            int k = Integer.parseInt(in.readLine());
            int res = new SolutionCopy().subarraySum(nums, k);
            String out = String.valueOf(res);
            System.out.println("满足要求的子数组个数为：" + out);
        }
    }
}

class Solution {
    //枚举法
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        for (int start = 0; start < len; start++) {
            int sum = 0;
            for (int end = start; end >= 0; end--) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }
}

class SolutionCopy {
    //哈希表
    /**
     * 扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
     */
    public int subarraySum(int[] nums, int k) {
        int pre = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k))
                count += map.get(pre - k);
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
