package 买卖股票的最佳时机;

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: BestTimeToBuyAndSellStock.java
 * 类的详细说明
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.28 - 下午 6:49
 * @label DynamicProgramming
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一只股票的价格表prices：[7,1,5,3,6,4]");
        while ((line = in.readLine()) != null) {
            int[] prices = new PublicMethod().stringToIntegerArray(line);
            int result = new SolutionCopy().maxProfit(prices);
            System.out.println("买卖一次股票能获取的最大利润为：" + result);
        }
    }
}

/**
 * Enum
 */
class Solution {

    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }
}

/**
 * DynamicProgramming
 */
class SolutionCopy {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int max = 0, min = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
