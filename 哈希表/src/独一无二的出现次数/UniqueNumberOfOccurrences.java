package 独一无二的出现次数;

import HashTable公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * FileName: UniqueNumberOfOccurrences.java
 * 类的详细说明
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.28 - 下午 10:43
 * @label HashTable
 */
public class UniqueNumberOfOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[1,2,2,1,1,3]");
        while ((line = in.readLine()) != null) {
            int[] arr = PublicMethod.stringToIntegerArray(line);
            boolean result = new Solution().uniqueOccurrences(arr);
            System.out.println(result ? "是" : "不是");
        }
    }
}

class Solution {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        // 获取数组中每个数出现次数
        for (int elem : arr) {
            // map.put(elem, map.getOrDefault(elem, 0) + 1);
            map.merge(elem, 1, Integer::sum);
        }
        /*
        Set<Integer> times = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> x : map.entrySet()) {
            times.add(x.getValue());
        }
        return times.size() == map.size();
        */
        // 比较两个集合大小是否一致
        return map.size() == new HashSet<>(map.values()).size();
    }
}