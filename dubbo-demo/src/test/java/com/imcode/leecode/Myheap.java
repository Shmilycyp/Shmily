package com.imcode.leecode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2022-01-04 16:50
 */
public class Myheap {

    public static void main(String[] args) {
        Myheap myheap = new Myheap(new int[]{4,1,3,2,16,9,10,14,8,7});

        myheap.insert(17);
        System.out.println(myheap);


        PriorityQueue<Myheap> myheaps = new PriorityQueue<>();


        myheaps.add(new Myheap());


    }


    private int[] nodes;

    private int heapLength;

    private static final int DEFAULT_SIZE = 8;


    public Myheap(int size) {
        this.nodes = new int[size];
        this.heapLength = nodes.length;
    }

    public Myheap() {
        this(DEFAULT_SIZE);
    }

    public Myheap(int[] heap){
        nodes = Arrays.copyOf(heap , heap.length);
        heapLength = nodes.length;
        buildMaxHeap();
    }

    public int peek(){
        return nodes[0];
    }


    public int pop(){

        int remove = nodes[0];
        swap(0 , heapLength - 1);
        nodes = Arrays.copyOf(nodes, heapLength - 1);
        heapLength--;
        maxHeapify(0);

        return remove;
    }


    public void increatKey(int index , int num){

        nodes[index] = nodes[index] + num;

        while (index > 0 && nodes[Math.max(parent(index) , 0)] < nodes[index]){
            swap(index , Math.max(parent(index) , 0));
            index = Math.max(parent(index) , 0);
        }
    }

    public void insert(int num){

        nodes = Arrays.copyOf(nodes, nodes.length + 1);
        nodes[nodes.length - 1] = 0;
        heapLength = nodes.length;


        increatKey(nodes.length -1 , num);

    }

    private void buildMaxHeap(){

        int i = (nodes.length -1) / 2;
        for (int j = i; j >= 0; j--) {
            maxHeapify(j);
        }
    }

    private int parent(int index){

        return index / 2 - 1;
    }

    private int left(int index){

        return index * 2 + 1;
    }

    private int right(int index){
        return index * 2 + 2;
    }






    /**
     * 对下标为 index 的元素进行调整
     * @param index 下标地址
     */
    private void maxHeapify(int index){

        // 左右节点的下标
        int left = left(index);
        int right = right(index);

        // 从当前节点和子节点中选出最大的那个下标
        int maxNumIndex = index;
        if(left < heapLength && nodes[left] > nodes[index]){
            maxNumIndex = left;
        }

        if(right < heapLength && nodes[right] > nodes[maxNumIndex]){
            maxNumIndex = right;
        }

        if(maxNumIndex != index){
            // 是其中某一个节点更大 继续调用
            swap(index , maxNumIndex);
            maxHeapify(maxNumIndex);
        }

    }

    private void swap(int index , int otherIndex){

        int temp = nodes[index];
        nodes[index]  = nodes[otherIndex];
        nodes[otherIndex] = temp;
    }


}
