package com.imcode.leecode;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-27 20:44
 */
public class Offer53 {


    public static void main(String[] args) {
        Offer53 offer53 = new Offer53();



        System.out.println(offer53.missingNumber(new int[]{1,2}));

    }


    public int missingNumber(int[] nums) {

        // 二分



        return find(nums , 0 , nums.length -1);
    }


    private int find(int[] nums , int left , int right){


        if (left == right) {

            if (nums[left] == left) {

                return left+1;
            }else if (nums[left] > left) {
                return left;
            } else {
                return left-1;
            }

        }

        int i = left + (right - left) / 2;

        if (nums[i] == i) {
            return find(nums , i +1 , right);
        } else {

            if (i == 0) {
                return 0;
            }

            if (nums[i -1] == i-1) {
                return i;
            }

            return find(nums, left ,i-1);
        }

    }

    public int search(int[] nums, int target) {


        int s = s(nums, 0, nums.length - 1, target);

        if (s == -1) {
            return 0;
        }

        int num = 0;
        int leftTemp = s;
        while (leftTemp >=0 && nums[leftTemp] == target){
            num++;
            leftTemp--;
        }

        int rightTemp = s+1;
        while (rightTemp < nums.length && nums[rightTemp] == target){
            num++;
            rightTemp++;
        }

        return num;
    }


    public int s(int[] nums, int left , int right , int target){


        if (right == left) {
            return nums[right] == target ? right : -1;
        }
        if (left > right) {
            return -1;
        }

        int index =left +  ((right - left) / 2);

        if (target < nums[index]) {
            return s(nums, left , index-1 , target);
        } else if (target > nums[index]) {
            return s(nums,index +1, right , target);
        }else {
            return index;
        }


    }

}
