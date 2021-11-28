import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName Solute.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 10:20
 */

public class Solute {
//    public static int deepsLeavesSum(TreeNode node) {
//        int res = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//
//        queue.add(node);
//
//        while (!queue.isEmpty()) {
//            int count = queue.size();
//            res = 0;
//            for (int i = 0; i < count; i++) {
//                TreeNode treeNode = queue.poll();
//                res += treeNode.val;
//
//                if (treeNode.left != null) {
//                    queue.add(treeNode.left);
//                }
//
//                if (treeNode.right != null) {
//                    queue.add(treeNode.right);
//                }
//            }
//        }
//
//        return res;
//    }

    private static int maxDeep = 0;
    private static int sum = 0;

    public static int deepsLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    public static void dfs(TreeNode root, int level) {
        if (root == null) return;
        if (level > maxDeep) {
            sum = 0;
            maxDeep = level;
        }

        if (level == maxDeep) {
            sum += root.val;
        }

        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }
}
