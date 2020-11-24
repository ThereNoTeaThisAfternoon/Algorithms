package 完全二叉树的节点个数;

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;
import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * FileName: CountCompleteTreeNodes.java
 * 类的详细说明
 * 给出一个完全二叉树，求出该树的节点个数。
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
 * 其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.24 - 下午 1:56
 * @label Tree Depth-firstSearch BinarySearch
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗完全二叉树root：[1,2,3,4,5,6]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            int result = new SolutionCopy2().countNodes(root);
            System.out.println("该完全二叉树有：" + result + "个节点");
        }
    }
}

/**
 * DFS
 */
class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

/**
 * BFS
 */
class SolutionCopy {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count += 1;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }
}

/**
 * BinarySearch BitManipulation
 */
class SolutionCopy2 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}

/**
 * 完全二叉树的高度可以直接通过不断地访问左子树就可以获取
 * 判断左右子树的高度:
 * 如果相等说明左子树是满二叉树, 然后进一步判断右子树的节点数(最后一层最后出现的节点必然在右子树中)
 * 如果不等说明右子树是深度小于左子树的满二叉树, 然后进一步判断左子树的节点数(最后一层最后出现的节点必然在左子树中)
 **/
class SolutionCopy3 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        // 1(根节点) + (1 << ld)-1(左完全左子树节点数) + 右子树节点数量
        if (ld == rd) {
            return (1 << ld) + countNodes(root.right);
        }
        // 1(根节点) + (1 << rd)-1(右完全右子树节点数) + 左子树节点数量
        else {
            return (1 << rd) + countNodes(root.left);
        }
    }

    private int getDepth(TreeNode r) {
        int depth = 0;
        while (r != null) {
            depth++;
            r = r.left;
        }
        return depth;
    }
}