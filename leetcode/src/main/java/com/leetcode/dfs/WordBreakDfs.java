package com.leetcode.dfs;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName WordBreak.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 11:40
 */
@Slf4j
public class WordBreakDfs {
    public static boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashSet<Integer>(), 0);
    }

    public static boolean dfs(String s, List<String> wordDict, Set<Integer> setIndex, int index ) {
        if (index == s.length()) {
            return true;
        }

        for (int i = index + 1; i <= s.length(); i++) {
            if (setIndex.contains(i)) {
                continue;
            }
            String str = s.substring(index, i);
            log.info("截取之后的字符串：{}", str);

            if (wordDict.contains(str)) {
                if (dfs(s, wordDict, setIndex, i)) {
                    return true;
                }
                setIndex.add(i);
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
