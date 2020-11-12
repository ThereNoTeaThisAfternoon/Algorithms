package 按奇偶排序数组Ⅱ;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: SortArrayByParityII.java
 * 类的详细说明
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.12 - 下午 12:26
 * @label Sort Array
 */
public class SortArrayByParityII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个待排序的数组A：[4,2,5,7]");
        while ((line = in.readLine()) != null) {
            int[] A = PublicMethod.stringToIntegerArray(line);
            int[] result = new SolutionCopy().sortArrayByParity(A);
            System.out.println(Arrays.toString(result));
        }
    }
}

/**
 * Iterator
 */
class Solution {

    public int[] sortArrayByParity(int[] A) {
        int a = 0, b = 1;
        int[] ans = new int[A.length];
        for (int num : A) {
            if (num % 2 == 0) {
                ans[a] = num;
                a += 2;
            } else {
                ans[b] = num;
                b += 2;
            }
        }
        return ans;
    }
}

/**
 * 为数组的偶数下标部分和奇数下标部分分别维护指针 i, j。
 * 随后，在每一步中，如果 A[i] 为奇数，则不断地向前移动 j（每次移动两个单位），直到遇见下一个偶数。
 * 此时，可以直接将 A[i] 与 A[j] 交换。我们不断进行这样的过程，最终能够将所有的整数放在正确的位置上。
 */
class SolutionCopy {
    // 把奇数下标上的偶数和偶数下标上的奇数交换
    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        int j = 1;
        for (int i = 0; i < len; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        A[i] = (A[i] + A[j]) - (A[j] = A[i]);
    }
    // ^ (+ -) (* /)
    private void swap1(int[] A, int i, int j) {
        A[i] = A[i] ^ A[j];
        A[j] = A[i] ^ A[j];
        A[i] = A[i] ^ A[j];
    }

    private void swap2(int[] A, int i, int j) {
        A[i] = A[i] + A[j];
        A[j] = A[i] - A[j];
        A[i] = A[i] - A[j];
    }

}