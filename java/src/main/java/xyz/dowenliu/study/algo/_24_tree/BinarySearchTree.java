package xyz.dowenliu.study.algo._24_tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Objects;
import java.util.Queue;
import java.util.function.ObjIntConsumer;

/**
 * 二叉查找树。此实现允许添加比较相等的元素
 * <p>create at 2019/12/5</p>
 *
 * @param <E> 节点存储元素类型
 * @author liufl
 * @since version 1.0
 */
public class BinarySearchTree<@NotNull E> implements BinaryTree<E> {
    /**
     * 树节点
     *
     * @param <E> 节点存储元素类型
     */
    private static class Node<@NotNull E> {
        /**
         * 节点存储的元素。不应该为 {@code null}
         */
        @NotNull
        final E element;
        /**
         * 节点的左子节点
         */
        @Nullable
        Node<E> left;
        /**
         * 节点的右子节点
         */
        @Nullable
        Node<E> right;

        /**
         * 构造节点。其左右子节点均为 {@code null}
         * @param element 节点要存储的元素
         */
        public Node(@NotNull E element) {
            this.element = Objects.requireNonNull(element);
        }
    }

    /**
     * 树的根节点
     */
    @Nullable
    private Node<@NotNull E> root;
    /**
     * 存储元素数量的变量
     */
    private int size;
    /**
     * 元素大小比较器
     */
    @NotNull
    private final Comparator<E> comparator;

    /**
     * 构造空树
     * @param comparator 元素大小比较器。不能为 {@code null}
     * @throws NullPointerException 元素大小比较器为 {@code null}
     */
    public BinarySearchTree(@NotNull Comparator<E> comparator) {
        this.comparator = Objects.requireNonNull(comparator);
        this.size = 0;
    }

