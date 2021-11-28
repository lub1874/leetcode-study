package com.leetcode.bfs;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName WordBreakBfs.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 13:16
 */
@Slf4j
public class WordBreakBfs {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Queue<Integer> indexQueue = new LinkedList<Integer>();
        indexQueue.add(0);
        int len = s.length();

        while (!indexQueue.isEmpty()) {
            int cur = indexQueue.poll();
            if (cur == len) {
                return true;
            }

            for (int i = cur + 1; i <= len; i++) {
                String str = s.substring(cur, i);
                log.info("截取的字符串：{}", str);
                if (wordDict.contains(str)) {
                    indexQueue.add(i);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<String>();
        wordDict.add("leet");
        wordDict.add("code");

        System.out.println(wordBreak(s, wordDict));
    }
}
