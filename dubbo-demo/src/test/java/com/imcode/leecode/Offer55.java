package com.imcode.leecode;

import java.math.BigDecimal;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-02-12 19:13
 */
public class Offer55 extends BaseTree{

    // 0.00001
    //2147483647
    public static void main(String[] args) {
        TreeNode treeNode = new Offer55().buildTree(new int[]{1,2}, new int[]{2,1});

        System.out.println(treeNode);

        double v = new Offer55().myPow(2.00000, -2147483648);



        System.out.println(v);

    }



    public double myPow(double x, int n) {

        if(x == 1D) {
            return 1D;
        }


        if (n == 0) {
            return 1D;
        }
        if (n == 1) {
            return x;
        }
        boolean flag = false;
        if (n < 0) {
            n = Math.abs(n);
            flag = true;
        }
        double result;
        double v = myPow(x, n / 2);
        if (Double.isInfinite(v)) {
            return 0D;
        }
        if (n % 2 == 0) {
            result =  v* v;
        } else {
            result = v * v * x;
        }
        return flag ? 1 / result : result;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {


        return a(preorder , 0 , preorder.length -1 , inorder , 0 , inorder.length -1);
    }

    private TreeNode a(int[] preorder , int left , int right , int[] inorder , int inLeft , int inRight){

        if (inLeft > inRight) {
            return null;
        }
        int root = preorder[left];
        int index = 0;
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == root) {
                index = i;
                break;
            }
        }

        TreeNode treeNode = new TreeNode(root);

        int leftLength = index - inLeft;

        if (leftLength < 0) {
            return treeNode;
        }

        treeNode.left = a(preorder , left + 1 , left  + leftLength , inorder , inLeft , index - 1);

        treeNode.right = a(preorder ,left +leftLength + 1 , right , inorder , index + 1 , inRight);

        return treeNode;
    }



    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        dfs(root , p , q);

        return node;
    }

    private TreeNode node;


    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return false;


        boolean ldfs = dfs(root.left, p, q);

        boolean rdfs = dfs(root.right, p, q);

        if ((ldfs && rdfs) || ((root == p || root == q) && (ldfs || rdfs))) {
            node = root;
        }



        return ldfs || rdfs || (root == p || root == q);
    }



//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//
//
//        if (root.val > p.val && root.val > q.val) {
//            return lowestCommonAncestor(root.left , p , q);
//        }
//
//        if (p.val > root.val && q.val > root.val) {
//            return lowestCommonAncestor(root.right , p , q);
//        }
//        return root;
//    }


    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }
        if (Math.abs(height(root.left) -height(root.right)) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }


    private int height(TreeNode root){

        if (root == null){
            return 0;
        }
        return Math.max(height(root.left) , height(root.right)) +1;
    }



}
