package com.imcode.leecode;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-25 21:22
 */
public class ReversePrint {

    private int size;

    private int index;

    private int[] nod;

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        a(head);
        return nod;
    }

    private void a(ListNode node){


        if (node.next != null) {
            size ++;
            a(node.next);
        }else {
            nod = new int[size + 1];
        }
        nod[index] = node.val;
        index++;
    }

    public class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
}
