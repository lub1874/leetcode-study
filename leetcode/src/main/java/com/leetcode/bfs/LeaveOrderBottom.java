package com.leetcode.bfs;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName LeaveOrderBottom.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 14:48
 */
public class LeaveOrderBottom {
    public static List<List<Integer>> leaveOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelCount = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(0, subList);
        }
        return res;
    }
}
