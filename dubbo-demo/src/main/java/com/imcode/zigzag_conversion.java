package com.imcode;

import cn.hutool.core.text.StrBuilder;

/**
 * @author shmilyah@163.com
 * @date 2022-06-21
 */
public class zigzag_conversion {


    public static void main(String[] args) {

        // PAYPALISHIRING
        System.out.println(new zigzag_conversion().convert("PAYPALISHIRING" , 3));

        // PAHNAPLSIIGYIR
        // PAHNAPLSIIGYIR

    }


    public String convert(String s, int numRows) {

        if (numRows <= 2 || s.length() <= numRows) {
            return s;
        }

        // 一个周期是 numRows + numRows - 2 =  2 n  -2
        // 每一行的 0 * (2n-2) - 1 * (2n-2)
        int p = numRows * 2 - 2;
        // 总共有i行

        StringBuilder strBuilder = new StringBuilder(s.length());

        for (int j = 0; j < numRows; j++) {

            // 循环每个周期
            for (int i = 0; i < s.length(); i += p) {
                // 加入每个周期的第一个字符
                if (i + j < s.length()) {
                    strBuilder.append(s.charAt(i + j));
                }

                // 加入周期的第二个字

                if (j != 0 && j != numRows - 1) {
                    int index = i + p - j;
                    if (index > i && index < s.length()) {
                        strBuilder.append(s.charAt(index));
                    }
                }


            }
        }

        return strBuilder.toString();

    }






}
