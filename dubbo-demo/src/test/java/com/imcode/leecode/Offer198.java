package com.imcode.leecode;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-26 21:07
 */
public class Offer198 {


    public static void main(String[] args) {

        Offer198 offer198 = new Offer198();

        System.out.println(offer198.rob(new int[]{2,7,9,3,1}));
    }


    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int[] moneys = new int[nums.length];
        moneys[0] = nums[0];
        moneys[1] = Math.max(nums[1] , nums[0]);

        for (int i = 2; i < nums.length; i++) {
            moneys[i] = Math.max(Math.max(moneys[i-2] + nums[i] , moneys[i]) , moneys[i-1]);
        }

        return moneys[nums.length - 1];
    }
}
