package 丑数II;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * FileName: UglyNumberII.java
 * 类的详细说明
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 * <p>
 * 1 <= n <= 1690
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.11 - 下午 3:22
 * @label Math DynamicProgramming
 */
public class UglyNumberII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整数n：10");
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            int result = new Solution().nthUglyNumber(n);
            System.out.println("第" + n + "个丑数是：" + result);
        }
    }
}

class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int n2 = 0, n3 = 0, n5 = 0;
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.min(2 * dp[n2], Math.min(3 * dp[n3], 5 * dp[n5]));
            if (dp[i] == 2 * dp[n2]) {
                n2++;
            }
            if (dp[i] == 3 * dp[n3]) {
                n3++;
            }
            if (dp[i] == 5 * dp[n5]) {
                n5++;
            }
        }
        return dp[n - 1];
    }
}

class SolutionCopy {
    // 使用集合，枚举每一个丑数项，再排序
    public int nthUglyNumber(int n) {
        List<Integer> list = new ArrayList<>();
        int max = Integer.MAX_VALUE;
        for (long a = 1; a < max; a *= 2) {
            for (long b = a; b < max; b *= 3) {
                for (long c = b; c < max; c *= 5) {
                    list.add((int) c);
                }
            }
        }
        list.sort(Comparator.comparingInt(a -> a));
        return list.get(n);
    }
}

class SolutionCopy2 {
    // 最小堆
    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }
}
