package 二叉树的序列化与反序列化;
/**
 * FileName: SerializeAndDeserializeBinaryTree
 * 类的详细说明
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * @label Tree Design
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.16
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("输入一个表示数组的字符串：[1,2,3,null,null,4,5]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            Codec codec = new Codec();
            //先序遍历 序列化与反序列化
            TreeNode deserialize = codec.deserialize(codec.serialize(root));
            //层序遍历输出
            String out = new PublicMethod().integerTreeToString(deserialize);
            System.out.println(out);
        }
    }
}

class Codec {
    //频繁添加字符串的场景，推荐用StringBuilder类去做
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = ser_help(root, new StringBuilder());
        return res.toString();
    }

    //先序遍历
    private StringBuilder ser_help(TreeNode root, StringBuilder str) {
        if (null == root) {
            str.append("null,");
            return str;
        }
        str.append(root.val);
        str.append(",");
        str = ser_help(root.left, str);
        str = ser_help(root.right, str);
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(parts));
        return deser_help(list);
    }

    private TreeNode deser_help(List<String> list) {
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        TreeNode res = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        res.left = deser_help(list);
        res.right = deser_help(list);
        return res;
    }
}