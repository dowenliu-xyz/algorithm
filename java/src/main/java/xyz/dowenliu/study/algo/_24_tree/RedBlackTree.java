package xyz.dowenliu.study.algo._24_tree;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Objects;
import java.util.Queue;
import java.util.function.ObjIntConsumer;

/**
 * 红黑树。此实现允许添加重复元素
 * <p>create at 2019/12/6</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class RedBlackTree<@NotNull E> implements BinaryTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class Node<@NotNull E> {
        /**
         * 节点存储的元素。
         */
        @NotNull
        E element;
        /**
         * 节点颜色
         */
        boolean color;
        /**
         * 节点的父节点
         */
        @Nullable
        Node<E> parent;
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

        Node(@NotNull E element,
             boolean color,
             @Nullable Node<E> parent,
             @Nullable Node<E> left,
             @Nullable Node<E> right) {
            this.element = element;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 根节点
     */
    @Nullable
    private Node<E> root;
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
     *
     * @param comparator 元素大小比较器。不能为 {@code null}
     * @throws NullPointerException 元素大小比较器为 {@code null}
     */
    public RedBlackTree(@NotNull Comparator<E> comparator) {
        this.comparator = Objects.requireNonNull(comparator);
        this.size = 0;
    }

    /**
     * 构造包含指定元素的树
     *
     * @param comparator 元素大小比较器。不能为 {@code null}
     * @param elements   要包含的元素。其中值为 {@code null} 的元素不会被添加到树中
     * @throws NullPointerException 元素大小比较器为 {@code null}
     */
    @SafeVarargs
    public RedBlackTree(@NotNull Comparator<E> comparator,
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
        // 找到插入位置
        Node<E> cur = this.root;
        Node<E> parent = null;
        while (cur != null) {
            parent = cur;
            cur = this.compare(element, cur) < 0 ? cur.left : cur.right;
        }
        // 插入
        if (parent == null) {
            // 插入根节点
            this.root = new Node<>(element, BLACK, null, null, null);
            this.size++;
            return true;
        }
        if (this.compare(element, parent) < 0) {
            cur = new Node<>(element, RED, parent, null, null);
            parent.left = cur;
        } else {
            cur = new Node<>(element, RED, parent, null, null);
            parent.right = cur;
        }
        this.fixAfterInsertion(cur);
        this.size++;
        return true;
    }

    private int compare(@NotNull E element, @NotNull Node<E> node) {
        return this.comparator.compare(element, node.element);
    }

    private static <E> boolean colorOf(@Nullable Node<E> node) {
        return node == null ? BLACK : node.color;
    }

    private static <E> void setColor(@Nullable Node<E> node, boolean color) {
        if (node != null) node.color = color;
    }

    @Nullable
    @Contract("null -> null; !null -> _")
    private static <E> Node<E> parentOf(@Nullable Node<E> node) {
        return node == null ? null : node.parent;
    }

    @Nullable
    @Contract("null -> null; !null -> _")
    private static <E> Node<E> leftOf(@Nullable Node<E> node) {
        return node == null ? null : node.left;
    }

    @Nullable
    @Contract("null -> null; !null -> _")
    private static <E> Node<E> rightOf(@Nullable Node<E> node) {
        return node == null ? null : node.right;
    }

    private void rotateLeft(@Nullable Node<E> node) {
        if (node == null) return;
        Node<E> r = node.right;
        if (r == null) throw new IllegalStateException("没有右子节点，无法左旋");
        node.right = r.left;
        if (r.left != null) r.left.parent = node;
        r.parent = node.parent;
        if (node.parent == null) this.root = r;
        else if (node.parent.left == node) node.parent.left = r;
        else node.parent.right = r;
        r.left = node;
        node.parent = r;
    }

    private void rotateRight(@Nullable Node<E> node) {
        if (node == null) return;
        Node<E> l = node.left;
        if (l == null) throw new IllegalStateException("没有左子节点，无法左旋");
        node.left = l.right;
        if (l.right != null) l.right.parent = node;
        l.parent = node.parent;
        if (node.parent == null) this.root = l;
        else if (node.parent.left == node) node.parent.left = l;
        else node.parent.right = l;
        l.right = node;
        node.parent = l;
    }

    private void fixAfterInsertion(@NotNull Node<E> cur) {
        while (cur != null && cur != root && colorOf(cur.parent) == RED) {
            if (parentOf(cur) == leftOf(parentOf(parentOf(cur)))) {
                Node<E> uncle = rightOf(parentOf(parentOf(cur)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(cur), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(cur)), RED);
                    cur = parentOf(parentOf(cur));
                } else {
                    if (cur == rightOf(parentOf(cur))) {
                        // <
                        cur = parentOf(cur);
                        this.rotateLeft(cur);
                    }
                    // /
                    setColor(parentOf(cur), BLACK);
                    setColor(parentOf(parentOf(cur)), RED);
                    this.rotateRight(parentOf(parentOf(cur)));
                }
            } else {
                Node<E> uncle = leftOf(parentOf(parentOf(cur)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(cur), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(cur)), RED);
                    cur = parentOf(parentOf(cur));
                } else {
                    if (cur == leftOf(parentOf(cur))) {
                        // >
                        cur = parentOf(cur);
                        rotateRight(cur);
                    }
                    // \
                    setColor(parentOf(cur), BLACK);
                    setColor(parentOf(parentOf(cur)), RED);
                    rotateLeft(parentOf(parentOf(cur)));
                }
            }
        }
        setColor(this.root, BLACK);
    }

    @Override
    public boolean contains(@Nullable E target) {
        if (target == null) return false;
        Node<E> p = this.root;
        while (p != null) {
            int compare = this.compare(target, p);
            if (compare < 0) p = p.left;
            else if (compare > 0) p = p.right;
            else return true;
        }
        return false;
    }

    /**
     * 查找并删除第一个找到的与目标元素相同的元素
     *
     * @param target 要查找并删除的目标元素
     * @return 树发生了改变，返回 {@code true} ；否则，返回 {@code false}
     */
    @Override
    public boolean remove(@Nullable E target) {
        if (target == null) return false;
        Node<E> node = this.root;
        while (node != null) {
            int compare = this.compare(target, node);
            if (compare < 0) node = node.left;
            else if (compare > 0) node = node.right;
            else break;
        }
        if (node == null) return false;
        this.removeNode(node);
        return true;
    }

    private void removeNode(@NotNull Node<E> node) {
        if (node.left != null && node.right != null) {
            Node<E> successor = getSuccessor(node);
            assert successor != null : "有右子节点，一定有后继节点";
            node.element = successor.element;
            node = successor;
        }

        Node<E> replacement = node.left == null ? node.right : node.left;

        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                this.root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }

            node.left = node.right = node.parent = null;

            if (node.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (node.parent == null) {
            this.root = null;
        } else {
            if (node.color == BLACK)
                fixAfterDeletion(node);

            if (node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }

        this.size--;
    }

    @Nullable
    private Node<E> getSuccessor(@NotNull Node<E> node) {
        Node<E> successor = node.right;
        while (successor != null && successor.left != null) {
            successor = successor.left;
        }
        return successor;
    }

    private void fixAfterDeletion(@NotNull Node<E> x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) { // 当前节点是其父节点的左子节点
                Node<E> sib = rightOf(parentOf(x)); // 兄弟节点

                if (colorOf(sib) == RED) { // 兄弟节点是红色
                    // 兄弟节点的父节点、两个子节点必定都是黑色
                    //     P:B
                    //    /   \
                    // X:B     S:R
                    //        /    \
                    //      SL:B  SR:B
                    setColor(sib, BLACK); // 兄弟节点变黑
                    setColor(parentOf(x), RED); // 父节点变红
                    //     P:B
                    //    /   \
                    // X:B     S:B
                    //        /    \
                    //      SL:R  SR:R
                    rotateLeft(parentOf(x)); // 围绕父节点左旋
                    //       S:B
                    //      /   \
                    //    P:R    SR:B
                    //   /   \
                    // X:B   SL:B
                    sib = rightOf(parentOf(x));
                } // 兄弟节点变成了黑色的了

                if (colorOf(leftOf(sib)) == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    // 兄弟节点的两个子节点都是黑色
                    setColor(sib, RED); // 兄弟变成红色
                    x = parentOf(x); // 向上调整
                } else {
                    // 兄弟节点至少有一个子节点不是黑色
                    if (colorOf(rightOf(sib)) == BLACK) {
                        // 兄弟节点右子节点是黑色的，那么兄弟节点左子节点必定是红色的
                        //     P:?
                        //    /   \
                        // X:B     S:B
                        //        /    \
                        //      SL:R  SR:B
                        setColor(leftOf(sib), BLACK); // 兄弟节点左子树涂黑
                        setColor(sib, RED); // 兄弟节点涂红
                        //     P:?
                        //    /   \
                        // X:B     S:R
                        //        /    \
                        //      SL:B  SR:B
                        rotateRight(sib); // 围绕兄弟节点右旋
                        //     P:?
                        //    /   \
                        // X:B     SL:B
                        //          \
                        //          S:R
                        //            \
                        //            SR:B
                        sib = rightOf(parentOf(x));
                    }
                    // 兄弟节点右子节点是红色的，兄弟节点左子节点颜色不定
                    //     P:?
                    //    /   \
                    // X:B     S:B
                    //        /    \
                    //      SL:?  SR:R
                    setColor(sib, colorOf(parentOf(x))); // 兄弟节点变为父节点的颜色
                    setColor(parentOf(x), BLACK); // 父节点涂黑
                    setColor(rightOf(sib), BLACK); // 兄弟节点的右子节点涂黑
                    //     P:B
                    //    /   \
                    // X:B     S:?
                    //        /    \
                    //      SL:?  SR:B
                    rotateLeft(parentOf(x)); // 围绕父节点左旋
                    //       S:?
                    //      /   \
                    //    P:B    SR:B
                    //   /   \
                    // X:B   SL:?
                    x = root; // 结束调整
                }
            } else { // 当前节点是其父节点的右子节点
                Node<E> sib = leftOf(parentOf(x)); // 兄弟节点

                if (colorOf(sib) == RED) { // 兄弟节点是红色的
                    // 兄弟节点的父节点、两个子节点必定都是黑色
                    //        P:B
                    //       /   \
                    //    S:R     X:B
                    //   /   \
                    // SL:B  SR:B
                    setColor(sib, BLACK); // 兄弟节点涂黑
                    setColor(parentOf(x), RED); // 父节点涂红
                    //        P:R
                    //       /   \
                    //    S:B     X:B
                    //   /   \
                    // SL:B  SR:B
                    rotateRight(parentOf(x)); // 围绕父节点右旋
                    //     S:B
                    //    /   \
                    // SL:B   P:R
                    //       /    \
                    //      SR:B  X:B
                    sib = leftOf(parentOf(x));
                } // 兄弟节点变成黑色的了

                if (colorOf(leftOf(sib)) == BLACK &&
                        colorOf(rightOf(sib)) == BLACK) {
                    // 兄弟节点的两个子节点都是黑色
                    setColor(sib, RED); // 兄弟节点变成红色
                    x = parentOf(x); // 向上调整
                } else {
                    // 兄弟节点至少有一个子节点不是黑色
                    if (colorOf(leftOf(sib)) == BLACK) {
                        // 兄弟节点左子节点是黑色的，那么兄弟节点右子节点必定是红色的
                        //       P:?
                        //      /   \
                        //    S:B    X:B
                        //   /   \
                        // SL:B  SR:R
                        setColor(rightOf(sib), BLACK); // 兄弟节点的右子节点涂黑
                        setColor(sib, RED); // 兄弟节点涂红
                        //       P:?
                        //      /   \
                        //    S:R    X:B
                        //   /   \
                        // SL:B  SR:B
                        rotateLeft(sib); // 围绕兄弟节点左旋
                        //         P:?
                        //        /   \
                        //      SR:B  X:B
                        //      /
                        //    S:R
                        //   /
                        // SL:B
                        sib = leftOf(parentOf(x));
                    }
                    // 兄弟节点左子节点是红色的，兄弟节点右子节点颜色不定
                    //        P:?
                    //       /   \
                    //    S:B     X:B
                    //   /   \
                    // SL:R  SR:?
                    setColor(sib, colorOf(parentOf(x))); // 兄弟节点变为父节点的颜色
                    setColor(parentOf(x), BLACK); // 父节点涂黑
                    setColor(leftOf(sib), BLACK); // 兄弟节点的左子节点涂黑
                    //        P:B
                    //       /   \
                    //    S:?     X:B
                    //   /   \
                    // SL:B  SR:?
                    rotateRight(parentOf(x)); // 围绕父节点右旋
                    //     S:?
                    //    /   \
                    // SL:B    P:B
                    //        /    \
                    //      SR:?   X:B
                    x = root; // 结束调整
                }
            }
        }

        setColor(x, BLACK);
    }

    @Nullable
    public E getMin() {
        Node<E> node = this.root;
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node.element;
    }

    @Nullable
    public E getMax() {
        Node<E> node = this.root;
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void walkLevelOrder(@NotNull ObjIntConsumer<E> consumer) {
        if (this.root == null) return;
        int queueSize = (int) (Math.log(this.size) / Math.log(2)) + 1;
        Queue<Node<@NotNull E>> nodeQueue = new ArrayDeque<>(queueSize);
        nodeQueue.add(this.root);
        int index = 0;
        while (!nodeQueue.isEmpty()) {
            Node<@NotNull E> node = nodeQueue.remove();
            if (node.left != null) nodeQueue.add(node.left);
            if (node.right != null) nodeQueue.add(node.right);
            consumer.accept(node.element, index++);
        }
    }

    @SuppressWarnings("DuplicatedCode")
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
