package com.imcode.leecode;

import java.util.Arrays;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-26 22:15
 */
public class Offer1654 {

    public static void main(String[] args) {
        Offer1654 offer1654 = new Offer1654();

        offer1654.minimumJumps(new int[]{1,6,2,14,5,17,4} , 16 , 9 , 7);
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {


        int i = x + b;

        int[] ints = new int[i];

        Arrays.fill(ints , Integer.MAX_VALUE);
        ints[0] = 0;

        int jump = a - b;

        if (jump > 0) {
            // 可以前进
            ints[0] = 1;
            int index = 0;
            while (index + jump -1< i){
                ints[index + jump-1] = ints[index] + 2;
                index += jump;
            }
            index = 0;
            while (index + a -1  < i) {
                ints[index + a -1 ] = Math.min(ints[index] + 1 , ints[index + a -1 ]);
                index += a;
            }
        } else {

            // 可以后退

        }


        return ints[x -1];
    }


    //
}
