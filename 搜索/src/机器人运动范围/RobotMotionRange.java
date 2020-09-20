package 机器人运动范围;
/**
 * FileName: RobotMotionRange.java
 * 类的详细使用说明
 *
 * @label Depth-fistSearch Breadth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.20
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RobotMotionRange {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line=in.readLine())!=null){
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);
            line = in.readLine();
            int k = Integer.parseInt(line);
            int result = new Solution().movingCount(m,n,k);
            System.out.println(result);
        }
    }
}
class Solution{
    //DFS
    public int movingCount(int m,int n,int k){
        boolean[][] visited = new boolean[m][n];
        return dfs(0,0,m,n,k,visited);
    }
    public int dfs(int i,int j,int m,int n,int k,boolean[][] visited){
        if(i<0||i>=m||j<0||j>=n||(i/10+i%10+j/10+j%10)>k||visited[i][j]){
            return 0;
        }
        visited[i][j] = true;
        return (dfs(i+1,j,m,n,k,visited)+dfs(i,j+1,m,n,k,visited)+1);
    }
}
class SolutionCopy {
    //BFS
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue= new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 0, 0 });
        while(queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if(i >= m || j >= n || k < si + sj || visited[i][j]) continue;
            visited[i][j] = true;
            res ++;
            queue.add(new int[] { i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj });
            queue.add(new int[] { i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8 });
        }
        return res;
    }
}