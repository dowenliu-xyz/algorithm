package xyz.dowenliu.study.algo._05_array;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Int数组操作封装。
 * <p>
 * 用于提供方法模板，代理实现 IntArray
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
class IntArrayOperator implements IntArray {
    private int[] data;
    private int size;

    public IntArrayOperator(int[] data, int size) {
        this.data = data;
        this.size = size;
    }

    int[] getData() {
        return data;
    }

    void setData(int[] data) {
        this.data = data;
    }

    @Override
    public int capacity() {
        return this.data.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * !!! DO NOT USE THIS TEMPLATE METHOD !!!
     * It just throws an UnsupportedOperationException.
     *
     * @see #validateInsertIndex(int)
     * @see #doInsertAt(int, int)
     */
    @Override
    public boolean insertAt(int index, int value)
            throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException();
    }

    void validateInsertIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    boolean doInsertAt(int index, int value) {
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
    public int get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        if (index >= this.size) {
            throw new NoSuchElementException("No value at index " + index);
        }
        return this.data[index];
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

    boolean isEqualsTo(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntArray)) return false;
        IntArray array = (IntArray) o;
        return Arrays.equals(this.values(), array.values());
    }

    int doHashCode() {
        return Arrays.hashCode(this.values());
    }

    String doToString() {
        return Arrays.toString(this.values());
    }
}
