package com.imcode.leecode;

import lombok.Data;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-02-12 19:14
 */
public class BaseTree {

    @Data
    public static class Node {

        private int value;

        private MyBrTree.Node left;

        private MyBrTree.Node right;

        private MyBrTree.Node p;

        public Node(int value) {
            this.value = value;
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
