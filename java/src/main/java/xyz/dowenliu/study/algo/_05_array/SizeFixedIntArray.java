package xyz.dowenliu.study.algo._05_array;

import java.util.NoSuchElementException;

/**
 * 定长int数组。提供数据的插入、删除、按照下标随机访问操作
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SizeFixedIntArray implements IntArray {
    private final IntArrayOperator operator;

    /**
     * 构建定长int数组
     *
     * @param capacity 存储容量
     */
    public SizeFixedIntArray(int capacity) {
        this.operator = new IntArrayOperator(new int[capacity], 0);
    }

    @Override
    public int capacity() {
        return this.operator.capacity();
    }

    @Override
    public int size() {
        return this.operator.size();
    }

    @Override
    public boolean insertAt(int index, int value)
            throws ArrayIndexOutOfBoundsException {
        if (this.isFull()) {
            return false;
        }
        this.operator.validateInsertIndex(index);
        return this.operator.doInsertAt(index, value);
    }

    @Override
    public int removeAt(int index)
            throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        return this.operator.removeAt(index);
    }

    @Override
    public void clear() {
        this.operator.clear();
    }

    @Override
    public int get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        return this.operator.get(index);
    }

    @Override
    public int set(int index, int value) throws IndexOutOfBoundsException {
        return this.operator.set(index, value);
    }

    @Override
    public int[] values() {
        return this.operator.values();
    }

    @Override
    public Array<Integer> boxed() {
        return new SizeFixedArray<>(this.capacity(), Integer[]::new);
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return this.operator.isEqualsTo(o);
    }

    @Override
    public int hashCode() {
        return this.operator.doHashCode();
    }

    @Override
    public String toString() {
        return this.operator.doToString();
    }
}
