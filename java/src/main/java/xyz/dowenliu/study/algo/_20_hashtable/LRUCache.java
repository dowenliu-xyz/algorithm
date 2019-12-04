package xyz.dowenliu.study.algo._20_hashtable;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * 基于散列表的LRU算法
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class LRUCache<K, V> {
    /**
     * 双向链表节点
     */
    private static class DNode<K, V> {
        final K key;
        V value;
        DNode<K, V> previous;
        DNode<K, V> next;

        public DNode() {
            this.key = null;
            this.value = null;
        }

        public DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private DNode<K, V> headNode;
    private DNode<K, V> tailNode;
    private int length;
    private final int capacity;
    private HashMap<K, DNode<K, V>> table;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("缓存容量最少也需要1");
        }
        this.capacity = capacity;
        this.length = 0;

        this.headNode = new DNode<>();
        this.tailNode = new DNode<>();

        this.headNode.next = this.tailNode;
        this.tailNode.previous = this.headNode;

        this.table = new HashMap<>();
    }

    public void add(K key, V value) {
        DNode<K, V> node = this.table.get(key);
        if (node == null) {
            if (this.length == this.capacity) {
                this.popTail();
            }
            DNode<K, V> newNode = new DNode<>(key, value);
            this.addNode(newNode);
            this.length++;
            this.table.put(key, newNode);
        } else {
            node.value = value;
            this.moveToHead(node);
        }
    }

    private void popTail() {
        DNode<K, V> node = this.tailNode.previous;
        if (node == this.headNode) return;
        this.removeNode(node);
        this.length--;
        this.table.remove(node.key);
    }

    private void addNode(@NotNull DNode<K, V> node) {
        node.next = this.headNode.next;
        node.previous = this.headNode;

        this.headNode.next.previous = node;
        this.headNode.next = node;
    }

    private void removeNode(@NotNull DNode<K, V> node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    private void moveToHead(@NotNull DNode<K, V> node) {
        this.removeNode(node);
        this.addNode(node);
    }

    public V get(K key) {
        DNode<K, V> node = this.table.get(key);
        if (node == null) {
            return null;
        }
        this.moveToHead(node);
        return node.value;
    }

    public void remove(K key) {
        DNode<K, V> node = this.table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        this.length--;
        this.table.remove(key);
    }
}
