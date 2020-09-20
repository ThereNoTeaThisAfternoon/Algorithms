package 寻找重复数;
/**
 * FileName: FindTheDuplicateNumber.java
 * 类的详细说明
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），len=n+1,val=[1,n]
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * @label Array TowPointer BinarySearch
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.5.26
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class FindTheDuplicateNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy2().findDuplicate(nums);
            String out = String.valueOf(result);
            System.out.println("数组中仅有的重复数为" + out);
        }
    }
}

class Solution {
    /**
     * 快慢指针思想, fast 和 slow 是指针, nums[slow] 表示取指针对应的元素
     * 注意 nums 数组中的数字都是在 1 到 n 之间的(在数组中进行游走不会越界),
     * 因为有重复数字的出现, 所以这个游走必然是成环的, 环的入口就是重复的元素,
     * 即按照寻找链表环入口的思路来做
     **/
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                fast = 0;
                while (nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }
}

class SolutionCopy1 {
    public int findDuplicate(int[] nums) {
        int index1 = 0;
        int index2 = 0;
        // 将其看成是一个循环的链表，快慢指针循环
        do {
            index1 = nums[index1];
            index2 = nums[index2];
            index2 = nums[index2];
        } while (index1 != index2);
        index1 = 0;
        // 找出在哪个位置为起始点，可证必定在圆圈起点相遇
        while (nums[index1] != nums[index2]) {
            index1 = nums[index1];
            index2 = nums[index2];
        }
        return nums[index1];
    }
}

class SolutionCopy2 {
    //看运气法
    public int findDuplicate(int[] nums) {
        int a, b;
        while (true) {
            a = new Random().nextInt(nums.length);
            b = new Random().nextInt(nums.length);
            if (a == b)
                continue;
            if (nums[a] == nums[b])
                return nums[a];
        }
    }
}