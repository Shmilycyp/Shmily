package com.imcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shmilyah@163.com
 * @date 2022-06-22
 */
public class string_to_integer_atoi {

    public static void main(String[] args) {
        System.out.println(new string_to_integer_atoi().myAtoi("   -42"));
    }



    public boolean isPalindrome(int x) {

        String s = String.valueOf(x);

        int left = 0 , right = s.length() -1;

        while (left != right){
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public int myAtoi(String s) {

        Atoi atoi = new Atoi();
        for (char c : s.toCharArray()) {
            atoi.putChar(c);
        }
        return (int) (atoi.sign * atoi.value);
    }

    public static class Atoi {

        // 1 start 2 sign 3 in number 4 end
        int state = 1;
        long value;
        int sign = 1;


        public static final Map<Integer, String[]> STATE_MECHINE = new HashMap<>();


        public void putChar(char c) {
            String newState = STATE_MECHINE.get(state)[getCol(c)];
            if ("sign".equals(newState)) {
                if (c == '-') {
                    sign = -1;
                }
                state = 2;
            } else if ("in_number".equals(newState)) {
                value = value * 10 + c - '0';
                value = sign == 1 ? Math.min(value, Integer.MAX_VALUE) : Math.min(value, -(long) Integer.MIN_VALUE);
                state = 3;
            } else if ("start".equals(newState)) {
                state = 1;
            }else {
                state = 4;
            }
        }

        static {
            STATE_MECHINE.put(1, new String[]{"start", "sign", "in_number", "end"});
            STATE_MECHINE.put(2, new String[]{"end", "end", "in_number", "end"});
            STATE_MECHINE.put(3, new String[]{"end", "end", "in_number", "end"});
            STATE_MECHINE.put(4, new String[]{"end", "end", "end", "end"});

        }

        public static int getCol(char c) {

            if (c == ' ') {
                return 0;
            }
            if (c == '+' || c == '-') {
                return 1;
            }
            if (Character.isDigit(c)) {
                return 2;
            }
            return 3;
        }

    }
}
