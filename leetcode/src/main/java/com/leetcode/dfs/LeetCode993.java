package com.leetcode.dfs;

import com.leetcode.TreeNode;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName LeetCode993.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 11:26
 */
public class LeetCode993 {
    private TreeNode xParent;
    private TreeNode yParent;
    private int xDepth = -1;
    private int yDepth = -2;

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, x, y, 0);

        return xDepth == yDepth && xParent != yParent;
    }

    public void dfs(TreeNode root, TreeNode parent, int x, int y, int depth) {
        if (root == null) {
            return;
        }

        if (root.val == x) {
            xParent = parent;
            xDepth = depth;
        } else if (root.val == y) {
            yParent = parent;
            yDepth = depth;
        }

        if (xDepth == yDepth && xParent == yParent) {
            return;
        }

        dfs(root.left, root, x, y, depth+1);
        dfs(root.right, root, x, y, depth + 1);


    }
}
