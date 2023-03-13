package com.imcode.leetcode.hot;

import java.util.Arrays;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/27
 * @Description :
 */
public class IsMatch2 {

    public static void main(String[] args) {
        System.out.println(new IsMatch2().isMatch("abcabczzzde", "*abc???de*"));
    }

    // "abcabczzzde"
    //"*abc???de*"

    public boolean isMatch(String s, String p) {

        if (s.length() == 0) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (p.length() == 0) {
            return false;
        }

        boolean[][] dp = new boolean[s.length()][p.length()];
        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; j < p.length(); j++) {

                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i][j] = isMatchBefore(dp, p, i -1, j -1);
                } else if (p.charAt(j) == '*') {
                    dp[i][j] = isMatchBefore(dp, p , i -1, j) || isMatchBefore(dp, p, i, j -1);
                }
                if (dp[i][j]) {
                    System.out.println("匹配 i = " + i + " j = " + j +  " s =>" + s.substring(0, i + 1) + " p =>" + p.substring(0, j +1) );
                }
            }
        }
        return dp[s.length() - 1][p.length() - 1];
    }



    private static boolean isMatchBefore(boolean[][] dp,  String p ,int sIndex, int pIndex) {

        if (sIndex < 0) {

            if (pIndex < 0) {
                return true;
            }
            if (p.charAt(pIndex) == '*') {
                if (pIndex == 0) {
                    return true;
                } else {
                    return isMatchBefore(dp, p , sIndex, pIndex - 1);
                }
            }
            return false;
        }

        if (pIndex < 0) {
            return false;
        }

        if (dp[sIndex][pIndex]) {
            return true;
        }

        if (p.charAt(pIndex) == '*') {
            return isMatchBefore(dp, p, sIndex, pIndex - 1);
        }

        return false;
    }

}
