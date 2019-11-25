package xyz.dowenliu.study.algo._05_array;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 定长int数组。提供数据的插入、删除、按照下标随机访问操作
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SizeFixedIntArray implements IntArray {
    // 内部数组，用于保存数据
    private int[] data;
    // 数组中实际元素个数
    private int size;

    /**
     * 构建定长int数组
     *
     * @param capacity 存储容量
     */
    public SizeFixedIntArray(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    @Override
    public int capacity() {
        return this.data.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        if (index >= this.size) {
            throw new NoSuchElementException("No value at index " + index);
        }
        return this.data[index];
    }

    @Override
    public boolean insertAt(int index, int value)
            throws ArrayIndexOutOfBoundsException {
        if (this.size == this.data.length) {
            return false;
        }
        if (index < 0 || index > this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        System.arraycopy(this.data, index, this.data, index + 1,
                this.size - index);
        this.data[index] = value;
        this.size++;
        return true;
    }

    @Override
    public int removeAt(int index)
            throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        if (index >= this.size) {
            throw new NoSuchElementException("No value at index " + index);
        }
        var removed = this.data[index];
        if (this.size - index + 1 >= 0) {
            System.arraycopy(data, index + 1, data,
                    index + 1 - 1, this.size - index - 1);
        }
        this.data[size - 1] = 0;
        this.size--;
        return removed;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.data[i] = 0;
        }
        this.size = 0;
    }

    @Override
    public int set(int index, int value) throws IndexOutOfBoundsException {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
        var replaced = this.data[index];
        this.data[index] = value;
        return replaced;
    }

    @Override
    public int[] values() {
        return Arrays.copyOfRange(this.data, 0, this.size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntArray)) return false;
        IntArray array = (IntArray) o;
        return size == array.size() &&
                Arrays.equals(this.values(), array.values());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(this.values());
        return result;
    }

    @Override
    public String toString() {
        return "SizeFixedIntArray{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
