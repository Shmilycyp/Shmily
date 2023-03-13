package com.imcode.leetcode.hot;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/5
 * @Description :
 */
public class MaxProfit {


    public static void main(String[] args) {

        // [6,1,3,2,4,7]
        System.out.println(new MaxProfit().maxProfit(new int[]{6, 1, 3, 2, 4, 7}));

        System.out.println(new MaxProfit().maxProfit(new int[]{3,3,5,0,0,3,1,4}));

        System.out.println(new MaxProfit().maxProfit(new int[]{1, 4, 2}));

    }


    public int maxProfit(int[] prices) {

        int sell11 = -prices[0], buyer11 = prices[0];
        int sell22 = -prices[0], buyer22 = prices[0];

        int max = 0;
        for (int i = 0; i < prices.length; i++) {

            sell11 = Math.max(sell11, prices[i]);
            buyer11 = Math.min(buyer11, prices[i]);



        }
        return max;
    }

}
