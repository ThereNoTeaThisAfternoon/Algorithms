package 爱生气的书店老板;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: GrumpyBookstoreOwner.java
 * 类的详细说明
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。
 * 每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.23 - 下午 8:26
 * @label Array SlidingWindow
 */
public class GrumpyBookstoreOwner {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组customers：[1,0,1,2,1,1,7,5]");
        while ((line = in.readLine()) != null) {
            int[] customers = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个整型数组grumpy：[0,1,0,1,0,1,0,1]");
            int[] grumpy = new PublicMethod().stringToIntegerArray(in.readLine());
            System.out.println("请输入老板的技能时间X：3");
            int X = Integer.parseInt(in.readLine());
            int result = new SolutionCopy().maxSatisfied(customers, grumpy, X);
            System.out.println("这一天营业下来，最多有" + result + "位客户感到满意");
        }
    }
}

/**
 * 首先 找到不改变的时候客人就满意的数量和 同时更新数组
 * 这样问题就转变为 求数组指定长度最大和的问题
 */
class Solution {

    public int maxSatisfied(int[] customers, int[] grumpy, int x) {
        int sum = 0, len = customers.length;
        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
                customers[i] = 0;
            }
        }
        int num = customers[0];
        int maxVal = customers[0];
        for (int i = 1; i < len; i++) {
            if (i < x) {
                num = num + customers[i];
            } else {
                num = num + customers[i] - customers[i - x];
            }
            maxVal = Math.max(maxVal, num);
        }
        return (sum + maxVal);
    }
}

class SolutionCopy {

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ret = 0; // 不用技能情况下可获得的满意值
        int extra = 0; // 使用技能后能额外获得最大满意值
        int window = 0;// 存放临时窗口中的满意值
        int len = customers.length;
        int l = 0, r = 0;
        while (r < len) {
            ret += (1 - grumpy[r]) * customers[r]; // 确定满意的顾客
            // 滑动窗口
            window += grumpy[r] * customers[r++];
            extra = Math.max(extra, window);
            if (r - l == X) {
                window -= grumpy[l] * customers[l++];
            }
        }
        return ret + extra;
    }
}