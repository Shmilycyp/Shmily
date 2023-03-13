package com.imcode.leetcode.hot;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/3
 * @Description :
 */
public class MinTaps {

    public static void main(String[] args) {
        // 7
        //[1,2,1,0,2,1,0,1]
        System.out.println(new MinTaps().minTaps(7, new int[]{1,2,1,0,2,1,0,1}));

        // 8
        //[4,0,0,0,0,0,0,0,4]
        System.out.println(new MinTaps().minTaps(8, new int[]{4,0,0,0,0,0,0,0,4}));

        // 8
        //[4,0,0,0,4,0,0,0,4]
        System.out.println(new MinTaps().minTaps(8, new int[]{4,0,0,0,4,0,0,0,4}));

        // 19
        //[4,1,5,0,5,3,3,3,0,0,3,3,2,2,4,4,2,3,4,2]

        System.out.println(new MinTaps().minTaps(19, new int[]{4,1,5,0,5,3,3,3,0,0,3,3,2,2,4,4,2,3,4,2}));

    }

    public int minTapsReal(int n, int[] ranges) {
        int[] rightMost = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            rightMost[i] = i;
        }
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            rightMost[start] = Math.max(rightMost[start], end);
        }
        int last = 0, ret = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            last = Math.max(last, rightMost[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }




    public int minTaps(int n, int[] ranges) {
        int[][] ints = new int[ranges.length][2];
        for (int i = 0; i < ranges.length; i++) {
            ints[i] = new int[]{i - ranges[i], i + ranges[i]};
        }
        Arrays.sort(ints, Comparator.comparingInt(o -> o[0]));
        if (ints[0][1] >= n) {
            return 1;
        }
        int minSlt = 1;
        // 以最左边的节点开始，
        int next = getInitIndex(ints);
        while (ints[next][1] < n) {
            int oldNext = next;
            next = getNext(ints, next, n);
            if (ints[oldNext][1] == ints[next][1]) {
                return -1;
            }
            minSlt++;
        }
        return minSlt;
    }


    private int getInitIndex(int[][] ints) {
        int maxIndex = 0;
        for (int i = 1; i < ints.length; i++) {
            if (ints[i][0] <= 0) {
                if (ints[i][1] > ints[maxIndex][1]) {
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }


    private int getNext(int[][] ints, int curIndex, int n) {
        int right = ints[curIndex][1];
        int maxIndex = curIndex;
        for (int i = 0; i < ints.length; i++) {
            // 左边有交界
            if (ints[i][0] <=  right) {
                // 当右边都超过的时候，要选左边更小的
                if (ints[i][1] >= n && ints[maxIndex][1] >= n) {
                    if (ints[i][0] < ints[maxIndex][0]) {
                        maxIndex = i;
                    }
                } else {
                    // 右边要
                    if (ints[i][1] > ints[maxIndex][1]) {
                        maxIndex = i;
                    }
                }
            }
        }
        return maxIndex;
    }


}
