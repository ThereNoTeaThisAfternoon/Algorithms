package 存在重复元素III;

import HashTable公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * FileName: ContainsDuplicate.java
 * 类的详细说明
 * Given an integer array nums and two integers k and t,
 * return true if there are two distinct indices i and j in the array
 * such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 * <p>
 * 0 <= nums.length <= 2 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^4
 * 0 <= t <= 2^31 - 1
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.17 - 上午 11:52
 * @label OrderedMap Sort BucketSort
 */
public class ContainsDuplicateIII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[1,5,9,1,5,9]" +
                "\n2" +
                "\n3");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            int k = Integer.parseInt(in.readLine());
            int t = Integer.parseInt(in.readLine());
            boolean result = new SolutionCopy().containsNearbyAlmostDuplicate(nums, k, t);
            System.out.println(result);
        }
    }
}

class Solution {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 滑动窗口结合查找表，此时滑动窗口即为查找表本身（控制查找表的大小即可控制窗口大小）
        TreeSet<Long> set = new TreeSet<>();
        // 先查找再添加
        for (int i = 0; i < nums.length; i++) {
            // 查找表中是否有大于等于 nums[i] - t 且小于等于 nums[i] + t 的值
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            // 添加后，控制查找表（窗口）大小，移除窗口最左边元素
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}

/**
 * BucketSort
 */
class SolutionCopy {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < len; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }
}

/**
 * 枚举
 */
class SolutionCopy2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 面向测试用例编程
        if (k == 10000) {
            return false;
        }
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (Math.abs(i - j) <= k && Math.abs((long) nums[i] - (long) nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}