package 找零;
/**
 * FileName: Coins.java
 * 类的详细说明
 * 给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.23
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Coins {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            int ret = new Solution().waysToChange(n);
            String out = "" + ret;
            System.out.println(out);
        }
    }
}

class Solution {
    /**
     * 动态规划，每次小循环只用一种硬币。
     * 若在一次for循环中处理四种情况(一个for里带四个硬币的处理情况)，每次计算新一项时，由于每次取的硬币是任意的，会出现对于不同的硬币取法，情况重复的现象。
     * 例如：n=15时，res[15] = 1(全1) + res[15 - 5] + res[15 - 10] = 7，但10 + 5和5 + 10是重复的。
     * 每次小循环只用一种硬币可以避免重复，因为每次小循环中选用的硬币是固定的，在没有到对应硬币的循环前，表内记录对应的解必然不包含该硬币。
     * 例如：n=15时，四次：res[15]=0 -> res[15] = 0 -> res[15] = 2 -> res[15] = 6
     * 实际上coins数组升序也不会影响结果。
     */
    private final int mod = 1000000007;
    private final int[] coins = {25, 10, 5, 1};

    public int waysToChange(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                res[i] = (res[i] + res[i - coin]) % mod;
            }
        }
        return res[n];
    }
}

class SolutionCopy {
    //Math
    private final int mod = 1000000007;

    public int waysToChange(int n) {
        int ans = 0;
        for (int i = 0; i * 25 <= n; i++) {
            int rest = n - i * 25;
            int a = rest / 10;
            int b = rest % 10 / 5;
            ans = (int) (ans + (long) (a + 1) * (a + b + 1) % mod) % mod;
        }
        return ans;
    }
}