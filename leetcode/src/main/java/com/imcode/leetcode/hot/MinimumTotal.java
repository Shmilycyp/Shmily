package com.imcode.leetcode.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/2/25
 * @Description :
 */
public class MinimumTotal {

    public static void main(String[] args) {

        ArrayList<List<Integer>> lists = new ArrayList<>();


        ArrayList<Integer> first = new ArrayList<>();
        first.add(2);

        ArrayList<Integer> second = new ArrayList<>();
        second.add(3);
        second.add(4);

        ArrayList<Integer> third = new ArrayList<>();
        third.add(6);
        third.add(5);
        third.add(7);

        ArrayList<Integer> fourth = new ArrayList<>();
        fourth.add(4);
        fourth.add(1);
        fourth.add(8);
        fourth.add(3);

        lists.add(first);
        lists.add(second);
        lists.add(third);
        lists.add(fourth);

        System.out.println(new MinimumTotal().minimumTotal(lists));

    }



    public int minimumTotal(List<List<Integer>> triangle) {
        int maxSize = triangle.get(triangle.size() - 1).size();

        int[] befor = new int[maxSize];
        Arrays.fill(befor, Integer.MAX_VALUE);
        int[] current = new int[maxSize];

        befor[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {

            List<Integer> integers = triangle.get(i);

            for (int j = 0; j < integers.size(); j++) {
                current[j] = Math.min(befor[j], befor[Math.max(j - 1, 0)]) + integers.get(j);
            }
            Arrays.fill(befor, Integer.MAX_VALUE);
            System.arraycopy(current, 0, befor, 0, integers.size());
        }

        return Arrays.stream(befor).min().getAsInt();
    }






}
