package 汇总区间;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: SummaryRanges.java
 * 类的详细说明
 * 给定一个无重复元素的有序整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
 * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 * <p>
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31-1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.10 - 下午 1:13
 * @label Array
 */
public class SummaryRanges {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个一维整型数组nums：[0,1,2,4,5,7]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            List<String> result = new SolutionCopy().summaryRanges(nums);
            System.out.println(Arrays.toString(result.toArray()));
        }
    }
}

/**
 * 每轮循环都获取到一个区间，从而直接对区间进行操作
 */
class Solution {

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int index = 0;
        while (index < nums.length) {
            String str = "" + nums[index];
            int range = 0;
            while (index + range < nums.length - 1 && nums[index + 1 + range] == nums[index] + range + 1) {
                range++;
            }
            if (range > 0) {
                str += "->" + nums[index + range];
            }
            list.add(str);
            index += range + 1;
        }
        return list;
    }
}

class SolutionCopy {

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            // 下一个不在区间范围内，该区间已完结，应把结果添加入集合
            if (!(i + 1 < nums.length && nums[i] == nums[i + 1] - 1)) {
                if (sb.length() > 0) {
                    sb.append("->");
                }
                sb.append(nums[i]);
                list.add(sb.toString());
                sb = new StringBuilder();
            } else if (sb.length() == 0) {// 下一个在区间范围内，且当前是区间头
                sb.append(nums[i]);
            }
            // 下一个在区间范围内
        }
        return list;
    }

}