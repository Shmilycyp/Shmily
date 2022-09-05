package com.imcode.leetcode.hot;

import com.imcode.leetcode.util.SortUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shmilyah@163.com
 * https://leetcode.cn/problems/3sum/?favorite=2cktkvj
 * @date 2022-09-05
 */
public class sum_3 {


    private List<List<Integer>> resource;

    public List<List<Integer>> threeSum(int[] nums) {

        resource = new ArrayList<>();
        SortUtils.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, i + 1, nums.length -1 , -nums[i] , nums[i]);
        }

        return resource;
    }


    public void twoSum(int[] nums, int left, int right, int target, int value) {


        while (left < right) {

            int sum = nums[left] + nums[right];

            if (sum == target) {

                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(value);
                temp.add(nums[left]);
                temp.add(nums[right]);
                resource.add(temp);
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                left++;

                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                right--;
            } else if (sum < target) {
                left ++;
            } else {
                right --;
            }

        }

    }







}
