package 两个数组的交集;

import Pointer公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * FileName: IntersectionOfTwoArrays.java
 * 类的详细说明
 * Given two arrays, write a function to compute their intersection.
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.2 - 下午 1:01
 * @label TwoPointers Sort HashTable
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums1：[1,2,2,1]");
        while ((line = in.readLine()) != null) {
            int[] nums1 = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入一个整型数组nums2：[2,2]");
            int[] nums2 = PublicMethod.stringToIntegerArray(in.readLine());
            int[] result = new SolutionCopy2().intersection(nums1, nums2);
            System.out.println(Arrays.toString(result));
        }
    }
}

/**
 * TwoPointers
 */
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();

        // 把交集放入list中
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                // next
                i = next(i, nums1);
                j = next(j, nums2);
            } else if (nums1[i] < nums2[j]) {
                // i -> next
                i = next(i, nums1);
            } else {
                // j -> next
                j = next(j, nums2);
            }
        }
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); ++k) {
            res[k] = list.get(k);
        }
        return res;
    }

    // 找到下一个不同的值
    private int next(int x, int[] array) {
        while (x + 1 < array.length) {
            if (array[x] != array[x + 1]) {
                return x + 1;
            }
            x = x + 1;
        }
        return array.length;
    }

}

/**
 * HashTable
 */
class SolutionCopy {
    // 集合去重，获取交集
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }
}

/**
 * StreamAPI
 */
class SolutionCopy2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(set1::contains).toArray();
    }

}