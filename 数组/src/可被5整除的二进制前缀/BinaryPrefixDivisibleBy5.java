package 可被5整除的二进制前缀;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: BinaryPrefixDivisibleBy5.java
 * 类的详细说明
 * 给定由若干 0 和 1 组成的数组 A。我们定义 N_i：
 * 从 A[0] 到 A[i] 的第 i 个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 * 返回布尔值列表 answer，只有当 N_i 可以被 5 整除时，答案 answer[i] 为 true，否则为 false。
 * <p>
 * 1 <= A.length <= 30000
 * A[i] 为 0 或 1
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.14 - 下午 6:50
 * @label Array
 */
public class BinaryPrefixDivisibleBy5 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个由0和1组成的数组A：[0,1,1]");
        while ((line = in.readLine()) != null) {
            int[] A = new PublicMethod().stringToIntegerArray(line);
            List<Boolean> result = new SolutionCopy2().prefixesDivBy5(A);
            System.out.println(Arrays.toString(result.toArray()));
        }
    }
}

class Solution {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int x = 0;
        for (int i : A) {
            x = ((x << 1) + i) % 5;
            list.add(x == 0);
        }
        return list;
    }
}

/**
 * 可被5整除的数字只跟该数字的最后一位数字（为0或5）有关系，
 * 于是不需要具体的算出二进制前缀对应的十进制整数是多少，
 * 只需每次保留最后一位数字（保留用该数字对10取余的十进制整数的结果）就好，
 * 而下一个二进制前缀对应的十进制整数 = 上一次的结果左移一位（乘以2）的结果
 * + 这次的A[i]（0或者1，正好对应十进制的0或者1）的结果。
 */
class SolutionCopy {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        int pre = 0;
        for (int value : A) {
            pre = pre << 1;
            pre += value;
            pre %= 10;
            list.add(pre % 5 == 0);
        }
        return list;
    }
}

// 编译原理，有限状体自动机 DFA，O(n)
class SolutionCopy2 {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> res = new ArrayList<>();
        int[][] StateSet = new int[][]{
                {0, 1},
                {2, 3},
                {4, 0},
                {1, 2},
                {3, 4}
        };
        int state = 0;
        for (int value : A) {
            state = StateSet[state][value];  //转换后的状态
            if (state == 0) {
                res.add(Boolean.TRUE);
            } else {
                res.add(Boolean.FALSE);
            }
        }
        return res;
    }
}