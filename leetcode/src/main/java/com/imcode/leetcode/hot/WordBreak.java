package com.imcode.leetcode.hot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author : Chen Yun Peng
 * @Date : 2023/3/2
 * @Description :
 */
public class WordBreak {


    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("apple");
        strings.add("pen");
        strings.add("applepen");
        strings.add("pine");
        strings.add("pineapple");

        // "cat","cats","and","sand","dog"

        // ["apple","pen","applepen","pine","pineapple"]


        for (String applepenapple : new WordBreak().wordBreak("pineapplepenapple", strings)) {

            System.out.println(applepenapple);
        }


    }

    public List<String> wordBreak(String s, List<String> wordDict) {

        int maxLength = 0;
        for (String s1 : wordDict) {
            maxLength = Math.max(s1.length(), maxLength);
        }

        LinkedList<String> result = new LinkedList<>();
        LinkedList<String> cur = new LinkedList<>();
        doFind(s, 0, wordDict, maxLength, result, cur);
        return result;
    }

    public void doFind(String s, int initIndex, List<String> dict, int maxWordLength,
                       LinkedList<String> result, LinkedList<String> cur) {

        List<String> nextWord = findNextWord(s, initIndex, dict, maxWordLength);
        if (nextWord.size() == 0) {
            return;
        }
        for (String s1 : nextWord) {
            cur.add(s1);
            if (s1.length() + initIndex == s.length()) {
                result.add(String.join(" ", cur));
                cur.removeLast();
                continue;
            }
            doFind(s, initIndex + s1.length(), dict, maxWordLength, result, cur);
            cur.removeLast();
        }
    }

    /**
     * 获取下一个文字
     * @param s
     * @param initIndex
     * @param dict
     * @return 下一个文字的下标，当返回 -1 时代表已经没有了或者没有满足条件的了
     */
    private LinkedList<String> findNextWord(String s, int initIndex, List<String> dict, int maxWordLength) {
        LinkedList<String> strings = new LinkedList<>();
        for (int i = initIndex; i < Math.min(s.length(), initIndex + maxWordLength); i++) {
            String str = s.substring(initIndex, i + 1);
            for (String s1 : dict) {
                if (s1.equals(str)) {
                    strings.add(s1);
                    break;
                }
            }
        }
        return strings;
    }


//    public boolean wordBreak(String s, List<String> wordDict) {
//
//        boolean[] dp = new boolean[s.length()];
//        for (int i = 0; i < s.length(); i++) {
//            for (String s1 : wordDict) {
//                if (i + 1 >= s1.length()) {
//                    if (s1.equals(s.substring( i - s1.length()  +1,i +1)) && (i - s1.length() + 1 == 0 || dp[i - s1.length()]) ) {
//                        dp[i] = true;
//                        break;
//                    }
//                }
//            }
//        }
//        return dp[dp.length - 1];
//    }

}
