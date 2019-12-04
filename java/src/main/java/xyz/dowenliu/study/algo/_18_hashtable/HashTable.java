package xyz.dowenliu.study.algo._18_hashtable;

import java.util.Objects;

/**
 * 散列表
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class HashTable<K, V> {
    /**
     * 默认初始容量
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 8;
    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75F;

    private static class Entry<K, V> {
        final K key;

        V value;


        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 表数组
     */
    private Entry<K, V>[] table;
    /**
     * 元素数量
     */
    private int size = 0;
    /**
     * 表空间用量
     */
    private int use = 0;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    public int size() {
        return this.size;
    }

    public V put(K key, V value) {
        int index = this.hash(key, this.table.length);
        Entry<K, V> entry = this.table[index];
        if (entry == null) {
            // 新增节点
            this.table[index] = new Entry<>(key, value, null);
            this.size++;
            this.use++;
            if (use >= table.length * LOAD_FACTOR) {
                // 需要扩容
                this.doubleCapacity();
            }
            return null;
        } else {
            while (true) {
                if (Objects.equals(entry.key, key)) {
                    // 替换
                    V origin = entry.value;
                    entry.value = value;
                    return origin;
                } else {
                    if (entry.next == null) {
                        // 新增
                        entry.next = new Entry<>(key, value, null);
                        this.size++;
                        return null;
                    }
                    entry = entry.next;
                }
            }
        }
    }

    private void doubleCapacity() {
        @SuppressWarnings("unchecked") Entry<K, V>[] newTable =
                new Entry[this.table.length * 2];
        int use = 0;
        for (Entry<K, V> old : this.table) {
            if (old == null) {
                continue;
            }
            while (old != null) {
                int index = this.hash(old.key, newTable.length);
                Entry<K, V> entry = newTable[index];
                if (entry == null) {
                    newTable[index] = new Entry<>(old.key, old.value, null);
                    use++;
                } else {
                    newTable[index] = new Entry<>(old.key, old.value, entry);
                }
                old = old.next;
            }
        }
        this.table = newTable;
        this.use = use;
    }

    private int hash(K key, int tableCapacity) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        return hash ^ hash >>> 16 % tableCapacity;
    }


    public V remove(K key) {
        int index = this.hash(key, this.table.length);
        Entry<K, V> entry = this.table[index];
        if (entry == null) {
            return null;
        }
        Entry<K, V> pre = null;
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                if (pre != null) {
                    pre.next = entry.next;
                    entry.next = null; // help GC
                    this.size--;
                    return entry.value;
                }
                Entry<K, V> next = entry.next;
                this.table[index] = next;
                entry.next = null; // help GC
                this.size--;
                if (next == null) {
                    this.use--;
                }
                return entry.value;
            }
            pre = entry;
            entry = entry.next;
        }
        return null;
    }


    public V get(K key) {
        int index = this.hash(key, this.table.length);
        Entry<K, V> entry = this.table[index];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean exist(K key) {
        int index = this.hash(key, this.table.length);
        Entry<K, V> entry = this.table[index];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }
}
