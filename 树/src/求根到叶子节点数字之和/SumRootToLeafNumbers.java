package 求根到叶子节点数字之和;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * FileName: SumRootToLeafNumbers.java
 * 类的详细说明
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * 计算从根到叶子节点生成的所有数字之和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.29 - 下午 4:33
 * @label Tree Depth-firstSearch Breadth-firstSearch
 */
public class SumRootToLeafNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗二叉树：[4,2,5,1,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new Solution().sumNumbers(root);
            System.out.println("从根到叶子节点生成的所有数字之和为：" + result);
        }
    }
}

/**
 * DFS
 */
class Solution {

    private int res;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            res += sum;
            return;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
}

class SolutionCopy {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}

/**
 * BFS
 */
class SolutionCopy2 {
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();
            TreeNode left = node.left, right = node.right;
            if (left == null && right == null) {
                sum += num;
            } else {
                if (left != null) {
                    nodeQueue.offer(left);
                    numQueue.offer(num * 10 + left.val);
                }
                if (right != null) {
                    nodeQueue.offer(right);
                    numQueue.offer(num * 10 + right.val);
                }
            }
        }
        return sum;
    }
}