package 最后一块石头的重量;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * FileName: LastStoneWeight.java
 * 类的详细说明
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。
 * 那么粉碎的可能结果如下：
 * |--如果 x == y，那么两块石头都会被完全粉碎；
 * |--如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.30 - 下午 8:18
 * @label GreedyAlgorithm Heap
 */
public class LastStoneWeight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一堆石头stones：[2,7,4,1,8,1]");
        while ((line = in.readLine()) != null) {
            int[] stones = PublicMethod.stringToIntegerArray(line);
            int result = new SolutionCopy2().lastStoneWeight(stones);
            System.out.println("最终的结果为：" + result);
        }
    }
}

/**
 * Greedy，排序，将最大的两块石头放到数组最后面，轮回进行len-1次。
 */
class Solution {

    public int lastStoneWeight(int[] stones) {
        Arrays.sort(stones);
        int len = stones.length;
        for (int i = 1; i < len; ++i) {
            stones[len - 2] = stones[len - 1] - stones[len - 2];
            stones[len - 1] = 0;
            Arrays.sort(stones);
        }
        return stones[len - 1];
    }
}

/**
 * Recursive
 */
class SolutionCopy {

    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        if (stones.length == 2) {
            return Math.abs(stones[0] - stones[1]);
        }
        if (stones[stones.length - 3] == 0) {
            return Math.abs(stones[stones.length - 2] - stones[stones.length - 1]);
        }
        Arrays.sort(stones);
        stones[stones.length - 2] = stones[stones.length - 1] - stones[stones.length - 2];
        stones[stones.length - 1] = 0;
        return lastStoneWeight(stones);
    }
}

/**
 * Heap
 */
class SolutionCopy2 {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int stone : stones) {
            heap.offer(stone);
        }
        while (heap.size() >= 2) {
            int a = heap.poll();
            int b = heap.remove();
            if (a > b) {
                heap.offer(a - b);
            }
        }
        return heap.size() == 1 ? heap.peek() : 0;
    }
}