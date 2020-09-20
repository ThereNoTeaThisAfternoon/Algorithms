package 鸡蛋掉落;

/**
 * FileName: SuperEggDrop.java
 * 类的详细说明
 * 获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，就不能再把它掉下去。
 * 知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，可以取一个鸡蛋（如果有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，确定 F 的值的最小移动次数是多少？
 *
 * @label Math BinarySearch DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.11
 * @version 1.00
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SuperEggDrop {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int K = Integer.parseInt(line);
            line = in.readLine();
            int N = Integer.parseInt(line);

            int ret = new Solution().superEggDrop(K, N);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
class Solution{
    /**
     * MethodName:
     * 方法的详细说明
     * dp[k][m] 的含义是k个鸡蛋 移动m次最多能够确定多少楼层
     * 这个角度思考
     * dp[k][m] 最多能够确定的楼层数为L
     * 那么我选定第一个扔的楼层之后，我要么碎，要么不碎
     * 这就是把L分成3段
     * 右边是没碎的那段 长度是dp[k][m - 1]
     * 左边是碎的那段 长度是dp[k-1][m - 1]
     * 中间是我选定扔的楼层 是1
     * 所以递推公式是
     * dp[k][m] = dp[k - 1][m - 1] + dp[k][m - 1] + 1
     *时间复杂度：O(K∗N)。
     * 空间复杂度：O(N)。每一层的解只依赖于上一层的解，因此每次只保留一层的解
     *
     * @param K egg
     * @param N floor
     * @return 返回鸡蛋不破的楼层数
     * @throws
     */
    public int superEggDrop(int K,int N){
        int[][] dp = new int[K+1][N+1];
        for(int n=1;n<=N;n++){
            dp[0][n] = 0;
            for(int k=1;k<=K;k++){
                dp[k][n] = dp[k][n-1]+dp[k-1][n-1]+1;
                if(dp[k][n] >= N){
                    return n;
                }
            }
        }
        return N;
    }
}
class SolutionCopy1 {
    /**
     * 时间复杂度：O(K * N \log N)O(K∗NlogN)。需要计算 O(K * N)O(K∗N) 个状态，每个状态计算时需要 O(\log N)O(logN) 的时间进行二分搜索。
     * 空间复杂度：O(K * N)O(K∗N)。需要 O(K * N)O(K∗N) 的空间存储每个状态的解。
     */
    Map<Integer, Integer> memo = new HashMap();
    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    public int dp(int K, int N) {
        if (!memo.containsKey(N * 100 + K)) {
            int ans;
            if (N == 0)
                ans = 0;
            else if (K == 1)
                ans = N;
            else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(K-1, x-1);
                    int t2 = dp(K, N-x);

                    if (t1 < t2)
                        lo = x;
                    else if (t1 > t2)
                        hi = x;
                    else
                        lo = hi = x;
                }
                ans = 1 + Math.min(Math.max(dp(K-1, lo-1), dp(K, N-lo)),
                        Math.max(dp(K-1, hi-1), dp(K, N-hi)));
            }
            memo.put(N * 100 + K, ans);
        }
        return memo.get(N * 100 + K);
    }
}
class SolutionCopy2 {
    /**
     * 决策单调性,是在最优化dp中的可能出现的一种性质,利用它我们可以降低转移的复杂度。
     * 首先dp中会有转移,每个状态都由若干个状态转移而来,最优化dp比较特殊,只能由一个最优的状态转移而来。
     * 我们称之为某个状态的最优转移点。 决策单调性,就是指状态的最优转移点随着dp顺序单调右移/左移等等。
     */
    public int superEggDrop(int K, int N) {
        // Right now, dp[i] represents dp(1, i)
        int[] dp = new int[N+1];
        for (int i = 0; i <= N; ++i)
            dp[i] = i;

        for (int k = 2; k <= K; ++k) {
            // Now, we will develop dp2[i] = dp(k, i)
            int[] dp2 = new int[N+1];
            int x = 1;
            for (int n = 1; n <= N; ++n) {
                // Let's find dp2[n] = dp(k, n)
                // Increase our optimal x while we can make our answer better.
                // Notice max(dp[x-1], dp2[n-x]) > max(dp[x], dp2[n-x-1])
                // is simply max(T1(x-1), T2(x-1)) > max(T1(x), T2(x)).
                while (x < n && Math.max(dp[x-1], dp2[n-x]) > Math.max(dp[x], dp2[n-x-1]))
                    x++;

                // The final answer happens at this x.
                dp2[n] = 1 + Math.max(dp[x-1], dp2[n-x]);
            }

            dp = dp2;
        }

        return dp[N];
    }
}