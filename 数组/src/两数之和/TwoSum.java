package 两数之和;

/**
 * FileName: TwoSum.java
 * 类的详细说明
 * 给定一个整数数组 nums 和一个目标值 target，
 * 在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 不能重复利用这个数组中同样的元素。
 *
 * @label   Array HashTable
 * @author  &#x8c2f;&#x535a;
 * @Date    2020.4.9
 * @version 1.00
 */
import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int[] ret = new Solution().twoSum(nums, target);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}
class Solution{
    /**
     * MethodName: twoSum
     * 类方法详细使用说明
     * 获取数组长度，创建一个map，每次循环获取差值key = target-nums[i]
     * 若key不存在，则map.put(nums[i],i)
     * 如果两数之和不存在，抛出一个异常
     *
     * @param nums 整数数组
     * @param target 目标值
     * @return 数组下标
     * @throws java.lang.IllegalArgumentException: No two sum Solution
     */
    public int[] twoSum(int[] nums,int target){
        int length = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < length;i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp),i};
            }
            map.put(nums[i],i);
        }
     throw new IllegalArgumentException("No two sum Solution");
    }
}
