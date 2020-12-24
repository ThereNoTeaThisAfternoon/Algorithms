package 分发糖果;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: Candy.java
 * 类的详细说明
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * |--每个孩子至少分配到 1 个糖果。
 * |--相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.24 - 下午 7:56
 * @label GreedyAlgorithm
 */
public class Candy {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组ratings：[1,0,2]");
        while ((line = in.readLine()) != null) {
            int[] ratings = PublicMethod.stringToIntegerArray(line);
            int result = new Solution().candy(ratings);
            System.out.println("老师需要准备" + result + "颗糖果");
        }
    }
}

/**
 * 左规则：当 ratings[i−1]<ratings[i] 时，i 号学生的糖果数量将比 i - 1 号孩子的糖果数量多。
 * 右规则：当 ratings[i]>ratings[i+1] 时，i 号学生的糖果数量将比 i + 1 号孩子的糖果数量多。
 */
class Solution {

    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        for (int i = 0; i < len; i++) {
            if (i > 0 && ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int ans = 0, right = 0;
        for (int i = len - 1; i >= 0; --i) {
            if (i < len - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ans += Math.max(left[i], right);
        }
        return ans;
    }
}