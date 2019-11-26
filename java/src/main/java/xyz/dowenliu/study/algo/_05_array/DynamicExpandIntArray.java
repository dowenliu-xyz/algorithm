package xyz.dowenliu.study.algo._05_array;

import java.util.NoSuchElementException;

/**
 * 自动扩容 {@code int} 数组。
 * <p>
 * 当插入值时，发现容量已满则自动扩容为原容量的两倍。
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class DynamicExpandIntArray implements IntArray {
    private static final int DEFAULT_INITIAL_CAPACITY = 8;

    private IntArrayOperator operator;

    public DynamicExpandIntArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public DynamicExpandIntArray(int initialCapacity) {
        this.operator = new IntArrayOperator(new int[initialCapacity], 0);
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
        this.operator.validateInsertIndex(index);
        if (this.isFull()) {
            expand();
        }
        return this.operator.doInsertAt(index, value);
    }

    private void expand() {
        final double expandRatio = 2.0;
        int[] full = this.operator.getData();
        int[] expanded = new int[(int) (full.length * expandRatio)];
        System.arraycopy(full, 0, expanded, 0, this.size());
        this.operator.setData(expanded);
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
