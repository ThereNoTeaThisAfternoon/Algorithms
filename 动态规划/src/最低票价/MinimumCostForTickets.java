package 最低票价;
/**
 * FileName: MinimumCostForTickets.java
 * 类的详细说明
 * 在这[1...365]天里，你要进行不定期不定次的火车旅行，
 * 现有日票、周票、月票可供选择，各个售价为costs[0],costs[1],costs[2]
 * 通行证允许数天无限制的旅行。
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 *
 * @label DynamicProgramming
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.6
 * @version 1.00
 */

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumCostForTickets {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入要进行火车旅行的日子，例如：[1,4,6,7,8,20]");
        while ((line = in.readLine()) != null) {
            int[] days = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入火车票日票、周票、月票的价格，例如：[2,7,15]");
            line = in.readLine();
            int[] costs = new PublicMethod().stringToIntegerArray(line);
            int ret = new Solution().minCostTickets(days, costs);
            String out = Integer.toString(ret);
            System.out.println(out);
        }
    }
}

class Solution {

    public int minCostTickets(int[] days, int[] costs) {
        //将[0...365]天花费的钱全部记录下来
        int[] allDayCosts = new int[366];
        //days的下标，确保遍历365天，以便于知道下次旅行日期
        int dayIdx = 0;
        //日票、周票、月票的花费
        int ticketDay = costs[0];
        int ticketWeek = costs[1];
        int ticketMonth = costs[2];
        //从第一天算，过去所有花费为零
        allDayCosts[0] = 0;
        //allDayCosts[i]是截止到第i天总花费
        //模拟从第一天到最后一天
        for (int today = 1; today <= 365; today++) {
            if (dayIdx >= days.length)
                break;
            //如果今天不旅行
            if (today != days[dayIdx]) {
                allDayCosts[today] = allDayCosts[today - 1];
                continue;
            }
            //下一个旅行日到来
            dayIdx++;
            //如果一个月前，买了月票，会不会更便宜
            //如果一个周前，买了周票，会不会更便宜
            //如果都不会，那就买日票
            allDayCosts[today] = Math.min(
                    Math.min(
                            allDayCosts[Math.max(0, today - 1)] + ticketDay,
                            allDayCosts[Math.max(0, today - 7)] + ticketWeek),
                    allDayCosts[Math.max(0, today - 30)] + ticketMonth
            );
        }
        return allDayCosts[days[days.length - 1]];
    }
}


