package xyz.dowenliu.study.algo._17_skiplist;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 泛型跳表
 * <p>create at 2019/12/3</p>
 *
 * @param <E> 元素类型。不允许为 {@code null}
 * @author liufl
 * @since version 1.0
 */
public class SkipList<@NotNull E> {
    /**
     * 跳表节点
     *
     * @param <E> 节点存储元素类型。不允许为 {@code null}
     */
    private static class Node<@NotNull E> {
        /**
         * 节点存储的元素，不能为 {@code null}
         */
        @NotNull
        protected final E element;
        /**
         * 记录各层指向的下一节点地址的数组。数组不会为 {@code null} ，
         * 数组长度为可指向层数，至少为 {@code 1} ，数组的元素可以为 {@code null} 。
         * 第 {@code n} 层的下一节点引用存储在索引 {@code n - 1} 的元素上。
         */
        @Nullable
        protected final Node<@NotNull E> @NotNull [] forwards;

        /**
         * 构造节点
         *
         * @param element 节点存储的元素，不能为 {@code null}
         * @param levels  节点可指向的层数。至少为 {@code 1}
         * @throws NullPointerException     要存储的元素为 {@code null}
         * @throws IllegalArgumentException 层数小于 {@code 1}
         */
        @SuppressWarnings("unchecked")
        public Node(@NotNull E element, int levels) {
            this.element = Objects.requireNonNull(element);
            if (levels < 1) {
                throw new IllegalArgumentException(
                        "节点链接层数至少为1，不能是" + levels);
            }
            this.forwards = new Node[levels];
        }

        /**
         * 构造头节点，正常节点构造需要使用 {@link #Node(Object, int)} 构造器。
         *
         * @param levels 节点可指向的层数。至少为 {@code 1}
         * @throws IllegalArgumentException 层数小于 {@code 1}
         */
        @SuppressWarnings({"unchecked", "ConstantConditions"})
        protected Node(int levels) {
            if (levels < 1) {
                throw new IllegalArgumentException(
                        "节点链接层数至少为1，不能是" + levels);
            }
            this.forwards = new Node[levels];
            this.element = null;
        }

        /**
         * 比较节点存储的元素与一个外部元素
         *
         * @param target     外部元素。不能为 {@code null}
         * @param comparator 元素类型比较器。不能为 {@code null}
         * @return 如果存储的元素小于外部元素，返回 {@code -1} ；
         * 如果存储的元素等于外部元素，返回 {@code 0} ；
         * 如果存储的元素大于外部元素，返回 {@code 1}
         */
        public int compareTo(@NotNull E target,
                             @NotNull Comparator<E> comparator) {
            return comparator.compare(this.element, target);
        }

        /**
         * 查找并返回当前节点在指定层链接中指向的下一节点
         *
         * @param level 链接层级数
         * @return 如果链接层级数小于 {@code 1} 或大于当前节点可指向的最大链接层级数，
         * 返回 {@code null} ；否则，返回当前节点在对应链接层级上指向的下一节点
         */
        @Nullable
        public Node<@NotNull E> nextAtLevel(int level) {
            if (level <= 0 || level > this.forwards.length) return null;
            return this.forwards[level - 1];
        }

