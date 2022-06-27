package com.imcode.ziya;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.SneakyThrows;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-05-11 21:44
 */
public class Hello {

    public static class A{
        static {
            System.out.println("A init");
            new B();
        }
        public static int a = 200;
    }

    public static class B {

        static {

            new A();
            System.out.println("B init");
        }
    }


    public static class Fa {

        public static String A = "A";
        static {
            String ss = "";
            System.out.println("FA init ");
        }

    }

    public static class Son extends Fa{

        public static String w = "C";
        static {
            System.out.println("Son init ");
        }
    }

    public static class Test {

        public static int i;
        public static Test isntall = new Test();
        public static int j = 1;

        public Test() {
            i++;
            j++;
        }
    }

    public static class LockTest {

        public boolean aleady = false;
        @SneakyThrows
        public LockTest () {
            Thread.sleep(5000);
            aleady = true;
        }



    }

    private volatile static LockTest lockTest;

    public static void main(String[] args) {



        new Thread(() -> {
            lockTest = new LockTest();
            System.out.println("init done");

        }).start();
        new Thread(() -> {
            while (lockTest == null);
            System.out.println(lockTest.aleady);
        }).start();

        System.out.println("main done");


        while (true);
    }

}
