package com.imcode.leecode;

import java.util.Stack;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-25 21:35
 */
public class Offer24 {

    public static void main(String[] args) {

        Offer24 offer24 = new Offer24();

        ListNode listNode = new ListNode(1);
        listNode.next= new ListNode(2);
        ListNode listNode1 = offer24.reverseList(listNode);
        System.out.println(listNode1);

    }

    private Stack<ListNode> stack = new Stack<>();

    public ListNode reverseList(ListNode head) {

        ListNode temp  = head;
        while (temp.next != null) {
            stack.push(temp);
            ListNode next = temp.next;
            temp.next =null;
            temp = next;
        }

        ListNode newHead =temp;
        while (!stack.isEmpty()){
            temp.next =stack.pop();
            temp = temp.next;
        }

        return newHead;
    }



   public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }

}
