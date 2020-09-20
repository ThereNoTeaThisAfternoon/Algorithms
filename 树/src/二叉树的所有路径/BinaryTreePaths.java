package 二叉树的所有路径;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * FileName: BinaryTreePaths.java
 * 类的详细说明
 * Given a binary tree , return all root-to-leaf paths
 * Node: a leaf is a node with no children
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.9.4
 * @label Depth-firstSearch Breadth-firstSearch Backtracking
 */
public class BinaryTreePaths {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个层序遍历的二叉树：[1,2,3,null,5]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            List<String> result = new SolutionCopy().binaryTreePaths(root);
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}

class Solution {//深度优先遍历
    private List<String> paths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return paths;
        }
        dfs(root, "" + root.val);
        return paths;
    }

    private void dfs(TreeNode root, String s) {
        if (root.left == null && root.right == null) {
            paths.add(s);
        }
        if (root.left != null) {
            dfs(root.left, s + "->" + root.left.val);
        }
        if (root.right != null) {
            dfs(root.right, s + "->" + root.right.val);
        }
    }
}

class SolutionCopy {//广度优先遍历

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        //队列，节点和路径成对出现，路径就是从根节点到当前节点的路径
        Queue<Object> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root.val + "");
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            String path = (String) queue.poll();
            //如果到叶子节点，说明找到了一条完整路径
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            //右子节点不为空就把右子节点和路径存放到队列中
            if (node.right != null) {
                queue.add(node.right);
                queue.add(path + "->" + node.right.val);
            }
            //左子节点不为空就把左子节点和路径存放到队列中
            if (node.left != null) {
                queue.add(node.left);
                queue.add(path + "->" + node.left.val);
            }
        }
        return res;
    }

}

class SolutionCopy1 { //递归回溯

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        //到达叶子节点，将路径加入集合
        if (root.left == null && root.right == null) {
            paths.add(root.val + "");
            return paths;
        }
        //遍历左树节点
        for (String path : binaryTreePaths(root.left)) {
            paths.add(root.val + "->" + path);
        }
        //遍历右树节点
        for (String path : binaryTreePaths(root.right)) {
            paths.add(root.val + "->" + path);
        }
        return paths;
    }
}
