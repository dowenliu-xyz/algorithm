package xyz.dowenliu.study.algo._18_hashtable;

import org.jetbrains.annotations.NotNull;

/**
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SimpleLRUCache<K, V> {
    private static class ListNode<K, V> {
        private K key;
        private V value;
        private ListNode<K, V> next;

        public ListNode(K key, V value, ListNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private final int capacity;
    private int size;
    @NotNull
    private final ListNode<K, V> listHead; // 从头部移除，尾部添加
    @NotNull
    private ListNode<K, V> tail;
    @NotNull
    private final HashTable<K, ListNode<K, V>> table;

    public SimpleLRUCache(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("缓存容量至少为1");
        this.capacity = capacity;
        this.listHead = new ListNode<>(null, null, null);
        this.tail = this.listHead;
        this.table = new HashTable<>();
    }

    public V get(K key) {
        ListNode<K, V> node = this.table.get(key);
        if (node == null) {
            return null;
        }
        // remove from list
        V value = node.value;
        ListNode<K, V> next = node.next;
        if (next != null) {
            K nextKey = next.key;
            V nextValue = next.value;
            node.key = nextKey;
            node.value = nextValue;
            node.next = next.next;
            next.next = null; // help GC
            this.table.put(nextKey, node);
        } else {
            // 就是尾部
            return value;
        }
        // insert as list tail
        ListNode<K, V> newTail = new ListNode<>(key, value, null);
        this.tail.next = newTail;
        this.tail = newTail;
        this.table.put(key, newTail);
        return node.value;
    }

    public void cache(K key, V value) {
        if (this.size == this.capacity) {
            // 移除最早的元素
            ListNode<K, V> next = this.listHead.next;
            if (next != null) {
                this.listHead.next = next.next;
                next.next = null; // help GC
                this.table.remove(next.key);
                this.size--;
            }
        }
        ListNode<K, V> newTail = new ListNode<>(key, value, null);
        this.tail.next = newTail;
        this.tail = newTail;
        this.table.put(key, newTail);
        this.size++;
    }
}
