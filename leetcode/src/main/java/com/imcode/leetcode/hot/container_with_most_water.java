package com.imcode.leetcode.hot;

/**
 * @author shmilyah@163.com
 * https://leetcode.cn/problems/container-with-most-water/?favorite=2cktkvj
 * @date 2022-09-05
 */
public class container_with_most_water {

    public static void main(String[] args) {

        container_with_most_water obj = new container_with_most_water();
        System.out.println(obj.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));

    }

    public int maxArea(int[] height) {
        int area = 0;
        int right = height.length -1, left = 0;

        while (left < right) {
            area = Math.max(area, getArea(height, left, right));
            if (height[left] < height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return area;
    }

    public static int getArea(int[] water, int left, int right) {
        return getLength(left, right) * Math.min(water[left], water[right]);
    }

    public static int getLength(int left, int right) {

        return right - left;
    }


}
