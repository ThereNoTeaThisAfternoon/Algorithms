package 笨阶乘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * FileName: ClumsyFactorial.java
 * 类的详细说明
 * 通常，正整数 n 的阶乘是所有小于或等于 n 的正整数的乘积。
 * 例如，factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1。
 * 相反，我们设计了一个笨阶乘 clumsy：在整数的递减序列中，
 * 我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。
 * 例如，clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1。
 * 然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，
 * \并且按从左到右处理乘法和除法步骤。
 * 另外，我们使用的除法是地板除法（floor division），所以 10 * 9 / 8 等于 11。这保证结果是一个整数。
 * 实现上面定义的笨函数：给定一个整数 N，它返回 N 的笨阶乘。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.1 - 下午 8:17
 * @label Math
 */
public class ClumsyFactorial {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整数N：10");
        while ((line = in.readLine()) != null) {
            int[] sums_100 = new int[100];
            for (int i = 0; i < sums_100.length; ++i) {
                sums_100[i] = new Solution().clumsy(i);
            }
            int result = new Solution().clumsy(Integer.parseInt(line));
            System.out.println("笨阶乘的结果为：" + result);
            System.out.println(Arrays.toString(sums_100));
        }
    }
}

class Solution {

    public int clumsy(int N) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(N);
        N--;
        int index = 0; // 用于控制乘除加减
        while (N > 0) {
            if (index % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (index % 4 == 1) {
                stack.push(stack.pop() / N);
            } else if (index % 4 == 2) {
                stack.push(N);
            } else {
                stack.push(-N);
            }
            index++;
            N--;
        }
        // 把栈中所有的数字依次弹出求和
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.poll();
        }
        return sum;
    }
}

class SolutionCopy {

    public int clumsy(int N) {
        if (N == 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N == 3) {
            return 6;
        } else if (N == 4) {
            return 7;
        }
        if (N % 4 == 0) {
            return N + 1;
        } else if (N % 4 == 1) {
            return N + 2;
        } else {
            return N - 1;
        }
    }
}