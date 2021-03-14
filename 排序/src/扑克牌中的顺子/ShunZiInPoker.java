package 扑克牌中的顺子;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * FileName: ShunZiInPoker
 * 类的详细说明
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * limit：
 * 数组长度为 5
 * 数组的数取值为 [0, 13] .
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.14 - 下午 3:31
 * @label Sort
 */
public class ShunZiInPoker {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组num：[1,3,5,0,0]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            boolean result = new SolutionCopy().isStraight(nums);
            System.out.println((result ? "" : "不") + "是一个顺子");
        }
    }
}

class Solution {

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;
        for (int i = 0; i < 4; ++i) {
            if (nums[i] == 0) {
                joker += 1;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4] - nums[joker] < 5;
    }
}

class SolutionCopy {

    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, min = 13;
        for (int num : nums) {
            if (num == 0) {
                continue; // 跳过大小王
            }
            min = Math.min(min, num);
            max = Math.max(max, num);
            if (set.contains(num)) {
                return false; // 出现重复牌
            }
            set.add(num);
        }
        return max - min < 5;
    }
}