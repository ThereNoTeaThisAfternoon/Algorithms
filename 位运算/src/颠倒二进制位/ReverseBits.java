package 颠倒二进制位;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: ReverseBits.java
 * 类的详细说明
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.29 - 下午 9:00
 * @label 输入是一个长度为 32 的二进制字符串
 */
public class ReverseBits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个无符号整数n：00000010100101000001111010011100");
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line, 2);
            int result = new Solution().reverseBits(n);
            System.out.println(result);
        }
    }
}

class Solution {

    public int reverseBits(int n) {
        // Integer.reverse(n);
        int ans = 0;
        int i = 32;
        while (i-- > 0) {
            ans <<= 1;
            ans += n & 1;
            n >>>= 1;
        }
        return ans;
    }
}

class SolutionCopy {
    // 没有无符号整数类型，因此对 n 的右移操作应使用逻辑右移。
    public int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }
}

class SolutionCopy2 {
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }
}
