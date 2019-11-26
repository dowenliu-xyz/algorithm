package xyz.dowenliu.study.algo._05_array;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SizeFixedArray<E> implements Array<E> {
    private ArrayOperator<E> operator;

    /**
     * 构建定长数组
     *
     * @param capacity 存储容量
     * @param generator 数组生成器
     */
    public SizeFixedArray(int capacity, IntFunction<E[]> generator) {
        this.operator = new ArrayOperator<>(generator.apply(capacity), 0);
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
    public boolean insertAt(int index, E e) throws ArrayIndexOutOfBoundsException {
        if (this.isFull()) {
            return false;
        }
        this.operator.validateInsertIndex(index);
        return this.operator.doInsertAt(index, e);
    }

    @Override
    public E removeAt(int index)
            throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        return this.operator.removeAt(index);
    }

    @Override
    public void clear() {
        this.operator.clear();
    }

    @Override
    public E get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException {
        return this.operator.get(index);
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        return this.operator.set(index, e);
    }

    @Override
    public E[] values() {
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
