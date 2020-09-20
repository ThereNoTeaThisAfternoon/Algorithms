package 用栈构建数组;
/**
 * FileName: UseStackBuildArray.java
 * 类的详细说明
 * 给你一个目标数组 target 和一个整数 n。每次迭代，需要从  list = {1,2,3..., n} 中依序读取一个数字。
 * 请使用下述操作来构建目标数组 target ：
 * Push：从 list 中读取一个新元素， 并将其推入数组中。
 * Pop：删除数组中的最后一个元素。
 * 如果目标数组构建完成，就停止读取更多元素。
 * 题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
 * 请返回构建目标数组所用的操作序列。
 * 题目数据保证答案是唯一的。
 *
 * @label Math BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.10
 * @version 1.00
 */

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UseStackBuildArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入待构建的目标数组：[1,3,5]");
        while ((line = in.readLine()) != null) {
            int[] target = new PublicMethod().stringToIntegerArray(line);
            line = in.readLine();
            int n = Integer.parseInt(line);
            List<String> ret = new Solution().buildArray(target, n);
            String out = new PublicMethod().stringListToString(ret);
            System.out.println(out);
        }
    }
}

class Solution {

    public List<String> buildArray(int[] target, int n) {
        int len = target.length;
        List<String> ans = new ArrayList<>();
        int temp = 1;
        for (int i = 0; i < len; i++) {
            while (target[i] > temp) {
                ans.add("Push");
                ans.add("Pop");
                temp++;
            }
            if (target[i] == temp) {
                ans.add("Push");
                temp++;
            }
            if (temp > n)
                break;
        }
        return ans;
    }
}