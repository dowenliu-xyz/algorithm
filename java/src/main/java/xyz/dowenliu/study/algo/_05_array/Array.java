package xyz.dowenliu.study.algo._05_array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * 泛型数组
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 * @param <E> 元素类型
 */
public interface Array<E> extends Cloneable {
    /**
     * 返回数组的容量。
     *
     * @return 数组容量
     */
    int capacity();

    /**
     * 返回数组中存储的元素的个数。
     *
     * @return 数组中存储的元素的个数
     */
    int size();

    default boolean isFull() {
        return this.size() == this.capacity();
    }

    default boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 向数组中指定位置插入元素
     *
     * @param index 插入位置索引。元素将插入到索引位置之前
     * @param e 要插入的元素
     * @return 如果插入成功，返回 {@code true} ；否则，返回 {@code false}
     * @throws ArrayIndexOutOfBoundsException 数组访问越界
     */
    boolean insertAt(int index, E e)
            throws ArrayIndexOutOfBoundsException;

    /**
     * 向数组末尾添加一个元素
     *
     * @param e 要添加的元素。
     * @return 如果添加成功，返回 {@code true} ；否则，返回 {@code false}
     */
    default boolean append(E e) {
        return this.insertAt(this.size(), e);
    }

    /**
     * 删除索引位置的元素
     *
     * @param index 要删除的元素的位置索引
     * @return 删除的元素
     * @throws NoSuchElementException         要删除的索引位置大于
     *                                        {@link #size()} - 1
     * @throws ArrayIndexOutOfBoundsException 数组访问越界（index 小于0）
     */
    E removeAt(int index)
            throws NoSuchElementException, ArrayIndexOutOfBoundsException;

    /**
     * 删除数组末尾的元素
     *
     * @return 删除的元素
     * @throws NoSuchElementException 当前数组末尾没有可供删除的元素
     */
    default E removeTail() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return this.removeAt(this.size() - 1);
    }

    /**
     * 删除所有的元素
     */
    void clear();

    /**
     * 按索引查找并返回元素
     *
     * @param index 索引
     * @return 索引位置上的元素
     * @throws ArrayIndexOutOfBoundsException 数组访问越界（index 小于0）
     * @throws NoSuchElementException         要查找的索引位置大于
     *                                        {@link #size()} - 1
     */
    E get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException;

    /**
     * 修改指定索引位置的元素
     *
     * @param index 要修改的元素的位置
     * @param e 要修改为的元素
     * @return 修改之前的元素
     * @throws IndexOutOfBoundsException 修改的索引位置不在有效范围内
     */
    E set(int index, E e) throws IndexOutOfBoundsException;

    /**
     * 返回由有效元素组成的等效元素数组，其元素顺序与本{@link Array<E>}相同。
     *
     * @return 等效元素数组
     */
    E[] values();

    /**
     * 生成元素流
     *
     * @return 元素流
     */
    default Stream<E> stream() {
        return Arrays.stream(values());
    }

    /**
     * 生成并行元素流
     *
     * @return 并行元素流
     */
    default Stream<E> parallelStream() {
        return stream().parallel();
    }

    /**
     * 将有效位的元素排序
     */
    default void sort(Comparator<E> comparator) {
        E[] values = values();
        Arrays.sort(values, comparator);
        for (int i = 0; i < values.length; i++) {
            set(i, values[i]);
        }
    }

    /**
     * 将有效位的元素倒序
     */
    default void reverse() {
        for (int i = 0; i < this.size() / 2; i++) {
            E temp = this.get(i);
            this.set(i, this.get(this.size() - 1 - i));
            this.set(this.size() - 1 - i, temp);
        }
    }
}
