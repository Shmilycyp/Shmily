package com.imcode.leetcode.hot;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/24
 * @Description :
 */
public class NumDecodings {

    public static void main(String[] args) {
        System.out.println(new NumDecodings().numDecodings("2611055971756562"));
    }

    // 1 -> 1
    // 11 ->   (1, 1)    (11)
    // 112 ->  (1,1,2)   (11, 2)  (1, 12)
    // 1123 -> (1,1,2,3) (11,2,3) (1, 12, 3) (1, 1, 23) (11,23)



    public int numDecodings(String s) {
        if (s.length() == 1) {
            return s.charAt(0) == '0' ? 0 : 1;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] nums = new int[chars.length];
        nums[0] = 1;
        nums[1] = s.charAt(1) != '0' && isIllegal(s.substring(0, 2)) ? 2 : 1;

        for (int i = 2; i < chars.length; i++) {
            char aChar = chars[i];

            if (aChar == '0') {
                if (isIllegal(s.substring(i - 1, Math.min(i + 1, s.length())))) {
                    nums[i] = nums[i - 1] - 1;
                } else {
                    return 0;
                }
            } else if (isIllegal(s.substring(i -1 , Math.min(i + 1, s.length())))) {
                nums[i] = nums[i -1] + nums[i - 2];
            } else {
                nums[i] = nums[i - 1];
            }
        }

        return nums[nums.length - 1];
    }

    private static boolean isIllegal(String str) {
        return str.charAt(0) != '0' && Integer.parseInt(str) < 27;
    }

}
