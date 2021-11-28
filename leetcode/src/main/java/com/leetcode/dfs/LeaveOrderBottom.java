package com.leetcode.dfs;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName LeaveOrderBottom.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 15:09
 */
public class LeaveOrderBottom {
    public List<List<Integer>> leaveOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root, 0);

        return res;
    }

    public void dfs(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level == list.size()) {
            list.add(0, new ArrayList<>());
        }

        list.get(list.size() - level - 1).add(root.val);
        dfs(list, root.left, level + 1);
        dfs(list, root.right, level + 1);
    }
}
