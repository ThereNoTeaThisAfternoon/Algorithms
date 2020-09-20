package 只出现一次的数字;
/**
 * FileName: SingleNumber.java
 * 类的详细说明
 * 给定一个 非空 整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * @label BitManipulation HashTable
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.14
 * @version 1.00
 */

import Bit公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SingleNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个数组：[1,2,12,1,2]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            int ret = new SolutionCopy1().singleNumber(nums);
            String out = String.valueOf(ret);
            System.out.println("Single Number : " + out);
        }
    }
}

class Solution {
    //Bit Manipulation 异或 different or
    //复杂度 O(N)O(1)
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}

class SolutionCopy {
    //HashSet
    //遍历数组中的每个数字，如果集合中没有该数字，则将该数字加入集合，
    //如果集合中已经有该数字，则将该数字从集合中删除，最后剩下的数字就是只出现一次的数字。
    //复杂度 O(N)O(N)
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num))
                set.remove(num);
            else
                set.add(num);
        }
        return set.hashCode();
    }
}

class SolutionCopy1 {
    //使用集合存储数组中出现的所有数字，并计算数组中的元素之和。
    // 由于集合保证元素无重复，因此计算集合中的所有元素之和的两倍，即为每个元素出现两次的情况下的元素之和。
    // 由于数组中只有一个元素出现一次，其余元素都出现两次，因此用集合中的元素之和的两倍减去数组中的元素之和，
    // 剩下的数就是数组中只出现一次的数字。
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int a = 0, b = 0;
        for (int num : nums) {
            a += num;
        }
        b = set.hashCode() * 2;
        return b - a;
    }
}


class SolutionCopy2 {
    //HashMap
    //使用哈希表存储每个数字和该数字出现的次数。遍历数组即可得到每个数字出现的次数，
    // 并更新哈希表，最后遍历哈希表，得到只出现一次的数字。
    //复杂度 O(N)O(N)
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }
        for (int num : nums) {
            if (map.get(num) == 1)
                return num;
        }
        return 0;
    }
}
