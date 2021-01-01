package 种花问题;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: CanPlaceFlowers.java
 * 类的详细说明
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。
 * 可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * <p>
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.1 - 下午 2:25
 * @label Greedy Array
 */
public class CanPlaceFlowers {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个一维数组表示花坛flowerbed：[1,0,0,0,1,0,0]");
        while ((line = in.readLine()) != null) {
            int[] flowerbed = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入需要种植的花的数量n：2");
            int n = Integer.parseInt(in.readLine());
            boolean result = new Solution().canPlaceFlowers(flowerbed, n);
            System.out.println((result ? "" : "不") + "能种" + n + "朵花");
        }
    }
}

/**
 * 防御式编程思想：在 flowerbed 数组两端各增加一个 0，这样处理的好处在于不用考虑边界条件，
 * 任意位置处只要连续出现三个 0 就可以栽上一棵花。
 */
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int[] lands = new int[len + 2];
        System.arraycopy(flowerbed, 0, lands, 1, len);
        // 若该土地上没有花，且相邻两边没有种花，那么就在该地上种花。
        for (int i = 1; i < lands.length - 1; ++i) {
            if (lands[i] != 1 && lands[i - 1] == 0 && lands[i + 1] == 0) {
                n--;
                lands[i] = 1; // 种花
            }
            if (n <= 0) {
                return true;
            }
        }
        return false;
    }
}