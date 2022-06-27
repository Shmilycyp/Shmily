package com.imcode.leecode;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-27 20:24
 */
public class Offer03 {



    public int findRepeatNumber(int[] nums) {

        int length = nums.length;

        for (int i = 0; i < length; i++) {

            for (int j = i+1 ; j < length; j++) {

                if (nums[i] ==  nums[j]) {
                    return nums[i];
                }

            }

        }
        return -1;
    }
}
