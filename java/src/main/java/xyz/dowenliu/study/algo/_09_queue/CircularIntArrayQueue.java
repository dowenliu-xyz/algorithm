package xyz.dowenliu.study.algo._09_queue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 基于数组的大小固定 {@code int} 循环队列
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class CircularIntArrayQueue implements IntQueue {
    private int[] data;
    private int head = 0;
    private int tail = 0;

    /**
     * 构造一个指定容量的 {@code int} 循环队列
     * @param capacity 队列容量。容量应该大于 {@code 0}
     * @throws IllegalArgumentException 容量参数小于等于 {@code 0}
     */
    public CircularIntArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量不能是" + capacity);
        }
        this.data = new int[capacity];
    }

    @Override
    public boolean enqueue(int value) {
        if ((this.tail + 1) % this.data.length == this.head) {
            return false;
        }
        this.data[this.tail++] = value;
        this.tail = this.tail % this.data.length;
        return true;
    }

    @Override
    public int dequeue() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        int ret = this.data[this.head++];
        this.head = this.head % this.data.length;
        return ret;
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
        int[] values;
        if (this.head <= this.tail) {
            values = Arrays.copyOfRange(this.data, this.head, this.tail);
        } else {
            values = new int[this.data.length + this.tail - this.head];
            System.arraycopy(this.data, this.head, values, 0, this.data.length - this.head);
            System.arraycopy(this.data, 0, values, this.data.length - this.head, this.tail);
        }
        return "CircularIntArrayQueue" + Arrays.toString(values) +
                "[capacity:" + this.data.length + "]";
    }
}
