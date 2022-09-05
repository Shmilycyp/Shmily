package com.imcode.leetcode.util;

import java.util.Arrays;

/**
 * @author shmilyah@163.com
 * @date 2022-09-05
 */
public class SortUtils {

    public static void main(String[] args) {
        int[] ints = {-1,0,1,2,-1,-4};
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }


    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }


    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partition = partition(nums, left, right);
            quickSort(nums, left, partition - 1);
            quickSort(nums, partition + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {

        int x = nums[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] <= x) {
                i ++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i + 1] = nums[right];
        nums[right] = temp;
        return i + 1;
    }



}
