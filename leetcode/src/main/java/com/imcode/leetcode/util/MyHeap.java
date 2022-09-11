package com.imcode.leetcode.util;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @author shmilyah@163.com
 * 堆的实现
 * @date 2022-09-07
 */
public class MyHeap<T> extends AbstractQueue<T> {



    private int[] heap;


    private void maxHeap(int[] heap, int i) {
        int left = left(i);
        int right = right(i);
        int largst;
        if (left <= heap.length - 1 && heap[left] > heap[i]) {
            largst = left;
        } else {
            largst = i;
        }
        if (right <= heap.length - 1 && heap[right] > heap[largst] ) {
            largst = right;
        }
        if (largst != i) {
            // 说明此时i不是最大的，为了维护堆的性质，需要下移，把最大的抬上去
            int i1 = heap[largst];
            heap[largst] = heap[i];
            heap[i] = i1;
            maxHeap(heap, largst);
        }
    }


    private void buildHeap(int[] heap) {
        // 自底像上建堆
        for (int i = heap.length / 2; i >= 0 ; i--) {
            maxHeap(heap, i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public int size() {
        return heap.length - 1;
    }


    /**
     * 获取父节点
     * @param index 节点
     * @return 父节点
     */
    public int parent(int index) {
        return index / 2 - 1;
    }

    /**
     * 左孩子
     */
    public int left(int index) {
        return index * 2 + 1;
    }

    /**
     * 右孩子
     */
    public int right(int index) {
        return index * 2 + 2;
    }


    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }
}
