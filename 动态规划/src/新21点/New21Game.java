package 新21点;
/**
 * FileName: New21Game.java
 * 类的详细说明
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，
 * 她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.6.3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class New21Game {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int N = Integer.parseInt(line);
            int K = Integer.parseInt(in.readLine());
            int W = Integer.parseInt(in.readLine());
            double result = new Solution().new21Game(N, K, W);
            String out = String.valueOf(result);
            System.out.println("分数不超过N的概率为：" + out);
        }
    }
}

class Solution {

    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N + 1];
        double sum = 0;
        dp[0] = 1;
        if (K > 0)
            sum += 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = sum / W;
            if (i < K)
                sum += dp[i];
            if (i >= W)
                sum -= dp[i - W];
        }
        double ans = 0;
        for (int i = K; i <= N; i++)
            ans += dp[i];
        return ans;
    }
}