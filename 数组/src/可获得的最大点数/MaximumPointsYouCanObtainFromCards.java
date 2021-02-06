package 可获得的最大点数;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: MaximumPointsYouCanObtainFromCards.java
 * 类的详细说明
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.6 - 下午 3:40
 * @label Array SlidingWindow
 */
public class MaximumPointsYouCanObtainFromCards {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组cardPoints：[1,2,3,4,5,6,1]");
        while ((line = in.readLine()) != null) {
            int[] cardPoints = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入你需要拿的牌数k：3");
            int k = Integer.parseInt(in.readLine());
            int result = new SolutionCopy2().maxScore(cardPoints, k);
            System.out.println("可以获得的最大点数为：" + result);
        }
    }
}

/**
 * 滑动结果集窗口
 */
class Solution {

    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] arr = new int[len + 1];
        for (int i = 0; i < len; ++i) {
            arr[i + 1] = arr[i] + cardPoints[i];
        }
        int maxScore = 0;
        for (int i = 0; i <= k; i++) {
            maxScore = Math.max(maxScore, arr[k - i] + arr[len] - arr[len - i]);
        }
        return maxScore;
    }
}

/**
 * 由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 n−k 张卡牌。
 * 求出剩余中间定长卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
 */
class SolutionCopy {

    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        // 滑动窗口大小为len - k
        int windowSize = len - k;
        // 选取前n个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; i++) {
            sum += cardPoints[i];
        }
        int minSize = sum;
        for (int i = windowSize; i < len; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum = sum + cardPoints[i] - cardPoints[i - windowSize];
            minSize = Math.min(minSize, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSize;
    }

}

/**
 * DFS，不适用于数组长度过大情况下
 */
class SolutionCopy2 {

    public int maxScore(int[] cardPoints, int k) {
        return dfs(cardPoints, 0, cardPoints.length - 1, k);
    }

    private int dfs(int[] cardPoints, int l, int r, int k) {
        if (k == 0) {
            return 0;
        }
        int removeLeft = cardPoints[l] + dfs(cardPoints, l + 1, r, k - 1);
        int removeRight = cardPoints[r] + dfs(cardPoints, l, r - 1, k - 1);
        return Math.max(removeLeft, removeRight);
    }
}