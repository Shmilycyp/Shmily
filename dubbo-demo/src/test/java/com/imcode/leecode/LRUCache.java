package com.imcode.leecode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author cyp chenyunpeng@xihuanwu.com
 * @date 2021-12-11 15:25
 */
public class LRUCache {

    // ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
    //[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
    public static void main(String[] args) {


        LRUCache lruCache = new LRUCache(3);


        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(1));

        lruCache.put(5,5);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));

        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(5));

    }


    private Map<Integer , DoubleNode> map;

    /**
     * 容量
     */
    private int capacity;

    private DoubleNode first;

    private DoubleNode last;


    public LRUCache(int capacity) {

        this.capacity = capacity;

        map = new HashMap<>(capacity);

    }

    public int get(int key) {


        // 直接从Map中获取
        DoubleNode doubleNode = map.get(key);

        if(doubleNode != null){

            // 移动node到头节点
            moveToFirst(doubleNode);

            return doubleNode.value;
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {

        // 查询是否存在
        DoubleNode doubleNode = map.get(key);

        if(doubleNode != null){


            doubleNode.value = value;

            moveToFirst(doubleNode);

        }else {

            if(capacity == map.size()){

                // 删除最后一个
                delLast();


            }
            // 新增一个node
            DoubleNode node = new DoubleNode();

            node.key = key;
            node.value = value;

            if(first != null){
                first.pre = node;
                node.next = first;
            }
            first = node;
            if(last == null){
                last = node;
            }

            map.put(key , node);

        }

    }


    /**
     * 删除最后一个节点
     */
    private void delLast(){



        // map中删除
        map.remove(last.key);

        // 删除尾节点
        last = last.pre;
        last.next = null;
    }


    /**
     * 移动节点到头节点
     * @param doubleNode 双向链表
     */
    private void moveToFirst(DoubleNode doubleNode){

        if(first == doubleNode){
            return;
        }

        if(last == doubleNode){
            last = last.pre;
            last.next = null;
        }

        // 先把当前节点从原来位置删除

        if(first != null){
            first.pre = doubleNode;
            doubleNode.next = first;
        }
        first = doubleNode;


        doubleNode.pre = null;

    }



    class DoubleNode{


        public int key;

        public int value;

        public DoubleNode pre;


        public DoubleNode next;




    }


}
