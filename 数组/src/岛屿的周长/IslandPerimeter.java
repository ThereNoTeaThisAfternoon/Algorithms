package 岛屿的周长;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: IslandPerimeter.java
 * 类的详细说明
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.30 - 下午 8:10
 * @label Array
 */
public class IslandPerimeter {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维数组表示地图：[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]");
        while ((line = in.readLine()) != null) {
            int[][] grid = new PublicMethod().stringTo2DIntegerArray(line);
            int result = new SolutionCopy().islandPerimeter(grid);
            System.out.println("该岛屿的周长为：" + result);
        }
    }
}

/**
 * 遍历二维数组，格子为陆地时，获取陆地四周不为陆地的格子个数
 */
class Solution {

    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // up
                    if (i == 0 || grid[i - 1][j] == 0) {
                        res++;
                    }
                    // right
                    if (j + 1 == grid[0].length || grid[i][j + 1] == 0) {
                        res++;
                    }
                    // down
                    if (i + 1 == grid.length || grid[i + 1][j] == 0) {
                        res++;
                    }
                    // left
                    if (j == 0 || grid[i][j - 1] == 0) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

}

class SolutionCopy {

    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                ans += i == 0 ? grid[i][j] : Math.max(grid[i][j] - grid[i - 1][j], 0);
                ans += j == 0 ? grid[i][j] : Math.max(grid[i][j] - grid[i][j - 1], 0);
                ans += i + 1 == grid.length ? grid[i][j] : Math.max(grid[i][j] - grid[i + 1][j], 0);
                ans += j + 1 == grid[i].length ? grid[i][j] : Math.max(grid[i][j] - grid[i][j + 1], 0);
            }
        }
        return ans;
    }

}

class SolutionCopy2 {

    private int[][] grid;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res += isLake(i - 1, j) + isLake(i, j + 1) + isLake(i + 1, j) + isLake(i, j - 1);
                }
            }
        }
        return res;
    }

    // 检测该位置是否为水域
    private int isLake(int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0) {
            return 1;
        } else {
            return 0;
        }
    }

}

class SolutionCopy3 {

    public int islandPerimeter(int[][] grid) {
        //重点关注前面遍历过得方格，如果之前有相邻方格，就-2;
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rsp = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    rsp += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        rsp -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        rsp -= 2;
                    }
                }
            }
        }
        return rsp;
    }

}

/**
 * 推导出公式 res = 4 * 岛屿格子数量 - 2 * 岛屿格子之间的相邻边
 */
class SolutionCopy4 {

    public int islandPerimeter(int[][] grid) {
        int m, n;
        if (grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0) {
            return 0;
        }

        int count = 0; // 岛屿格子数量
        int edge = 0; // 岛屿格子之间的相邻边
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                count++;
                if (j + 1 < n && grid[i][j + 1] == 1) edge++; // 判断右边是不是 陆地格子
                if (i + 1 < m && grid[i + 1][j] == 1) edge++; // 判断下面是不是 陆地格子
            }
        }

        return 4 * count - 2 * edge;
    }

}