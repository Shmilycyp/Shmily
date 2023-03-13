package com.imcode.leetcode.hot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/12
 * @Description :
 */
public class DiffWaysToCompute {




    public static void main(String[] args) {
        List<Integer> result = new DiffWaysToCompute().diffWaysToCompute("2*3-4*5");
        result.forEach(System.out::println);
    }


    // 输入：expression = "2*3-4*5"
    //输出：[-34,-14,-10,-10,10]
    //解释：
    //(2*(3-(4*5))) = -34
    //((2*3)-(4*5)) = -14
    //((2*(3-4))*5) = -10
    //(2*((3-4)*5)) = -10
    //(((2*3)-4)*5) = 10
    // 
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

    public List<Integer> diffWaysToCompute(String expression) {

        // 先打平到数组上
        List<Object> parse = parse(expression);
        ArrayList<Integer> result = new ArrayList<>();
        dfs(result, parse, 0, 0, '+');
        return result;
    }


    private static void dfs(List<Integer> result, List<Object> parse, Integer curResult, int index, char op) {


        if (index >= parse.size() - 1) {
            result.add(cacel(curResult, (int)parse.get(index), op));
            return;
        }
        // 确定当前值是多少 = 不融合 或与下一个融合
        Integer first = cacel(curResult, (int) parse.get(index), op);
        dfs(result, parse, first, index + 2, (char) parse.get(index + 1));

        // 如果当前的下标还支持一次运算
        if (index + 3 <= parse.size() - 1) {
            Integer merger = cacel((int) parse.get(index), (int) parse.get(index + 2), (char) parse.get(index + 1));
            Integer second = cacel(curResult, merger, op);

            // 如果后面还有最少一个值，则继续
            if (index + 4 <= parse.size() - 1) {
                dfs(result, parse, second, index + 4, (char) parse.get(index + 3));
            } else {
                result.add(second);
            }
        }

    }








    private static List<Object> parse(String expression) {

        LinkedList<Object> result = new LinkedList<>();

        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (atomicInteger.get() != expression.length()) {
            result.add(getNextNumber(expression, atomicInteger));
            if (atomicInteger.get() < expression.length() - 1) {
                result.add(expression.charAt(atomicInteger.get()));
                atomicInteger.incrementAndGet();
            }
        }
        return result;
    }


    private static Integer cacel(int num1, int num2, char c) {

        switch (c) {

            case '*':
                return num1 * num2;

            case '-':
                return num1 - num2;
            case '+':
                return num1 + num2;
            default:
                throw new RuntimeException("");
        }
    }

    private static Integer getNextNumber(String expression, AtomicInteger curIndex) {
        StringBuilder num = new StringBuilder(2);
        while (curIndex.get() < expression.length() && isNumber(expression.charAt(curIndex.get()))) {
            num.append(expression.charAt(curIndex.get()));
            curIndex.incrementAndGet();
        }
        if (num.length() == 0) {
            return null;
        }
        return Integer.parseInt(num.toString());
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }


}
