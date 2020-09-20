package 数组中数字出现次数;
/**
 * FileName: NumbersAppearInArray.java
 * 类的详细说明
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * @label Array Search
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.28
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NumbersAppearInArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            int[] res = new SolutionCopy2().singleNumbers(nums);
            String out = Arrays.toString(res);
            System.out.println(out);
        }
    }

    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input == null)
            return new int[0];
        String[] strings = input.split(",");
        int[] output = new int[strings.length];
        int index = 0;
        for (String string : strings) {
            output[index++] = Integer.parseInt(string);
        }
        return output;
    }
}

class Solution {
    //暴力破解
    //将可配对的值变成-1
    //复杂度 O(NN)O(1)
    public int[] singleNumbers(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] res = new int[2];
        int index = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[j] == -1) continue;
                if (nums[i] == nums[j]) {
                    nums[i] = -1;
                    nums[j] = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != -1) {
                res[index++] = nums[i];
            }
        }
        return res;
    }
}

class SolutionCopy1 {
    //BinarySearch
    //O(N logN)O(l)
    public int[] singleNumbers(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
            min = Math.min(min, num);
            max = Math.max(max, num);
            sum ^= num;
        }
        if (zeroCount == 1) {//如果某个数是零
            return new int[]{sum, 0};
        }
        int lo = min, hi = max;
        while (lo <= hi) {
            int mid = lo < 0 ? lo + hi >> 1 : lo + (hi - lo) / 2;// 根据 lo 的正负性来判断二分位置怎么写，防止越界。
            int loSum = 0, hiSum = 0;
            for (int num : nums) {
                if (num <= mid)
                    loSum ^= num;
                else
                    hiSum ^= num;
            }
            if (loSum != 0 && hiSum != 0)// 两个都不为0，说明 p 和 q 分别落到2个数组里了。
                return new int[]{loSum, hiSum};
            if (loSum == 0)// 说明 p 和 q 都比 mid 大，所以比 mid 小的数的异或和变为0了。
                lo = mid;
            else// 说明 p 和 q 都不超过 mid
                hi = mid;
        }
        return null;
    }
}

class SolutionCopy2 {
    //位运算
    //全部异或求得key = target1^target2, key&var == 0 ,那么一定有target1 & var == 0,target2 & var == 0
    //复杂度O(N)O(1)
    public int[] singleNumbers(int[] nums) {
        int key = 0;
        for (int num : nums) {
            key ^= num;
        }
        int mask = 1;//获得k中最低位的1
        while ((key & mask) == 0) {
            mask <<= 1;
        }
        int target1 = 0, target2 = 0;
        for (int num : nums) {
            if ((num & mask) == 0)
                target1 ^= num;
            else
                target2 ^= num;
        }

        return new int[]{target1, target2};
    }
}