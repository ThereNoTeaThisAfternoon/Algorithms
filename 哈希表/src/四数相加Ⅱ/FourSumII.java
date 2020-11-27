package 四数相加Ⅱ;

import HashTable公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: FourSumII.java
 * 类的详细说明
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.27 - 下午 2:49
 * @label HashTable BinarySearch
 */
public class FourSumII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请依次输入四个相同长度的整型数组A、B、C、D：\n" +
                "[1,2]\n" +
                "[-2,-1]\n" +
                "[-1,2]\n" +
                "[0,2]");
        while ((line = in.readLine()) != null) {
            int[] A = PublicMethod.stringToIntegerArray(line);
            int[] B = PublicMethod.stringToIntegerArray(in.readLine());
            int[] C = PublicMethod.stringToIntegerArray(in.readLine());
            int[] D = PublicMethod.stringToIntegerArray(in.readLine());
            int count = new SolutionCopy1().fourSumCount(A, B, C, D);
            System.out.println("满足要求的元组个数为：" + count);
        }
    }
}

/**
 * Enum，时间复杂度太高O(n^4)
 */
class Solution {
    // A[i] + B[j] + C[k] + D[l] = 0
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int count = 0;
        for (int i : A) {
            for (int j : B) {
                for (int k : C) {
                    for (int l : D) {
                        if (i + j + k + l == 0) {
                            count += 1;
                        }
                    }
                }
            }
        }
        return count;
    }
}

/**
 * HashTable
 */
class SolutionCopy1 {

    // A[i] + B[j]  = -(C[k] + D[l])
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int count = 0;
        // 遍历 A和B 所有元素组合情况，记录在countMap中，key为两数和，value为该两数和出现次数
        for (int i : A) {
            for (int j : B) {
                countMap.put(i + j, countMap.getOrDefault(i + j, 0) + 1);
            }
        }
        // 遍历 C和D 所有元素组合情况，取和的负值判断是否在countMap中，若存在则取出对应的value值
        for (int k : C) {
            for (int l : D) {
                count += countMap.getOrDefault(-(k + l), 0);
            }
        }
        return count;
    }
}