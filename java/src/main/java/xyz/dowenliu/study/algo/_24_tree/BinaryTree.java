package xyz.dowenliu.study.algo._24_tree;

import org.jetbrains.annotations.NotNull;

import java.util.function.ObjIntConsumer;

/**
 * 二叉树
 * <p>create at 2019/12/5</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface BinaryTree<@NotNull E> extends Tree<E> {
    /**
     * 中序遍历节点
     *
     * @param consumer 对节点元素和遍历索引（自 {@code int} 计）的操作
     * @throws NullPointerException 操作为空
     */
    void walkInOrder(@NotNull ObjIntConsumer<E> consumer);
}
