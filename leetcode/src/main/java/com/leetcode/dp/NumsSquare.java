package com.leetcode.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName NumsSquare.scala
 * @author: lulong
 * @Date: 2021/11/18
 * @Time: 14:27
 */
@Slf4j
public class NumsSquare {
    public static int numsSquare(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for(int j = 1; j*j <= i; j++) {
                int num = dp[i - j*j] + 1;
                int num1 = dp[i];
                dp[i] = Math.min(num1, num);
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(numsSquare(12));
    }
}
