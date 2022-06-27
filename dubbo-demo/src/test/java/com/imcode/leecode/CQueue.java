package com.imcode.leecode;

import java.util.Stack;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-24 22:13
 */
public class CQueue {

    private Stack<Integer> input =new Stack<>();

    private Stack<Integer> output = new Stack<>();

    public CQueue() {

    }

    public void appendTail(int value) {

        input.push(value);

    }

    public int deleteHead() {

        if (output.size() > 0) {
            return output.pop();
        }

        if (input.size() > 0){

            while (input.size() > 0) {
                output.push(input.pop());
            }
            return output.pop();
        }else {
            return -1;
        }

    }
}
