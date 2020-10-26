package 有多少小于当前数字的数字;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: HowManyNumbersAreSmallerThanCurrentNumber.java
 * 类的详细说明
 * 给一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 对于每个 nums[i] 必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.26 - 下午 7:27
 * @label
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[8,1,2,2,3]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int[] result = new SolutionCopy().smallerNumbersThanCurrent(nums);
            System.out.println(Arrays.toString(result));
        }
    }
}

/**
 * 取出数组中每一个数字，逐一与数组中其他数字比较，每轮循环统计出比当前数小的数的数量
 */
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; ++i) {
            int count = 0; // 计算比数组中当前数字小的数字的数目
            for (int j = 0; j < len; ++j) {
                if (j != i && nums[j] < nums[i]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }
}

class SolutionCopy {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] array = new int[101];
        int[] res = new int[nums.length];
        // 先用array统计nums数组中每个数字的个数，然后遍历array
        for (int num : nums) {
            array[num] += 1;
        }
        // 通过累加计算每个数字前面有几个比它小的数
        for (int i = 1; i < array.length; ++i) {
            array[i] += array[i - 1];
        }
        // 最后直接以nums为索引从arrays中取答案
        for (int i = 0; i < res.length; ++i) {
            res[i] = nums[i] == 0 ? 0 : array[nums[i] - 1];
        }
        return res;
    }
}