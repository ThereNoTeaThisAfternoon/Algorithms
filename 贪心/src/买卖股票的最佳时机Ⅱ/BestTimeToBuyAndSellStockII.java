package 买卖股票的最佳时机Ⅱ;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: BestTimeToBuyAndSellStockII.java
 * 类的详细说明
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.8 - 下午 5:27
 * @label GreedyAlgorithm Array DynamicProgramming
 */
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组prices：[7,1,5,3,6,4]");
        while ((line = in.readLine()) != null) {
            int[] prices = PublicMethod.stringToIntegerArray(line);
            int result = new Solution().maxProfit(prices);
            System.out.println("买卖股票能获取的最大利润为：" + result);
        }
    }
}

class Solution {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int maxProfit = 0;

        for (int i, j = 0; j < len - 1; j++) {
            if (prices[j] >= prices[j + 1]) {
                continue;
            }
            i = j;
            while (j + 1 < len && prices[j] <= prices[j + 1]) {
                j++;
            }
            maxProfit += prices[j] - prices[i];
        }
        return maxProfit;
    }
}

/**
 * GreedyAlgorithm
 * 只能用于计算最大利润，计算的过程并不是实际的交易过程。
 */
class SolutionCopy {
    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}

/**
 * DynamicProgramming
 */
class SolutionCopy2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }
}
