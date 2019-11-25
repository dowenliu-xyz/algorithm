package xyz.dowenliu.study.algo._05_array;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * Int数组
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface IntArray extends Cloneable {
    /**
     * 返回数组的容量。
     *
     * @return 数组容量
     */
    int capacity();

    /**
     * 返回数组中存储的 {@code int}值的个数。
     *
     * @return 数组中存储的 {@code int}值的个数
     */
    int size();

    default boolean isFull() {
        return this.size() == this.capacity();
    }

    default boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 向数组中指定位置插入值
     *
     * @param index 插入位置索引。元素将插入到索引位置之前
     * @param value 要插入的值
     * @return 如果插入成功，返回 {@code true}；否则，返回 {@code false}
     * @throws ArrayIndexOutOfBoundsException 数组访问越界
     */
    boolean insertAt(int index, int value)
            throws ArrayIndexOutOfBoundsException;

    /**
     * 向数组末尾添加一个 {@code int}值
     *
     * @param value 要添加的值。
     * @return 如果添加成功，返回 {@code true}；否则，返回 {@code false}
     */
    default boolean append(int value) {
        return this.insertAt(this.size(), value);
    }

    /**
     * 删除索引位置的值
     *
     * @param index 要删除的值的位置索引
     * @return 删除的值
     * @throws NoSuchElementException         要删除的索引位置大于
     *                                        {@link #size()} - 1
     * @throws ArrayIndexOutOfBoundsException 数组访问越界（index 小于0）
     */
    int removeAt(int index)
            throws NoSuchElementException, ArrayIndexOutOfBoundsException;

    /**
     * 删除数组末尾的值
     *
     * @return 删除的值
     * @throws NoSuchElementException 当前数组末尾没有可供删除的值
     */
    default int removeTail() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return this.removeAt(this.size() - 1);
    }

    /**
     * 删除所有的值
     */
    void clear();

    /**
     * 按索引查找并返回值
     *
     * @param index 值索引
     * @return 索引位置上的值
     * @throws ArrayIndexOutOfBoundsException 数组访问越界（index 小于0）
     * @throws NoSuchElementException         要查找的索引位置大于
     *                                        {@link #size()} - 1
     */
    int get(int index)
            throws ArrayIndexOutOfBoundsException, NoSuchElementException;

    /**
     * 修改指定索引位置的值
     *
     * @param index 要修改的值的位置
     * @param value 要修改为的值
     * @return 修改之前的值
     * @throws IndexOutOfBoundsException 修改的索引位置不在有效范围内
     */
    int set(int index, int value) throws IndexOutOfBoundsException;

    /**
     * 返回由有效值组成的等效值数组，其值顺序与本{@link IntArray}相同。
     *
     * @return 等效值数组
     */
    int[] values();

    /**
     * 生成 {@code int}流
     *
     * @return {@code int}流
     */
    default IntStream stream() {
        return Arrays.stream(values());
    }

    /**
     * 生成并行 {@code int}流
     *
     * @return 并行 {@code int}流
     */
    default IntStream parallelStream() {
        return stream().parallel();
    }

    /**
     * 将有效位的值排序
     */
    default void sort() {
        int[] values = values();
        Arrays.sort(values);
        for (int i = 0; i < values.length; i++) {
            set(i, values[i]);
        }
    }

    /**
     * 将有效位的值倒序
     */
    default void reverse() {
        for (int i = 0; i < this.size() / 2; i++) {
            int temp = this.get(i);
            this.set(i, this.get(this.size() - 1 - i));
            this.set(this.size() - 1 - i, temp);
        }
    }

//    Array<Integer> boxed(); // TODO boxed
}
