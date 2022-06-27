package com.imcode.leecode;

import java.util.Stack;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-24 22:17
 */
public class MinStack {

    private final Stack<Integer> stack= new Stack<>();

    private final Stack<Integer> minStack = new Stack<>();


    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {

        stack.push(x);

        if (minStack.size() == 0){
            minStack.push(x);
        }else if(minStack.peek() >= x){
            minStack.push(x);
        }
    }

    public void pop() {

        Integer pop = stack.pop();
        if(pop.equals(minStack.peek())){
            minStack.pop();
        }

    }

    public int top() {

        return stack.peek();
    }

    public int min() {

        return minStack.peek();
    }

}
