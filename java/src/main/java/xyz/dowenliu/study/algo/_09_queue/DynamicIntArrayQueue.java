package xyz.dowenliu.study.algo._09_queue;

import java.util.Arrays;

/**
 * 基于数组的自动扩容 {@code int} 线性队列
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class DynamicIntArrayQueue extends IntArrayQueue {
    public DynamicIntArrayQueue(int capacity) {
        super(capacity);
    }

    public DynamicIntArrayQueue() {
        this(8);
    }

    @Override
    public boolean enqueue(int value) {
        if (this.tail == this.data.length && this.head == 0) {
            int[] data = new int[this.data.length * 2];
            System.arraycopy(
                    this.data, 0, data, 0, this.data.length);
            this.data = data;
        }
        return super.enqueue(value);
    }

    @Override
    public String toString() {
        int[] values = Arrays.copyOfRange(this.data, this.head, this.tail);
        return "DynamicIntArrayQueue" + Arrays.toString(values) +
                "[capacity:" + this.data.length + "]";
    }
}
