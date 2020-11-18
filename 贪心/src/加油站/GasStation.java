package 加油站;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: GasStation.java
 * 类的详细说明
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 说明: 
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.18 - 下午 4:38
 * @label GreedyAlgorithm
 */
public class GasStation {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组gas：[1,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            int[] gas = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入一个整型数组cost：[3,4,5,1,2]");
            int[] cost = PublicMethod.stringToIntegerArray(in.readLine());
            int result = new Solution().canCompleteCircuit(gas, cost);
            System.out.println(result);
        }
    }
}

/**
 * 车子能开完全程的条件
 * 车从 i 能开到 i+1
 * 所有加油站的总油量 >= 车子总耗油量
 */
class Solution {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // rest表示油的数目和消耗数目之差
        // run表示剩余油量能否走到下一个加油站
        // start代表出发的加油站
        int rest = 0, run = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            run += (gas[i] - cost[i]);
            rest += (gas[i] - cost[i]);
            if (run < 0) {
                start = i + 1;
                run = 0;
            }
        }
        return rest < 0 ? -1 : start;
    }
}
