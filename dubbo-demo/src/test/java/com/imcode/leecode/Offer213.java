package com.imcode.leecode;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-26 21:34
 */
public class Offer213 {

    public static void main(String[] args) {

        Offer213 offer213 = new Offer213();

        System.out.println(offer213.rob(new int[]{0}));
    }


    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0] , nums[1]);
        }


        int[] ints = new int[nums.length - 1];

        System.arraycopy(nums , 0 , ints , 0, nums.length-1);

        int[] ints1 = new int[nums.length - 1];
        System.arraycopy(nums, 1 , ints1 , 0 , nums.length -1);

        return Math.max(realRob(ints) , realRob(ints1));

    }


    public int realRob(int[] nums) {

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
