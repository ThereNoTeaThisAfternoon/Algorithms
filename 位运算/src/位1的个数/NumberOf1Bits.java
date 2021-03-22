package 位1的个数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: NumberOf1Bits.java
 * 类的详细说明
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，
 * 输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
 * 因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 * <p>
 * 提示：
 * 输入必须是长度为 32 的二进制串。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.22 - 下午 9:00
 * @label BitManipulation
 */
public class NumberOf1Bits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个无符号整数n：1111111111111111111111111111111");
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line, 2);
            int result = new SolutionCopy().hammingWeight(n);
            System.out.println("汉明重量为：" + result);
        }
    }
}

/**
 * n & (n−1)，其预算结果恰为把 n 的二进制位中的最低位的 1 变为 0 之后的结果。
 */
class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}

/**
 * 循环检查
 */
class SolutionCopy {

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            if ((n & 1 << i) != 0) {
                count++;
            }
        }
        return count;
    }
}

class SolutionCopy2 {

    public int hammingWeight(int n) {
        // return Integer.bitCount(n);
        n = n - ((n >>> 1) & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
        n = (n + (n >>> 4)) & 0x0f0f0f0f;
        n = n + (n >>> 8);
        n = n + (n >>> 16);
        return n & 0x3f;
    }
}