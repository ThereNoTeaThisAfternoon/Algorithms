package 数组相对排序;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * FileName: RelativeSortArray.java
 * 类的详细说明
 * 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.14 - 下午 12:57
 * @label Sort Array
 */
public class RelativeSortArray {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组arr1：[2,3,1,3,2,4,6,7,9,2,19]");
        while ((line = in.readLine()) != null) {
            int[] arr1 = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个整型数组arr2：[2,1,4,3,9,6]");
            int[] arr2 = new PublicMethod().stringToIntegerArray(in.readLine());
            int[] result = new SolutionCopy().relativeSortArray(arr1, arr2);
            System.out.println("排序后的数组为：" + Arrays.toString(result));
        }
    }
}

/**
 * enum
 */
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1.length < 2) {
            return arr1;
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int cur : arr2) {
            for (int j = 0; j < arr1.length; ++j) {
                if (cur == arr1[j]) {
                    ans[index++] = cur;
                    arr1[j] = Integer.MAX_VALUE;
                }
            }
        }
        Arrays.sort(arr1);
        for (int value : arr1) {
            if (value == Integer.MAX_VALUE) {
                break;
            }
            ans[index++] = value;
        }
        return ans;
    }
}

/**
 * 使用map数组存放arr2中数字的出现个数
 */
class SolutionCopy {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1.length < 2) {
            return arr1;
        }
        int[] ans = new int[arr1.length];
        int[] map = new int[1001];
        // 遍历arr1，统计每个元素的数量
        for (int value : arr1) {
            map[value] += 1;
        }
        // 遍历arr2，处理出现在map中arr2的元素
        int index = 0;
        for (int value : arr2) {
            while (map[value] > 0) {
                ans[index++] = value;
                map[value]--;
            }
        }
        // 遍历map，将arr2中未出现元素依次放入末尾
        for (int i = 0; i < map.length; i++) {
            while (map[i] > 0) {
                ans[index++] = i;
                map[i]--;
            }
        }
        return ans;
    }
}

/**
 * 自定义排序
 */
class SolutionCopy2 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : arr1) {
            list.add(num);
        }
        for (int i = 0; i < arr2.length; i++){
            map.put(arr2[i], i);
        }
        list.sort((x, y) -> {
            if (map.containsKey(x) || map.containsKey(y)){
                return map.getOrDefault(x, 1001) - map.getOrDefault(y, 1001);
            }
            return x - y;
        });
        for (int i = 0; i < arr1.length; i++){
            arr1[i] = list.get(i);
        }
        return arr1;
    }
}
