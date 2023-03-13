package com.imcode.leetcode.everything;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/9
 * @Description :
 */
public class ThreadLocalTry {


    private static final ThreadLocal<String> NORMAL_USER_NAME = ThreadLocal.withInitial(() -> "this is normal user name init");
    private static final InheritableThreadLocal<String> INHERITABLE_USER_NAME = new InheritableThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {

        NORMAL_USER_NAME.set("main set normal user name");


        new Thread(() -> System.out.println(NORMAL_USER_NAME.get())).start();



        INHERITABLE_USER_NAME.set("main set inhertiable user name");

        new Thread(() -> System.out.println(INHERITABLE_USER_NAME.get())).start();
        INHERITABLE_USER_NAME.remove();

        Thread.sleep(1000);
    }





}
