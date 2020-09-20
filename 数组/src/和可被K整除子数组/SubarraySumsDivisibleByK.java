package 和可被K整除子数组;
/**
 * FileName: SubarraySumsDivisibleByK.java
 * 类的详细说明
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * (subSum%K+K)%K == 0
 *
 * @label Array HashTable
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.5.27
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组：[4,5,0,-2,-3,1]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个整数K：5");
            int K = Integer.parseInt(in.readLine());
            int result = new SolutionCopy1().subarrayDivByK(A, K);
            String out = String.valueOf(result);
            System.out.println("可以被K整除的数位： " + out);
        }
    }
}

class Solution {
    //枚举法
    public int subarrayDivByK(int[] A, int K) {
        int len = A.length;
        int count = 0;
        int sum;
        for (int i = 0; i < len; i++) {
            sum = 0;
            for (int j = i; j < len; j++) {
                sum += A[j];
                if ((sum % K + K) % K == 0)
                    count++;
            }
        }
        return count;
    }
}

class SolutionCopy1 {
    //哈希表+逐一统计
    Map<Integer, Integer> record = new HashMap<>();

    public int subarrayDivByK(int[] A, int K) {
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem : A) {
            sum += elem;
            //注意：当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % K + K) % K;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }
}