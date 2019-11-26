package xyz.dowenliu.study.algo._05_array;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 * 自动扩容数组
 * <p>
 * 当插入值时，发现容量已满则自动扩容为原容量的两倍。
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class DynamicExpandArray<E> implements Array<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 8;

    private ArrayOperator<E> operator;
    private IntFunction<E[]> generator;

    public DynamicExpandArray(IntFunction<E[]> generator) {
        this(DEFAULT_INITIAL_CAPACITY, generator);
    }

    public DynamicExpandArray(int initialCapacity, IntFunction<E[]> generator) {
        this.generator = generator;
        this.operator = new ArrayOperator<>(
                this.generator.apply(initialCapacity), 0);
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
    public boolean insertAt(int index, E e)
            throws ArrayIndexOutOfBoundsException {
        this.operator.validateInsertIndex(index);
        if (this.isFull()) {
            expand();
        }
        return this.operator.doInsertAt(index, e);
    }

    private void expand() {
        final double expandRatio = 2.0;
        E[] full = this.operator.getData();
        E[] expanded = this.generator.apply((int) (full.length * expandRatio));
        System.arraycopy(full, 0, expanded, 0, this.size());
        this.operator.setData(expanded);
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
