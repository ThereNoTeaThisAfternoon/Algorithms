package 公平的糖果棒交换;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * FileName: FairCandySwap
 * 类的详细说明
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。
 * （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * 保证爱丽丝与鲍勃的糖果总量不同。
 * 答案肯定存在。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.1 - 下午 8:06
 * @label Array
 */
public class FairCandySwap {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入爱丽丝的糖果棒A：[1,1]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入鲍勃的糖果棒B：[2,2]");
            int[] B = new PublicMethod().stringToIntegerArray(in.readLine());
            int[] result = new SolutionCopy().fairCandySwap(A, B);
            System.out.println("必须交换的糖果棒大小为：" + Arrays.toString(result));
        }
    }
}

class Solution {

    public int[] fairCandySwap(int[] A, int[] B) {
        int[] ans = new int[2];
        // 求出爱丽丝和鲍勃所拥有的糖果总和
        int sumA = 0, sumB = 0;
        for (int i : A) {
            sumA += i;
        }
        for (int j : B) {
            sumB += j;
        }
        // 排序，从而使用二分法，避免使用组合
        Arrays.sort(A);
        Arrays.sort(B);
        //
        int temp = (sumA - sumB) / 2;
        for (int i = 0, j = 0; i < A.length && j < B.length; ) {
            if (A[i] - B[j] == temp) {
                ans[0] = A[i];
                ans[1] = B[j];
                break;
            } else if (A[i] - B[j] < temp) {
                i++;
            } else {
                j++;
            }
        }
        return ans;
    }
}

class SolutionCopy {
    // sumA + y - x = sumB + x - y
    // x = y + (sumA - sumB) / 2;
    public int[] fairCandySwap(int[] A, int[] B) {
        int[] ans = new int[2];
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> set = new HashSet<>();
        for (int x : A) {
            set.add(x);
        }
        for (int y : B) {
            int x = y + delta;
            if (set.contains(x)) {
                ans[0] = x;
                ans[1] = x;
                break;
            }
        }
        return ans;
    }

}