        /**
         * 更新当前节点在指定层链接中指向的下一节点
         *
         * @param level 链接层级数。如果链接层级数小于 {@code 1}
         *              或大于当前节点可指向的最大链接层级数，
         *              当前方法直接返回，不作任何处理。
         * @param next  要更新为的下一节点引用。可以是 {@code null}
         */
        public void setNextAtLevel(int level, @Nullable Node<@NotNull E> next) {
            if (level <= 0 || level > this.forwards.length) return;
            this.forwards[level - 1] = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.element);
            Node<@NotNull E> next = this.nextAtLevel(1);
            if (next != null) {
                sb.append(", ").append(next.toString());
            }
            return sb.toString();
        }
    }

    /**
     * 跳表头节点
     *
     * @param <E> 节点存储元素类型。实际存储为 {@code null}
     */
    private static class HeadNode<E> extends Node<E> {
        /**
         * 构造头节点
         *
         * @param levels 当前跳表链接最大层级。至少为 {@code 1}
         * @throws IllegalArgumentException 层数小于 {@code 1}
         */
        public HeadNode(int levels) {
            super(levels);
        }

        @Override
        public int compareTo(@NotNull E target,
                             @NotNull Comparator<E> comparator) {
            return -1;
        }
    }

    private static final int DEFAULT_MAX_LEVEL = 62;

    @NotNull
    private final HeadNode<E> listHead;
    private int size;
    private final int maxLevel;
    private int levels = 1;
    @NotNull
    private final Comparator<E> elementComparator;

    /**
     * 构造跳表
     *
     * @param maxLevel          跳表链接最大层数。不能小于 {@code 1}
     * @param elementComparator 元素比较器。不能为 {@code null}
     * @throws IllegalArgumentException 跳表链接最大层数小于 {@code 1}
     * @throws NullPointerException     元素比较器为 {@code null}
     */
    public SkipList(int maxLevel, @NotNull Comparator<E> elementComparator) {
        if (maxLevel < 1) {
            throw new IllegalArgumentException("跳表至少要有一层链");
        }
        this.maxLevel = maxLevel;
        this.listHead = new HeadNode<>(this.maxLevel);
        this.elementComparator = Objects.requireNonNull(elementComparator);
    }

    /**
     * 构造跳表。跳表链接最大层数为 {@value #DEFAULT_MAX_LEVEL}
     *
     * @param elementComparator 元素比较器。不能为 {@code null}
     * @throws NullPointerException 元素比较器为 {@code null}
     */
    public SkipList(@NotNull Comparator<E> elementComparator) {
        this(DEFAULT_MAX_LEVEL, elementComparator);
    }

    public int size() {
        return this.size;
    }

    @NotNull
    private Node<E> findLastLessThan(@NotNull E target) {
        int level = this.levels;
        Node<E> llt = this.listHead;
        while (level > 0 && llt.compareTo(target, this.elementComparator) < 0) { // 可以向右找
            Node<@NotNull E> next = llt.nextAtLevel(level);
            if (next != null &&
                    next.compareTo(target, this.elementComparator) < 0) {
                // 右侧仍有小于 target 的元素
                llt = next;
            } else {
                // 右侧的值都大于等于 target
                // 下降到下一层
                level--;
            }
        }
        return llt;
    }

    /**
     * 查找并返回目标元素是否在跳表中
     *
     * @param target 要查找的元素
     * @return 如果跳表中包含目标元素，返回 {@code true} ；否则，返回 {@code false}。
     * 如果目标为 {@code null} ，返回 {@code false}。
     */
    public boolean contains(E target) {
        if (target == null) {
            return false;
        }
        Node<E> previous = this.findLastLessThan(target);
        Node<@NotNull E> node = previous.nextAtLevel(1);
        return node != null && Objects.equals(node.element, target);
    }

    private int randomLevel() {
        Random random = ThreadLocalRandom.current();
        int level = 1;
        while (random.nextInt() % 2 == 1 && level < this.maxLevel) {
            level++;
        }
        return level;
    }

    /**
     * 插入元素
     *
     * @param element 要插入的元素。如果元素为 {@code null} ，跳表不会插入任何元素。
     */
    public void add(E element) {
        if (element == null) {
            return;
        }
        int levels = this.randomLevel();
        Node<@NotNull E> newNode = new Node<>(element, levels);
        Node<E> previous = this.listHead;
        int level = levels;
        while (level > 0 &&
                previous.compareTo(element, this.elementComparator) <= 0) {
            Node<@NotNull E> next = previous.nextAtLevel(level);
            if (next != null &&
                    next.compareTo(element, this.elementComparator) <= 0) {
                previous = next;
            } else {
                newNode.setNextAtLevel(level, next);
                previous.setNextAtLevel(level, newNode);
                level--;
            }
        }
        if (levels > this.levels) {
            this.levels = levels;
        }
        this.size++;
    }

    /**
     * 从跳表中删除元素。
     *
     * @param element 要删除的元素。如果为 {@code null} ，跳表不会删除任何元素。
     * @return 如果找到并删除了元素，返回 {@code true} ；否则，返回 {@code false}。
     */
    public boolean erase(E element) {
        if (element == null) {
            return false;
        }
        Node<E> lastLessThan = this.findLastLessThan(element);
        Node<@NotNull E> node = lastLessThan.nextAtLevel(1);
        if (node == null || !Objects.equals(node.element, element)) {
            return false;
        }
        int level = node.forwards.length;
        Node<E> previous = this.listHead;
        while (level > 0) {
            Node<@NotNull E> next = previous.nextAtLevel(level);
            if (next == null) {
                level--;
            } else if (next != node) {
                previous = next;
            } else {
                previous.setNextAtLevel(level, next.nextAtLevel(level));
                next.setNextAtLevel(level, null); // help GC
                level--;
            }
        }
        this.size--;
        while (this.levels > 1 &&
                this.listHead.nextAtLevel(this.levels) == null) {
            this.levels--;
        }
        return true;
    }

    @Override
    public String toString() {
        Node<@NotNull E> head = this.listHead.nextAtLevel(1);
        return head == null ? "[]" : "[" + head.toString() + "]";
    }

    public String toGraphString(int nodeChars) {
        nodeChars = Integer.min(3, nodeChars);
        StringBuilder sb = new StringBuilder();
        for (int level = this.levels; level > 0; level--) {
            sb.append(String.format("%2d", level)).append(": *");
            Node<@NotNull E> node = this.listHead.nextAtLevel(1);
            Node<@NotNull E> atLevel = this.listHead.nextAtLevel(level);
            while (node != null) {
                if (atLevel == null || atLevel != node) {
                    sb.append("---").append("-".repeat(nodeChars + 2));
                } else {
                    String format = " %" + nodeChars + "s ";
                    sb.append("-->")
                            .append(String.format(format, atLevel.element));
                    atLevel = atLevel.nextAtLevel(level);
                }
                node = node.nextAtLevel(1);
            }
            sb.append("--> NIL");
            if (level > 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * 按Key比较的元素
     * @param <K> 可比较的Key类型
     * @param <E> 包装的元素类型
     */
    public static class Keyed<K extends Comparable<K>, E> {
        @NotNull
        private final K key;
        @NotNull
        private final E element;

        /**
         * 构造器
         * @param key key
         * @param element 元素
         */
        public Keyed(@NotNull K key, @NotNull E element) {
            this.key = key;
            this.element = element;
        }

        @NotNull
        public K getKey() {
            return key;
        }

        @NotNull
        public E getElement() {
            return element;
        }

        /**
         * 生成比较器
         * @param <K> 用于比较的key类型
         * @param <E> 元素类型
         * @return 用于比较 {@link Keyed} 的比较器
         */
        @NotNull
        public static <K extends Comparable<K>, E>  Comparator<Keyed<K, E>> getComparator() {
            return Comparator.comparing(Keyed::getKey);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Keyed<?, ?> keyed = (Keyed<?, ?>) o;
            return key.equals(keyed.key) &&
                    element.equals(keyed.element);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, element);
        }
    }
}
