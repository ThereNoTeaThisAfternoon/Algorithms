package 根据身高重建队列;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * FileName: QueueReconstructionByHeight.java
 * 类的详细说明
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，
 * 其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
 * 编写一个算法来重建这个队列。
 * 注意：总人数少于1100人。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.16 - 下午 7:36
 * @label GreedyAlgorithm
 */
public class QueueReconstructionByHeight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型二维数组people：[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]");
        while ((line = in.readLine()) != null) {
            int[][] people = PublicMethod.stringTo2DIntegerArray(line);
            int[][] result = new Solution().reconstructQueue(people);
            String output = PublicMethod.integer2DArrayToString(result);
            System.out.println("重排序后的数组为" + output);
        }
    }
}

class Solution {
    /**
     * 解题思路：先排序再插入
     * 1.排序规则：按照先H高度降序，K个数升序排序
     * 2.遍历排序后的数组，根据K插入到K的位置上
     * <p>
     * 核心思想：高个子先站好位，矮个子插入到K位置上，前面肯定有K个高个子，矮个子再插到前面也满足K的要求
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
        // 再一个一个插入。
        // [7,0]
        // [7,0], [7,1]
        // [7,0], [6,1], [7,1]
        // [5,0], [7,0], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [7,1]
        // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }
        return list.toArray(people);
    }
}