package com.imcode.leecode;

import java.util.Arrays;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-04 15:25
 */
public class QuickSort {


    public static void main(String[] args) {
        System.out.println(9/2);
        quickSort(new int[]{2,8,7,1,3,5,6,4,9});
    }




    public static void quickSort(int[] nums){

        quickSort(nums , 0 , nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }


    // [2,3,1]
    // 1 , 2
    private static void quickSort(int[] nums , int p , int r){

        if(p < r){

            int partition = q(nums, p, r);

            quickSort(nums , p , partition -1);
            quickSort(nums , partition + 1 , r);

        }

    }


    private static int partition(int[] nums , int p , int r){

        int x = nums[r];
        int i = p-1;

        for (int j = p; j < r-1; j++) {
            if(nums[j] <= x){
                i++;

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i +1] = nums[r];
        nums[r] = temp;

        return i+1;
    }


    public static int q(int[] nums , int left , int right){

        int x = nums[right];

        int i = left , j = right ;

        // 从左边找到一个比x 大的数

        while (i != j){
            while (nums[i] <= x && i < j) i++;
            while (nums[j] >= x && i < j) j--;
            swap(nums , i , j);
        }
        swap(nums , right , i );

        return i;
    }


    private static void swap(int[] nums , int x , int y){

        int temp = nums[y];
        nums[y] = nums[x];
        nums[x] = temp;


    }
}
