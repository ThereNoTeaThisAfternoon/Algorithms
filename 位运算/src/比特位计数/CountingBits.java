package 比特位计数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: CountingBits.java
 * 类的详细说明
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.6 - 上午 10:44
 * @label BitManipulation DynamicProgramming
 */
public class CountingBits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个非负整数num：5");
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);
            int[] result = new SolutionCopy().countBits(num);
            System.out.println(Arrays.toString(result));
        }
    }
}

class Solution {

    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        for (int i = 0; i <= num; ++i) {
            bits[i] = popCount(i);
        }
        return bits;
    }

    private int popCount(int i) {
        int count = 0;
        while (i != 0) {
            i = i & (i - 1);
            count++;
        }
        return count;
    }
}

class SolutionCopy {

    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; ++i) { // 从一开始，零不满足
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}

class SolutionCopy2 {

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);  //注意i&1需要加括号
        }
        return res;
    }
}