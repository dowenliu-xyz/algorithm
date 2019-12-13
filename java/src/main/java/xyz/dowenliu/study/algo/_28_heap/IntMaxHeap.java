package xyz.dowenliu.study.algo._28_heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@code int} 元素的大顶堆
 * <p>create at 2019/12/9</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntMaxHeap {
    /**
     * 堆存储
     */
    private int[] data;
    /**
     * 已存储的数据数量
     */
    private int size;

    /**
     * 构造大顶堆
     *
     * @param capacity 堆容量。应为正整数
     * @throws IllegalArgumentException 如果容量参数为 {@code 0} 或负数
     */
    public IntMaxHeap(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("堆大小不能为" + capacity);
        this.data = new int[capacity];
        this.size = 0;
    }

    /**
     * 返回当前堆元素数量
     *
     * @return 当前堆元素数量
     */
    public int size() {
        return this.size;
    }

    /**
     * 向堆中插入一个元素
     *
     * @param value 要插入的元素
     * @return 如果堆已满，返回 {@code false} ；否则，返回 {@code true}
     */
    public boolean add(int value) {
        if (this.size >= this.data.length) return false;
        int i = this.size;
        this.data[i] = value;
        this.size++;
        while (i > 0 && this.data[i] > this.data[(i - 1) / 2]) { // 自下往上堆化
            swap(this.data, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
        return true;
    }

    private static void swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     * @throws NoSuchElementException 堆为空
     */
    public int getMax() throws NoSuchElementException {
        if (this.size == 0) throw new NoSuchElementException("堆为空");
        return this.data[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return 堆顶元素
     * @throws NoSuchElementException 堆为空
     */
    public int removeMax() throws NoSuchElementException {
        if (this.size == 0) throw new NoSuchElementException("堆为空");
        int max = this.data[0];
        this.data[0] = this.data[this.size - 1];
        this.size--;
        heapify(this.data, this.size, 0);
        return max;
    }

    private static void heapify(int[] array, int size, int i) {
        while (true) {
            int maxPos = i;
            int left = i * 2 + 1;
            if (left < size && array[maxPos] < array[left])
                // 有左子节点且比左子节点小
                maxPos = left;
            int right = i * 2 + 2;
            if (right < size && array[maxPos] < array[right])
                // 有右子节点且比右子节点小
                maxPos = right;
            if (maxPos == i) break; // 比左右节点都大
            swap(array, i, maxPos);
            i = maxPos;
        }
    }

    public int[] toArray() {
        return Arrays.copyOfRange(this.data, 0, this.size);
    }
}
