package com.imcode.leetcode.hot;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/3
 * @Description :
 */
public class VideoStitching {

    public static void main(String[] args) {

        // [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]
        //10
        System.out.println(new VideoStitching().videoStitching(new int[][]{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}}, 10));

    }

    public int videoStitching(int[][] clips, int time) {
        int[] maxRight = new int[time + 1];
        for (int i = 0; i < maxRight.length; i++) {
            maxRight[i] = i;
        }

        for (int i = 0; i < clips.length; i++) {
            int left = Math.min(maxRight.length - 1, clips[i][0]);
            int right = Math.min(clips[i][1], maxRight.length -1 );
            maxRight[left] = Math.max(maxRight[left],right );
        }
        int ret = 0, pre = 0, max = 0;
        for (int i = 0; i < time; i++) {
            max = Math.max(max, maxRight[i]);
            if (i == max) {
                return -1;
            }
            if (pre == i) {
                pre = max;
                ret++;
            }
        }
        return ret;
    }





}
