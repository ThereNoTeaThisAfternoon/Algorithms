package 监控二叉树;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: BinaryTreeCameras.java
 * 类的详细说明
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.22 - 下午 10:41
 * @label Tree Depth-firstSearch Greedy
 */
public class BinaryTreeCameras {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二叉树：[0,0,null,0,0]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new Solution().minCameraCover(root);
            System.out.println("可以安装的摄像头最小数目为：" + result);
        }
    }
}

class Solution {
    private int camera;

    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (dfs(root) == 2) {
            camera++;
        }
        return camera;
    }

    //0：该节点安装了监控，1：该节点可观，但未安装监控，2：该节点不可观
    private int dfs(TreeNode root) {
        if (root == null) {
            return 1;
        }
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 2 || right == 2) {
            camera++;
            return 0;
        } else if (left == 0 || right == 0) {
            return 1;
        } else {
            return 2;
        }
    }
}