    /**
     * 构造包含指定元素的树
     * @param comparator 元素大小比较器。不能为 {@code null}
     * @param elements 要包含的元素。其中值为 {@code null} 的元素不会被添加到树中
     * @throws NullPointerException 元素大小比较器为 {@code null}
     */
    @SafeVarargs
    public BinarySearchTree(@NotNull Comparator<E> comparator,
                            @Nullable E @Nullable ... elements) {
        this(comparator);
        if (elements == null) return;
        for (E element : elements) {
            this.add(element);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(@Nullable E element) {
        if (element == null) return false;
        if (root == null) {
            root = new Node<>(element);
            this.size++;
            return true;
        }

        Node<@NotNull E> p = this.root;
        while (true) {
            if (this.comparator.compare(element, p.element) < 0) {
                if (p.left == null) {
                    p.left = new Node<>(element);
                    this.size++;
                    return true;
                }
                p = p.left;
            } else {
                // element >= p.element
                if (p.right == null) {
                    p.right = new Node<>(element);
                    this.size++;
                    return true;
                }
                p = p.right;
            }
        }
    }

    @Override
    public boolean contains(@Nullable E target) {
        if (target == null) return false;
        Node<@NotNull E> p = this.root;
        while (p != null) {
            int compare = this.comparator.compare(target, p.element);
            if (compare < 0) p = p.left;
            else if (compare > 0) p = p.right;
            else {
                if (Objects.equals(target, p.element)) return true;
                p = p.right;
            }
        }
        return false;
    }

    /**
     * 查找并删除第一个找到的与目标元素相同的元素
     * @param target 要查找并删除的目标元素
     * @return 树发生了改变，返回 {@code true} ；否则，返回 {@code false}
     */
    @Override
    public boolean remove(@Nullable E target) {
        if (target == null) return false;
        Node<@NotNull E> parent = null; // 要删除子节点的父节点
        Node<@NotNull E> node = this.root; // 要删除的子节点
        while (node != null && !Objects.equals(target, node.element)) {
            parent = node;
            int compare = this.comparator.compare(target, node.element);
            if (compare < 0) node = node.left;
            else node = node.right;
        }
        if (node == null) return false; // 没有找到要删除的节点
        // 要删除的节点已找到
        if (node.left != null && node.right != null) {
            // 左右子节点具在，找右子节点的最小左子节点
            Node<@NotNull E> minNode = node.right; // 右子节点的最小左子节点
            Node<@NotNull E> minParent = node; // 右子节点的最小左子节点的父级节点
            while (minNode.left != null) {
                minParent = minNode;
                minNode = minNode.left;
            }
            // 将 node 和 minNode 交换，删除 node 的问题就转换为删除 minNode 问题了。
            Node<@NotNull E> minNodeRight = minNode.right; // 记录 minNode 的右子节点
            minParent.left = null; // 断开 minNode
            // 用自由的 minNode 换下 node
            minNode.left = node.left;
            minNode.right = node.right;
            if (parent == null) {
                this.root = minNode;
            } else {
                if (parent.left == node) {
                    parent.left = minNode;
                } else {
                    parent.right = minNode;
                }
            }
            // 用自由的 node 补上 minNode 的位置
            node.left = null;
            node.right = minNodeRight;
            minParent.left = node; // node 的父节点已经是 minParent 了
            parent = minParent; // 还是使用 parent 变量来记录 node 的父节点
        }
        assert node.left == null || node.right == null;
        Node<@NotNull E> parentNewChild =
                node.left == null ? node.right : node.left;
        if (parent == null) {
            this.root = parentNewChild;
        } else {
            if (parent.left == node) {
                parent.left = parentNewChild;
            } else {
                parent.right = parentNewChild;
            }
        }
        this.size--;
        return true;
    }

    /**
     * 查找并返回树中存储的最小元素
     * @return 树中存储的最小元素
     */
    @Nullable
    public E getMin() {
        Node<@NotNull E> node = this.root;
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node.element;
    }

    /**
     * 查找并返回树中存储的最大元素
     * @return 树中存储的最大元素
     */
    @Nullable
    public E getMax() {
        Node<@NotNull E> node = this.root;
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node.element;
    }

    @Override
    public void walkPreOrder(@NotNull ObjIntConsumer<E> consumer) {
        this.walkPreOrder(0, this.root, Objects.requireNonNull(consumer));
    }

    private int walkPreOrder(int index,
                              @Nullable Node<@NotNull E> node,
                              @NotNull ObjIntConsumer<E> consumer) {
        if (node == null) return index;
        consumer.accept(node.element, index++);
        index = walkPreOrder(index, node.left, consumer);
        return walkPreOrder(index, node.right, consumer);
    }

    @Override
    public void walkInOrder(@NotNull ObjIntConsumer<E> consumer) {
        this.walkInOrder(0, this.root, Objects.requireNonNull(consumer));
    }

    private int walkInOrder(int index,
                            @Nullable Node<@NotNull E> node,
                            @NotNull ObjIntConsumer<E> consumer) {
        if (node == null) return index;
        index = walkInOrder(index, node.left, consumer);
        consumer.accept(node.element, index++);
        return walkInOrder(index, node.right, consumer);
    }

    @Override
    public void walkPostOrder(@NotNull ObjIntConsumer<E> consumer) {
        this.walkPostOrder(0, this.root, Objects.requireNonNull(consumer));
    }

    private int walkPostOrder(int index,
                              @Nullable Node<@NotNull E> node,
                              @NotNull ObjIntConsumer<E> consumer) {
        if (node == null) return index;
        index = walkPostOrder(index, node.left, consumer);
        index = walkPostOrder(index, node.right, consumer);
        consumer.accept(node.element, index++);
        return index;
    }

    @Override
    public void walkLevelOrder(@NotNull ObjIntConsumer<E> consumer) {
        if (this.root == null) return;
        Queue<Node<@NotNull E>> nodeQueue = new ArrayDeque<>((int) (Math.log(this.size) / Math.log(2)) + 1);
        nodeQueue.add(this.root);
        int index = 0;
        while (!nodeQueue.isEmpty()) {
            Node<@NotNull E> node = nodeQueue.remove();
            if (node.left != null) nodeQueue.add(node.left);
            if (node.right != null) nodeQueue.add(node.right);
            consumer.accept(node.element, index++);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        this.walkInOrder((e, index) -> {
            if (index > 0) {
                sb.append(", ");
            }
            sb.append(e.toString());
        });
        sb.append(']');
        return sb.toString();
    }
}
