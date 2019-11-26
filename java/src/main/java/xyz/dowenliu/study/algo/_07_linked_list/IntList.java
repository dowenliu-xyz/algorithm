package xyz.dowenliu.study.algo._07_linked_list;

/**
 * {@code int} 列表
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface IntList {
    /**
     * 返回列表包含的值数量
     *
     * @return 列表包含的值数量
     */
    int size();

    /**
     * 列表是否为空
     *
     * @return 列表为空，返回 {@code true} ；否则，返回 {@code false}
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * 按索引位置获取值
     *
     * @param index 索引
     * @return 索引位置上的值
     * @throws IndexOutOfBoundsException 索引访问越界
     */
    int get(int index) throws IndexOutOfBoundsException;

    /**
     * 判断列表中是否有对应值
     *
     * @param value 要判断的值
     * @return 包含，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean contains(int value);

    /**
     * 修改索引位置的值
     *
     * @param index 索引
     * @param value 要改为的值
     * @return 修改前的值
     * @throws IndexOutOfBoundsException 索引访问越界
     */
    int set(int index, int value) throws IndexOutOfBoundsException;

    /**
     * 在索引位置之前添加值
     *
     * @param index 索引
     * @param value 要添加的值
     * @throws IndexOutOfBoundsException 索引访问越界
     */
    void addBefore(int index, int value) throws IndexOutOfBoundsException;

    /**
     * 添加值到列表头部
     *
     * @param value 要添加的值
     */
    default void addHead(int value) {
        this.addBefore(0, value);
    }

    /**
     * 在索引位置之后添加值
     *
     * @param index 索引
     * @param value 要添加的值
     * @throws IndexOutOfBoundsException 索引访问越界
     */
    void addAfter(int index, int value) throws IndexOutOfBoundsException;

    /**
     * 添加值到列表末尾
     *
     * @param value 要添加的值
     */
    default void addTail(int value) {
        this.addAfter(this.size() - 1, value);
    }

    /**
     * 删除索引位置的值
     *
     * @param index 索引
     * @return 删除前索引位置的值
     * @throws IndexOutOfBoundsException 索引访问越界
     */
    int remove(int index) throws IndexOutOfBoundsException;

    /**
     * 删除第一个具有指定值的值
     *
     * @param value 要删除的值
     * @return 找到并删除了值，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean removeBy(int value);

    /**
     * 查找并返回值第一次出现的位置索引
     *
     * @param value 要查找的值
     * @return 如果列表中有要查找的值，返回第一次出现的位置索引；否则，返回 {@code -1}
     */
    int indexOf(int value);

    /**
     * 查找并返回值最后一次出现的位置索引
     *
     * @param value 要查找的值
     * @return 如果列表中有要查找的值，返回最后一次出现的位置索引；否则，返回 {@code -1}
     */
    int lastIndexOf(int value);

    /**
     * 返回值等效的数组。其中值的顺序与列表中相同。
     * @return 等效数组
     */
    int[] toArray();

    /**
     * 清空列表
     */
    void clear();

    /**
     * 反转列表
     */
    void reverse();
}
