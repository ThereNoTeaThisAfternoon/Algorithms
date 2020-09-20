package 二叉树右视图;
/**
 * FileName: BinaryTreeRightSideView.java
 * 类的详细说明
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * @label Tree Depth-firstSearch Breadth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.22
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BinaryTreeRightSideView {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            List<Integer> ret = new Solution().rightSideView(root);
            String out = new PublicMethod().integerArrayListToString(ret,ret.size());
            System.out.println(out);
        }
    }
}

class Solution {
    //BFS
    //利用广度优先搜索进行层序遍历，记录下每层最后一个元素
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();//定义队列，将根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//当前队列大小，即当前层的节点个数
            for (int i = 0; i < size; i++) {//遍历当前层所有节点
                TreeNode node = queue.poll();
                //将当前层所有左右孩子入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                //将当前层的最后一个节点放入结果列表
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}

class SolutionCopy1 {
    //DFS
    //按照[根节点->右子树->左子树]的顺序访问，就可以保证每层都是最先访问最右边的节点
    //与先序遍历[根左右]相反。
    List<Integer> res = new ArrayList<>();//定义结果列表

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);//从根节点开始访问，根节点深度为0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;
        //先访问当前节点，在递归访问右子树和左子树
        if (depth == res.size()) {
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}