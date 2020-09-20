package 课程表;
/**
 * FileName: CourseScheduleII.java
 * 类的详细说明
 * 有向无环图（DAG）
 * 对[[1,0],[2,0],[3,1],[3,2]]进行拓扑排序，[i][1]是先决条件，即[i][1] -> [i][0]
 * 输出[0,1,2,3] 或 [0,2,1,3]
 *
 * @label TopologicalSort Depth-firstSearch Breath-firstSearch Graph
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.17
 * @version 1.00
 */

import Topo公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CourseScheduleII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入课程数numCourses: 4");
        while ((line = in.readLine()) != null) {
            int numCourses = Integer.parseInt(line);
            System.out.println("请输入选修课程拓扑图：[[1,0],[2,0],[3,1],[3,2]]");
            line = in.readLine();
            int[][] prerequisites = new PublicMethod().stringToInteger2DArray(line);
            int[] ret = new Solution().findOrder(numCourses, prerequisites);
            String out = new PublicMethod().integerArrayToString(ret);
            System.out.println(out);
        }
    }
}

class Solution {
    // BFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] input = new int[numCourses];
        int[] res = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        // 统计节点的入度
        for (int[] edge : prerequisites) {
            input[edge[0]]++;
        }
        // 将入度为0的点入队
        for (int i = 0; i < numCourses; i++) {
            if (input[i] == 0) {
                queue.offer(i);
            }
        }
        int idx = 0;
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            res[idx++] = temp;
            // 修改节点入度
            for (int[] edge : prerequisites) {
                if (edge[1] == temp) {
                    input[edge[0]]--;
                    if (input[edge[0]] == 0) {
                        queue.offer(edge[0]);
                    }
                }
            }
        }
        // 出现环了(res中没包括所有点, idx没走完)
        if (idx != numCourses) {
            return new int[]{};
        }
        return res;
    }
}

class SolutionCopy {

    int[] res;
    int[] visited;
    Map<Integer, List<Integer>> edges;
    boolean isValid = true;
    int counter;
    //DFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        res = new int[numCourses];
        visited = new int[numCourses];
        edges = new HashMap<>();
        counter = numCourses - 1;

        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> l = edges.getOrDefault(prerequisites[i][1], new LinkedList<>());
            l.add(prerequisites[i][0]);
            edges.put(prerequisites[i][1], l);
        }

        for (int i = 0; i < numCourses; i++) dfs(i);

        if (!isValid) return new int[0];
        return res;
    }

    void dfs(int i) {
        if (visited[i] == 2) return;

        visited[i] = 1; //访问中
        if (edges.containsKey(i)) {
            List<Integer> l = edges.get(i);
            for (int course : l) {
                if (visited[course] == 1) {
                    isValid = false;
                    return;
                }
                dfs(course);
                if (!isValid) return;
            }
        }
        visited[i] = 2; //访问完成
        res[counter--] = i;
    }
}