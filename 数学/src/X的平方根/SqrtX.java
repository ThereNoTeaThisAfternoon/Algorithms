package X的平方根;
/**
 * FileName: SqrtX.java
 * 类的详细说明
 * 实现(int)Sqrt(x)
 *
 * @label Math BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.9
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SqrtX {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);
            int ans = new Solution().mySqrt(x);
            String out = String.valueOf(ans);
            System.out.println(out);
        }
    }
}

class Solution {
    //折半查找
    public int mySqrt(int x) {
        //return (int)Math.sqrt(x);
        //return (int)Math.pow(x,0.5);
        if (x < 2)
            return x;
        int left = 0, right = x, ans = 0x8fffffff;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (middle * middle <= x) {
                ans = middle;
                left = middle + 1;
            } else
                right = middle - 1;
        }
        return ans;
    }
}
