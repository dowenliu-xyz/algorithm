package xyz.dowenliu.study.algo._09_queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 基于数组的 {@code int} 线性队列。
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntArrayQueue implements IntQueue {
    private int[] data;
    private int head = 0;
    private int tail = 0;

    /**
     * 构造一个 {@code int} 线性队列。
     * @param capacity 队列容量。需要大于 {@code 0}
     * @throws IllegalArgumentException 容量参数小于等于 {@code 0}
     */
    public IntArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量不能是" + capacity);
        }
        this.data = new int[capacity];
    }

    @Override
    public boolean enqueue(int value) {
        if (this.tail == this.data.length) {
            if (this.head == 0) {
                return false;
            }
            System.arraycopy(this.data, this.head, this.data, 0, this.tail - this.head);
            this.tail = this.tail - this.head;
            this.head = 0;
        }
        this.data[this.tail++] = value;
        return true;
    }

    @Override
    public int dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.data[this.head++];
    }

    @Override
    public int head() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.data[this.head];
    }

    @Override
    public boolean isEmpty() {
        return this.head == this.tail;
    }

    @Override
    public String toString() {
        int[] values = Arrays.copyOfRange(this.data, this.head, this.tail);
        return "IntArrayQueue" + Arrays.toString(values) +
                "[capacity:" + this.data.length + "]";
    }
}
