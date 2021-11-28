package com.leetcode.bfs;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName isCousins.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 10:56
 */
public class Solute {
    public static boolean isCousins(TreeNode root, int x, int y) {
        // 两个队列，一个存放节点，一个存放节点的值
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valueQueue = new LinkedList<>();

        nodeQueue.add(root);
        valueQueue.add(root.val);

        // 节点队列不为空，说明没遍历完
        while (!nodeQueue.isEmpty()) {
            // BFS是一层一层的去遍历，这是当前层级的节点个数
            int levelSize = nodeQueue.size();
            for (int i = 0; i < levelSize; i++) {
                // 节点和节点的值同时出队
                TreeNode poll = nodeQueue.poll();
                valueQueue.poll();

                // 判断是不是亲兄弟节点
                if (poll.left != null && poll.right != null) {
                    if ((poll.left.val == x && poll.right.val == y) || (poll.left.val == y && poll.right.val == x)) {
                        return false;
                    }
                }

                if (poll.left != null) {
                    nodeQueue.offer(poll.left);
                    valueQueue.offer(poll.left.val);
                }
                if (poll.right != null) {
                    nodeQueue.offer(poll.right);
                    valueQueue.offer(poll.right.val);
                }
            }

            if (valueQueue.contains(x) && valueQueue.contains(y)) {
                return true;
            }
        }

        return false;
    }
}
