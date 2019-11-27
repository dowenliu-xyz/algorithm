package xyz.dowenliu.study.algo._07_linked_list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * {@code int} 链表
 * <p>create at 2019/11/27</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface IntLinkedList extends IntList {
    /**
     * 链表节点
     */
    interface Node {
        /**
         * 返回节点存储的值
         * @return 节点存储的值
         */
        int getValue();

        /**
         * 返回节点的后续节点
         * @return 节点的后续节点。如果不存在，返回 {@code null}
         */
        @Nullable
        Node getNext();
    }


    /**
     * 获取索引位置的节点
     *
     * @param index 索引
     * @return 索引位置的节点
     * @throws IndexOutOfBoundsException 索引访问越界
     */
    @NotNull
    Node getNode(int index) throws IndexOutOfBoundsException;

    /**
     * 查找并返回值第一次出现位置的节点
     * @param value 要查找的值
     * @return 如果找到，返回值第一次出现位置的节点；否则，返回 {@code null}
     */
    @Nullable
    Node nodeOfValue(int value);

    @Override
    default boolean contains(int value) {
        return this.nodeOfValue(value) != null;
    }
}
