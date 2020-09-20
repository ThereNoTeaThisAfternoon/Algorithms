package 形成两个异或相等数组的三元组数目;
/**
 * FileName: NumberOfTriples.java
 * 类的详细说明
 * 从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 * @label BitManipulation Array Math
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.13
 * @version 1.00
 */

import Bit公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberOfTriples {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个正整数数组：[2,3,1,6,7]");
        while ((line = in.readLine()) != null) {
            int[] arr = new PublicMethod().stringToIntegerArray(line);
            int ret = new SolutionCopy().countTriplets(arr);
            String out = Integer.toString(ret);
            System.out.println("形成两个异或相等三元组数目为：" + out);
        }
    }
}

class Solution {
    /**
     * 时间复杂度O(N^2)，空间复杂度O(1)。
     * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1],b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
     * 那么根据位运算的规则， a^b=arr[i]^ arr[i + 1] ^ ... ^ arr[k]，即i到k。
     * 此外，根据位运算，如果a^b==0,那么a==b，这是逆否命题，即反推也成立。
     * 而固定了两端之后，中间的j可以任意取，故有k-i种。每次计算完，如果满足条件，在ans里加上k-i即可。
     */
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int ans = 0;

        for (int i = 0; i < len - 1; i++) {
            int sum = 0;
            for (int k = i; k < len; k++) {
                sum ^= arr[k];
                if (sum == 0 && k > i)
                    ans += k - i;
            }
        }
        return ans;
    }
}

class SolutionCopy {
    //枚举法
    public int countTriplets(int[] arr) {
        int len = arr.length;
        if (len < 2)
            return 0;
        int ans = 0;

        for (int i = 0; i < len - 1; i++) {
            int a = 0;
            for (int j = i; j < len; j++) {
                a ^= arr[j];
                int b = 0;
                for (int k = j + 1; k < len; k++) {
                    b ^= arr[k];
                    if (a == b)
                        ans++;
                }
            }
        }
        return ans;
    }
}
