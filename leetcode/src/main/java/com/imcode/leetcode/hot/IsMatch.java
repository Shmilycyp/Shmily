package com.imcode.leetcode.hot;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/25
 * @Description :
 */
public class IsMatch {


    /**
     *  p p p p
     * p
     * p
     * .
     * *
     * p
     * p
     *
     * "a"
     * ".*..a*"
     *
     * @param args
     */
    public static void main(String[] args) {
        // "mississippi"
        //"mis*is*p*."
        System.out.println(new IsMatch().isMatch("a", "..*"));
    }

    // "bbbba"  mississ
    //".*a*a"   mis*i

    // "aaa"
    //"ab*a*c*a"

    // "aaa"
    //"ab*ac*a"

    // "ab"
    //".*"

    // "aab"
    //"c*a*b"

    //"mississippi"
    //"mis*is*p*."

    // "a"
    //"..*"

    // 状态转移方程： 普通情况: 如果当前字符匹配，则当前的是否匹配可以由字符各减去一获得 （i-1）(j -1)，如果恰巧当前是第一个字符，则是空字符
    //              特殊情况： 如果当前是由* 号匹配，则当前是否匹配由当前 (i -1)(j) 决定
    //              特殊情况: 如果当前不匹配且当前匹配的是*号，则需要去除*号之后重新匹配


    public boolean isMatch(String s, String p) {
        // 每个坐标 (i, j) 代表 s字符串的前i个字符与p的前j个字符是否匹配
        boolean[][] dp = new boolean[s.length()][p.length()];

        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; j < p.length(); j++) {

                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i][j] = isMatchBefore(dp, s, p, i - 1, j -1);
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i)) {
                        dp[i][j] = isMatchBefore(dp, s, p, i - 1, j) || isMatchBefore(dp, s, p, i, j -2);
                    } else {
                        dp[i][j] = isMatchBefore(dp, s, p, i, j - 2);
                    }

                }
                if (dp[i][j]) {
                    System.out.println("匹配 i = " + i + " j = " + j +  " s =>" + s.substring(0, i + 1) + " p =>" + p.substring(0, j +1) );
                }
            }
        }
        return dp[s.length() - 1] [p.length() -1];
    }


    /**
     * 当前是否匹配
     * 1. 如果之前的计算结果为true 则直接为ture
     * 2. 如果当前是*号，则进行递归查询去除了*号之后是否匹配
     * 3. 特殊处理，如果是匹配字符为
     * @param dp 以前的计算缓存
     * @param s 源字符串 s
     * @param p 匹配字符 p
     * @param sIndex 当前计算的 s索引
     * @param pIndex 当前计算的 p索引
     * @return 以当前索引进行匹配是否匹配
     */
    private static boolean isMatchBefore(boolean[][] dp, String s, String p, int sIndex, int pIndex) {

        if (sIndex < 0) {
            if (pIndex < 0) {
                return true;
            }
            if (p.charAt(pIndex) == '*') {
                return isMatchBefore(dp, s, p, sIndex, pIndex - 2);
            }
            return false;
        }
        if (pIndex < 0) {
            return false;
        }
        if (dp[sIndex][pIndex]) {
            return true;
        }
        // 如果此时的Pindex对应的为*
        if (p.charAt(pIndex) == '*') {
            return isMatchBefore(dp, s, p, sIndex, pIndex - 2);
        }
        return false;
    }




}
