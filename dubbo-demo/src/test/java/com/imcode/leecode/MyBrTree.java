package com.imcode.leecode;

import lombok.Data;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-16 14:22
 */
public class MyBrTree {



    public void del(Node node){

        if(node.left ==  null){
            transplant(node , node.right);
        }else if(node.right == null){
            transplant(node , node.left);
        }else {
            // 左右节点都有值
            Node successor = treeSuccessor(node);

            if(successor.p != node){

                // 交货后继和后继的右边节点
                transplant(successor , successor.right);
                successor.right = node.right;
                successor.right.p = successor;
            }

            transplant(node , successor);
            successor.left = node.left;
            successor.left.p = successor;

        }
    }


    /**
     * 仅替换
     * @param u 将被替换的NODE
     * @param v 要替换的NODE
     */
    public void transplant(Node u ,Node v){

        if(u.p == null){
            root = v;
        }else if (u.p.left == u){
            u.p.left = v;
        }else {
            u.p.right = v;
        }
        if(v != null){
            v.p = u.p;
        }

    }

    private Node root;

    public void insert(int value){

        Node temp = root;
        Node y = null;
        while (temp!= null){
            y = temp;
            if(value < temp.value) {
                temp =temp.left;
            }else {
                temp = temp.right;
            }
        }
        if(y == null){
            root = new Node(value);
        }else {

            if(value < y.value){
                y.left = new Node(value);
            }else {
                y.right = new Node(value);
            }
        }
    }


    public Node treePre(Node node){

        if(node.left != null){
            return maxValue(node.left);
        }

        //
        Node p = node.p;

        while (p != null && p.left == node){
            node = p;
            p = p.p;
        }
        return p;
    }


    // 查找后继节点
    public Node treeSuccessor(Node node){

        if(node.right != null) {
            return minValue(node.right);
        }
        Node p = node.p;
        while (p != null && p.right == node){
            node = p;
            p = p.p;
        }
        return p;
    }


    public Node minValue(Node p){
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }


    public Node maxValue(Node p){

        while (p.right != null){
            p = p.right;
        }
        return p;
    }


    public void inorderTreeWalk(Node node){
        if(node != null) {
            inorderTreeWalk(node.left);
            System.out.println(node.value);
            inorderTreeWalk(node.right);
        }
    }

    public Node inerativeTreeSearch (Node tree , int x) {

        while (tree != null && tree.value != x){
            if(x < tree.value){
                tree = tree.left;
            }else {
                tree = tree.right;
            }
        }
        return tree;
    }







    @Data
    public static class Node {

        private int value;

        private Node left;

        private Node right;

        private Node p;

        public Node(int value) {
            this.value = value;
        }
    }

}
