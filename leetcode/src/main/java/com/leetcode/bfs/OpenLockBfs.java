package com.leetcode.bfs;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName OpenLockBfs.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 13:38
 */
@Slf4j
public class OpenLockBfs {
    public static int openLock(String[] deadEnds, String target) {
        Set<String> deadSets = new HashSet<>(Arrays.asList(deadEnds));
        Set<String> visited = new HashSet<>();
        String start = "0000";

        if (deadSets.contains(start)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String poll = queue.poll();

                for (int i = 0; i < 4; i++) {
                    char ch = poll.charAt(i);
                    String strAdd = poll.substring(0, i) + (ch == '9' ? 0 : ch - '0' + 1) + poll.substring(i + 1);
                    String strSub = poll.substring(0, i) + (ch == '0' ? 9 : ch - '0' - 1) + poll.substring(i + 1);
                    log.info("当前的level: {}", level);
                    log.info("加1之后的字符串: {}", strAdd);
                    log.info("减1之后的字符串: {}", strSub);
                    if (poll.equals(target)) {
                        return level;
                    }

                    if (!deadSets.contains(strAdd) && !visited.contains(strAdd)) {
                        queue.add(strAdd);
                        visited.add(strAdd);
                    }

                    if (!deadSets.contains(strSub) && !visited.contains(strSub)) {
                        queue.add(strSub);
                        visited.add(strSub);
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] deadEnds = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        System.out.println(openLock(deadEnds, target));

        System.out.println(openLock1(deadEnds, target));
    }

    public static int openLock1(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        //开始遍历的字符串是"0000"，相当于根节点
        String startStr = "0000";
        if (set.contains(startStr))
            return -1;
        //创建队列
        Queue<String> queue = new LinkedList<>();
        //记录访问过的节点
        Set<String> visited = new HashSet<>();
        queue.offer(startStr);
        visited.add(startStr);
        //树的层数
        int level = 0;
        while (!queue.isEmpty()) {
            //每层的子节点个数
            int size = queue.size();
            while (size-- > 0) {
                //每个节点的值
                String str = queue.poll();
                //对于每个节点中的4个数字分别进行加1和减1，相当于创建8个子节点，这八个子节点
                //可以类比二叉树的左右子节点
                for (int i = 0; i < 4; i++) {
                    char ch = str.charAt(i);
                    //strAdd表示加1的结果，strSub表示减1的结果
                    String strAdd = str.substring(0, i) + (ch == '9' ? 0 : ch - '0' + 1) + str.substring(i + 1);
                    String strSub = str.substring(0, i) + (ch == '0' ? 9 : ch - '0' - 1) + str.substring(i + 1);
                    //如果找到直接返回
                    if (str.equals(target))
                        return level;
                    //不能包含死亡数字也不能包含访问过的字符串
                    if (!visited.contains(strAdd) && !set.contains(strAdd)) {
                        queue.offer(strAdd);
                        visited.add(strAdd);
                    }
                    if (!visited.contains(strSub) && !set.contains(strSub)) {
                        queue.offer(strSub);
                        visited.add(strSub);
                    }
                }
            }
            //当前层访问完了，到下一层，层数要加1
            level++;
        }
        return -1;
    }
}
