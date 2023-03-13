package com.imcode.leetcode.nativi;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/23
 * @Description :
 */
public class NativeTest {
    public native int add(int a, int b);

    static {
        System.loadLibrary("NativeLib");
    }

    public static void main(String[] args) {
        NativeTest test = new NativeTest();
        int sum = test.add(1, 2);
        System.out.println("sum = " + sum);
    }
}
