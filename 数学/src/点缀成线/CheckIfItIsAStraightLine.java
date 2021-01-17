package 点缀成线;

import Math公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: CheckIfItAStraightLine.java
 * 类的详细说明
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，
 * 其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * <p>
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.17 - 上午 9:15
 * @label Math Array Geometry
 */
public class CheckIfItIsAStraightLine {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维数组coordinates：[[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]");
        while ((line = in.readLine()) != null) {
            int[][] coordinates = PublicMethod.stringTo2DIntegerArray(line);
            boolean result = new Solution().checkStraightLine(coordinates);
            System.out.println((result ? "" : "不") + "属于同一直线");
        }
    }
}

/**
 * 一般式：Ax+By+C=0（AB≠0）
 * 两点式：(y-y1)/(x-x1)=(y-y2)/(x-x2) 　（直线过定点(x1,y1),(x2,y2)）
 */
class Solution {

    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        if (len < 3) {
            return true;
        }
        for (int i = 1; i < len - 1; ++i) {
            int a = (coordinates[i][1] - coordinates[i - 1][1]) * (coordinates[i + 1][0] - coordinates[i][0]);
            int b = (coordinates[i + 1][1] - coordinates[i][1]) * (coordinates[i][0] - coordinates[i - 1][0]);
            // 斜率不相等则退出
            if (!(a == b)) {
                return false;
            }
        }
        return true;
    }
}

class SolutionCopy {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}
