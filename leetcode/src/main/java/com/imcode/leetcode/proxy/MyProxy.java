package com.imcode.leetcode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/28
 * @Description :
 */
public class MyProxy {


    public static void main(String[] args) {


        Proxy.newProxyInstance(MyProxy.class.getClassLoader(), new Class[]{EmptyInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });


    }

    public static class MyClass implements EmptyInterface {


    }


    public static interface EmptyInterface {



    }


}
