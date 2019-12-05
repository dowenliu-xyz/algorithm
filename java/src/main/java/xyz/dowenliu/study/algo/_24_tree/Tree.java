package xyz.dowenliu.study.algo._24_tree;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.ObjIntConsumer;

/**
 * 树。
 * <p>
 * 各节点不能存储 {@code null}
 * <p>create at 2019/12/5</p>
 *
 * @param <E> 树节点存储的元素类型
 * @author liufl
 * @since version 1.0
 */
public interface Tree<@NotNull E> {
    /**
     * 返回树当前存储的元素数量
     *
     * @return 树当前存储的元素数量
     */
    int size();

    /**
     * 插入子节点
     *
     * @param element 要插入的元素。如果要插入的元素为 {@code null} ，树不会插入任何节点
     * @return 树发生了改变，返回 {@code true} ；否则，返回 {@code false}
     */
    @Contract("null -> false; !null -> _")
    boolean add(@Nullable E element);

    /**
     * 查找并返回当前树是否包含目标元素
     *
     * @param target 要查找的目标元素
     * @return 如果找到目标元素，返回 {@code true} ；否则，返回 {@code false}
     */
    @Contract("null -> false; !null -> _")
    boolean contains(@Nullable E target);

    /**
     * 查找并删除目标元素。如果有多个相同目标元素，删除其中任意一个。
     *
     * @param target 要查找并删除的目标元素
     * @return 树发生了改变，返回 {@code true} ；否则，返回 {@code false}
     */
    @Contract("null -> false; !null -> _")
    boolean remove(@Nullable E target);

    /**
     * 前序遍历节点
     *
     * @param consumer 对节点元素和遍历索引（自 {@code int} 计）的操作
     * @throws NullPointerException 操作为空
     */
    void walkPreOrder(@NotNull ObjIntConsumer<E> consumer);

    /**
     * 后序遍历节点
     *
     * @param consumer 对节点元素和遍历索引（自 {@code int} 计）的操作
     * @throws NullPointerException 操作为空
     */
    void walkPostOrder(@NotNull ObjIntConsumer<E> consumer);

    /**
     * 按层（广度优先）遍历节点
     *
     * @param consumer 对节点元素和遍历索引（自 {@code int} 计）的操作
     * @throws NullPointerException 操作为空
     */
    void walkLevelOrder(@NotNull ObjIntConsumer<E> consumer);
}
