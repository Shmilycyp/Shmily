package com.imcode.leecode;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-26 20:30
 */
public class Offer58 {

    public static void main(String[] args) {

        Offer58 offer58 = new Offer58();

        System.out.println(offer58.reverseLeftWords("abc", 1));
    }


    // abc 1 -> bca


    public String reverseLeftWords(String s, int n) {


        // 3
        int length = s.length();

        // 1
        int i = n % length;


        String substring = s.substring(i+1);

        String substring1 = s.substring(0, i+1);


        return substring1 + substring ;
    }

}
