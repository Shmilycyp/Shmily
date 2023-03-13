package com.imcode.leetcode.hot;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/25
 * @Description :
 */
public class MaximalSquare {

    /**
     * [["1","1","1","1","1","1","1","1"],
     * ["1","1","1","1","1","1","1","0"],
     * ["1","1","1","1","1","1","1","0"],
     * ["1","1","1","1","1","0","0","0"],
     * ["0","1","1","1","1","0","0","0"]]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new MaximalSquare().maximalSquare(new char[][]{
                {'1','1','1','1', '1', '1', '1', '1'},
                {'1','1','1','1', '1', '1', '1', '0'},
                {'1','1','1','1', '1', '1', '1', '0'},
                {'1','1','1','1', '1', '0', '0', '0'},
                {'0','1','1','1', '1', '0', '0', '0'}}));
    }


    public int maximalSquare(char[][] matrix) {

        int maxValue = 0;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxValue = Math.max(maxValue, dp[i][0]);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i] == '1' ? 1 :0;
            maxValue = Math.max(maxValue, dp[0][i]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int value = dp[i - 1][j - 1];
                    if (value != 0) {
                        // 说明此时已经形成正方形了，判断此时可以形成一个多大的正方形

                        int valueQ = 1;
                        for (int q = 1; q <= value; q++) {
                            if (matrix[i][j -q] == '1' && matrix[i - q][j] == '1') {
                                valueQ++;
                            } else {
                                break;
                            }
                        }
                        dp[i][j] = valueQ;
                    } else {
                        dp[i][j] = 1;
                    }
                }
                maxValue = Math.max(maxValue, dp[i][j]);
            }
        }

        return maxValue * maxValue;
    }


}
