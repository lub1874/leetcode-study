package com.leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName NumsSquare.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 14:14
 */
public class NumsSquare {
    public static int numsSquare(int n) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int i = 0; i < size; i++) {
                int digit = queue.poll();
                for(int j = 1; j <n ;j++) {
                    int nodeValue = digit + j*j;
                    if (nodeValue == n) {
                        return level;
                    }

                    if (nodeValue > n) {
                        break;
                    }

                    if (!visited.contains(nodeValue)) {
                        queue.offer(nodeValue);
                        visited.add(nodeValue);
                    }
                }
            }
        }

        return level;
    }

    public static void main(String[] args) {
        System.out.println(numsSquare(12));
    }
}
