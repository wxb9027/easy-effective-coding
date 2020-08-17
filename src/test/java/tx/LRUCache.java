package tx;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @Description: TODO
 * @Author TODO
 * @DATE 20/7/7 20:00
 * @Version 1.0
 **/
public class LRUCache {
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    private HashMap<Integer,Node> cache;
    private DoubleLinkList linkList;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.linkList = new DoubleLinkList();
    }

    public int get(int key) {
        if(!this.cache.containsKey(key)){
            return -1;
        }
        Node node = this.cache.get(key);
        // 放入linklist 元素存在，直接移动
        this.linkList.moveToTail(node);
        return node.getValue();
    }

    public void put(int key, int value) {
        // 容量校验
        if (this.cache.size() > this.capacity){
            Node node = this.linkList.removeFromHead();
            this.cache.remove(node.value);
        }
        Node node = new Node();
        node.setKey(key);
        node.setValue(value);
        this.cache.put(key,node);
        this.linkList.putToTail(node);
    }

    //1 定义双向链表  头尾节点 放入 删除
    private class DoubleLinkList{
        private Node head;
        private Node tail;

        public void putToTail(Node node){
            //第一次放入
            if(null == head){
                head = node;
                tail = node;
            }
            tail.next = node;
            node.pre = tail;
            tail =node;

        }
        public Node removeFromHead(){
            Node removeNode = head;
            //最后一个元素
            if(head == tail){
               head = null;
               tail =null;
            }
            head = head.next;
            head.pre = null;
            return removeNode;
        }
        /***
         * 移动元素
         */
        public void moveToTail(Node node){
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }

    }
    private class Node{
        private Node next;
        private Node pre;
        private int key;
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }






}
