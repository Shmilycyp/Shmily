package com.imcode.leetcode.hot;

import java.util.Arrays;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/10
 * @Description :
 */
public class JumpGame {

    public static void main(String[] args) {


        System.out.println(new JumpGame().maxProfit(new int[]{1,2,3,4,5}));

        System.out.println(new JumpGame().uniquePathsWithObstacles(
                new int[][]{{1 , 0}}));

        System.out.println(new JumpGame().jump(new int[]{2,3,0,1,4}));
    }










    /**
     * TODO 改成贪心算法
     */
    public int jump(int[] nums) {
        // 计PATH[i] 的值为到达i所需的最小步数
        int[] path = new int[nums.length];
        for (int i = 1; i < path.length; i++) {
            path[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length) {
                    continue;
                }
                path[i + j] = Math.min(path[i + j], path[i] + 1);
            }
        }
        return path[nums.length - 1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 1;
        }
        int[][] cost = new int[obstacleGrid.length][obstacleGrid[0].length];

        cost[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        // 计cost[i][j] 为到达 (i,j)时的路径
        for (int i = 1; i < cost.length; i++) {
            if (obstacleGrid[i][0] != 1) {
                cost[i][0] = cost[i -1][0];
            }
        }
        for (int i = 1; i < cost[0].length; i++) {
            if (obstacleGrid[0][i] != 1) {
                cost[0][i] = cost[0][i -1];
            }
        }
        for (int i = 1; i < cost.length; i++) {
            for (int j = 1; j < cost[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cost[i][j] = 0;
                    continue;
                }
                cost[i][j] = cost[i -1][j] + cost[i][j -1];
            }
        }
        return cost[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }


    // 第二天能获取到的最大利润 = 前一天所能获得的最大利润

    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxMoney = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                // 该抛了
                maxMoney += prices[i - 1] - minPrice;
                minPrice = prices[i];
            }
        }
        if (minPrice < prices[prices.length - 1]) {
            maxMoney += (prices[prices.length - 1] - minPrice);
        }
        return maxMoney;
    }


}







