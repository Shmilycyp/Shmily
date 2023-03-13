package com.imcode.leetcode.hot;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/6
 * @Description :
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        // [1,3,6,7,9,4,10,5,6]
        System.out.println(new LengthOfLIS().lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
    }


    public int lengthOfLIS(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {

            int max = 0;
            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }

        return max + 1;
    }
}
