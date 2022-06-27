package com.imcode;

/**
 * @author shmilyah@163.com
 * @date 2022-06-20
 */
public class leecode {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babbad"));
    }


    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 动态规划
        boolean[][] booleans = new boolean[length + 1][length + 1];
        // 初始化
        for (int i = 0; i < length; i++) {
            booleans[i][i] = true;
        }
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                booleans[i][j] = booleans[i +1][j -1] && chars[i] == chars[j];

            }
        }

        // 动态转移方程
        // P(i , j ) = P(i+1 , j -1) && char[i] = char[j]


        return "";
    }



    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
