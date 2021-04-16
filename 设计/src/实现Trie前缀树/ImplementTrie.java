package 实现Trie前缀树;

import Design公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: ImplementTrie.java
 * 类的详细说明
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；
 * 否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；
 * 否则，返回 false 。
 *  
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.14 - 下午 8:30
 * @label Design Trie
 */
public class ImplementTrie {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一些方法functions：[\"Trie\",\"insert\",\"search\",\"search\",\"startsWith\",\"insert\",\"search\"]");
        while ((line = in.readLine()) != null) {
            String[] functions = PublicMethod.stringToStringArray(line);
            System.out.println("请输入一些操作数options：[\"\",\"apple\",\"apple\",\"app\",\"app\",\"app\",\"app\"]");
            String[] options = PublicMethod.stringToStringArray(in.readLine());
            Trie trie = null;
            String[] results = new String[functions.length];
            for (int i = 0; i < functions.length; ++i) {
                switch (functions[i]) {
                    case "Trie":
                        trie = new Trie();
                        results[i] = "null";
                        break;
                    case "insert":
                        assert trie != null;
                        trie.insert(options[i]);
                        results[i] = "null";
                        break;
                    case "search":
                        assert trie != null;
                        results[i] = String.valueOf(trie.search(options[i]));
                        break;
                    case "startsWith":
                        assert trie != null;
                        results[i] = String.valueOf(trie.startsWith(options[i]));
                }
            }
            System.out.println(Arrays.toString(results));
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
class Trie {

    // Trie树其实就是维护有公共前缀子串的树。
    private class Node { // 每个节点最多有26个不同的小写字母
        private boolean isEnd;
        private Node[] next;

        public Node() {
            isEnd = false;
            next = new Node[26];
        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node cur = this.root;
        for (int i = 0, len = word.length(), ch; i < len; ++i) {
            ch = word.charAt(i) - 'a';
            if (cur.next[ch] == null) {
                cur.next[ch] = new Node();
            }
            cur = cur.next[ch];
        }
        cur.isEnd = true; // 加上一个标记，表示为一个单词
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0, len = word.length(), ch; i < len; ++i) {
            ch = word.charAt(i) - 'a';
            if (cur.next[ch] == null) {
                return false;
            }
            cur = cur.next[ch];
        }
        return cur.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0, len = prefix.length(), ch; i < len; ++i) {
            ch = prefix.charAt(i) - 'a';
            if (cur.next[ch] == null) {
                return false; // 未遍历完前缀直接返回
            }
            cur = cur.next[ch];
        }
        return true;
    }
}

