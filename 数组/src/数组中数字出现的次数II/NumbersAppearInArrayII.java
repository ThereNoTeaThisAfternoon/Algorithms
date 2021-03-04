package 数组中数字出现的次数II;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: NumbersAppearInArrayII.java
 * 类的详细说明
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.4 - 下午 7:06
 * @label Array BitOperation
 */
public class NumbersAppearInArrayII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[9,1,7,9,7,9,7]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy().singleNumber(nums);
            System.out.println("数组中只出现一次的数字是：" + result);
        }
    }
}

class Solution {

    public int singleNumber(int[] nums) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            int temp = nums[i];
            for (int j = 0; j < len; ++j) {
                if (j == len - 1) {
                    ans = temp;
                    break;
                }
                if (i == j) {
                    continue;
                }
                if (temp == nums[j]) {
                    break;
                }
            }
        }
        return ans;
    }
}

class SolutionCopy {

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> hash = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            if (entry.getValue() == 1) {
                ans = entry.getKey();
                break;
            }
        }
        return ans;
    }
}

class SolutionCopy2 {
    // 实际上，只需要修改求余数值 m ，即可实现解决：除了一个数字以外，其余数字都出现 m 次的通用问题。
    public int singleNumber(int[] nums) {
        int[] help = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num & (1 << i)) != 0 ? 1 : 0;
            }
        }
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (1 << i) * (help[i] % 3);
        }
        return res;
    }
}

class SolutionCopy3 {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}