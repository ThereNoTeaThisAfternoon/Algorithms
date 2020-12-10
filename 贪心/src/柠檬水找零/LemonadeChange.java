package 柠檬水找零;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: LemonadeChange.java
 * 类的详细说明
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.10 - 下午 7:36
 * @label Greedy
 */
public class LemonadeChange {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组bills：[5,5,5,10,20]");
        while ((line = in.readLine()) != null) {
            int[] bills = PublicMethod.stringToIntegerArray(line);
            boolean result = new Solution().lemonadeChange(bills);
            System.out.println((result ? "能" : "不能") + "正确找零");
        }
    }
}

class Solution {
    public boolean lemonadeChange(int[] bills) {
        Map<Integer, Integer> wallet = new HashMap<>();
        for (int bill : bills) {
            // 5元直接收，无需找零钱
            if (bill == 5) {
                wallet.put(5, wallet.getOrDefault(5, 0) + 1);
                // 10元查看是否有五元零钱，有则收入10元，找5元零钱，否则返回false
            } else if (bill == 10) {
                if (wallet.getOrDefault(5, 0) > 0) {
                    wallet.put(10, wallet.getOrDefault(10, 0) + 1);
                    wallet.put(5, wallet.get(5) - 1);
                } else {
                    return false;
                }
                // 20元查看是否有15元零钱{10+5，5+5+5}，有则收入20元，找零，否则返回false
            } else {
                // 10 + 5
                if (wallet.getOrDefault(10, 0) > 0 && wallet.getOrDefault(5, 0) > 0) {
                    wallet.put(10, wallet.get(10) - 1);
                    wallet.put(5, wallet.get(5) - 1);
                    wallet.put(20, wallet.getOrDefault(20, 0) + 1);
                    // 5 + 5 + 5
                } else if (wallet.getOrDefault(5, 0) > 2) {
                    wallet.put(5, wallet.get(5) - 3);
                    wallet.put(20, wallet.getOrDefault(20, 0) + 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * 5 美元，由于柠檬水的价格也为 5 美元，因此我们直接收下即可。
 * 10 美元，我们需要找回 5 美元，如果没有 5 美元面值的钞票，则无法正确找零。
 * 20 美元，我们需要找回 15 美元，此时有两种组合方式，一种是一张 10 美元和 5 美元的钞票，
 * 一种是 33 张 55 美元的钞票，如果两种组合方式都没有，则无法正确找零。
 * 当可以正确找零时，两种找零的方式中我们更倾向于第一种，即如果存在 5 美元和 10 美元，我们就按第一种方式找零，
 * 否则按第二种方式找零，因为需要使用 5 美元的找零场景会比需要使用 10 美元的找零场景多，我们需要尽可能保留 5 美元的钞票。
 */
class SolutionCopy {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) { // 收取五美元
                five++;
            } else if (bill == 10) { // 收取十美元，找零五美元
                ten++;
                five--;
            } else if (ten > 0) { // 收取二十美元，找零十+五美元
                ten--;
                five--;
            } else { // 收取二十美元，找零三个五美元
                five -= 3;
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }
}