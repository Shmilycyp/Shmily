package com.imcode.leetcode.hot;

import java.util.function.DoublePredicate;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/28
 * @Description :
 */
public class IsInterleave {

    public static void main(String[] args) {
        System.out.println(new IsInterleave().isInterleave("a", "", "a"));
    }


    public boolean isInterleave(String s1, String s2, String s3) {

        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        if (s3.length() == 0) {
            return true;
        }
        // dp[i][j] w
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        // 初始化第一行
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (s3.charAt(i - 1) == s1.charAt(i -1) && dp[i - 1][0]) {
                dp[i][0] = true;
            }
        }

        for (int i = 1; i < dp[0].length; i++) {
            if (s3.charAt(i - 1) == s2.charAt(i - 1) && dp[0][i -1]) {
                dp[0][i] = true;
            }
        }
        dp[0][0] = false;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s1.charAt(i -1) == s3.charAt(i + j - 1) && dp[i -1][j]) {
                    dp[i][j] = true;
                    continue;
                }
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }


}
