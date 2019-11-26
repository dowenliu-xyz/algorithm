package xyz.dowenliu.study.algo._05_array;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 泛型数组操作封装。
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
class ArrayOperator<E> implements Array<E> {
    private E[] data;
    private int size;

    public ArrayOperator(E[] data, int size) {
        this.data = data;
        this.size = size;
    }

    E[] getData() {
        return data;
    }

    void setData(E[] data) {
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
     * @see #doInsertAt(int, E)
     */
    @Override
    public boolean insertAt(int index, E e)
            throws ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException();
    }

    void validateInsertIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }

    boolean doInsertAt(int index, E e) {
        System.arraycopy(this.data, index, this.data, index + 1,
                this.size - index);
        this.data[index] = e;
        this.size++;
        return true;
    }

    @Override
    public E removeAt(int index)
            throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        //noinspection DuplicatedCode
        if (index >= this.size) {
            throw new NoSuchElementException("No value at index " + index);
        }
        var removed = this.data[index];
        if (this.size - index + 1 >= 0) {
            System.arraycopy(data, index + 1, data,
                    index + 1 - 1, this.size - index - 1);
        }
        this.data[size - 1] = null;
        this.size--;
        return removed;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.data[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        if (index >= this.size) {
            throw new NoSuchElementException("No element at index " + index);
        }
        return this.data[index];
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
        var replaced = this.data[index];
        this.data[index] = e;
        return replaced;
    }

    @Override
    public E[] values() {
        return Arrays.copyOfRange(this.data, 0, this.size);
    }

    boolean isEqualsTo(Object o) {
        if (this == o) return true;
        if (!(o instanceof Array)) return false;
        Array array = (Array) o;
        return Arrays.equals(this.values(), array.values());
    }

    int doHashCode() {
        return Arrays.hashCode(this.values());
    }

    String doToString() {
        return Arrays.toString(this.values());
    }
}
