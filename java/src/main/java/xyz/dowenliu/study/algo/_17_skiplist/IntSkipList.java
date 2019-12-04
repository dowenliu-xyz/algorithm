package xyz.dowenliu.study.algo._17_skiplist;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code int} 元素的跳表
 * <p>create at 2019/12/3</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntSkipList {
    /**
     * 跳表节点
     */
    public static class Node {
        /**
         * 节点存储的值
         */
        protected final int value;
        /**
         * 记录各层指向的下一节点地址的数组。数组不会为 {@code null} ，
         * 数组长度为可指向层数，至少为 {@code 1} ，数组的元素可以为 {@code null} 。
         * 第 {@code n} 层的下一节点引用存储在索引 {@code n - 1} 的元素上。
         */
        @Nullable
        protected final Node @NotNull [] forwards;

        /**
         * 构造节点
         *
         * @param value  节点存储的值
         * @param levels 节点可指向的层数。至少为 {@code 1}
         * @throws IllegalArgumentException 层数小于 {@code 1}
         */
        public Node(int value, int levels) {
            this.value = value;
            if (levels < 1) {
                throw new IllegalArgumentException(
                        "节点链接层数至少为1，不能是" + levels);
            }
            this.forwards = new Node[levels];
        }

        /**
         * 比较节点存储的值与一个外部值
         *
         * @param target 外部值
         * @return 如果存储的值小于外部值，返回 {@code -1} ；
         * 如果存储的值等于外部值，返回 {@code 0} ；
         * 如果存储的值大于外部值，返回 {@code 1}
         */
        public int compareTo(int target) {
            return Integer.compare(this.value, target);
        }

        /**
         * 查找并返回当前节点在指定层链接中指向的下一节点
         *
         * @param level 链接层级数
         * @return 如果链接层级数小于 {@code 1} 或大于当前节点可指向的最大链接层级数，
         * 返回 {@code null} ；否则，返回当前节点在对应链接层级上指向的下一节点
         */
        @Nullable
        public Node nextAtLevel(int level) {
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
        public void setNextAtLevel(int level, @Nullable Node next) {
            if (level <= 0 || level > this.forwards.length) return;
            this.forwards[level - 1] = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.value);
            Node next = this.nextAtLevel(1);
            if (next != null) {
                sb.append(", ").append(next.toString());
            }
            return sb.toString();
        }
    }

    /**
     * 跳表头节点
     */
    private static class HeadNode extends Node {
        /**
         * 构造头节点
         *
         * @param levels 当前跳表链接最大层级。至少为 {@code 1}
         * @throws IllegalArgumentException 层数小于 {@code 1}
         */
        public HeadNode(int levels) {
            super(0, levels);
        }

        @Override
        public int compareTo(int target) {
            return -1;
        }
    }

    private static final int DEFAULT_MAX_LEVEL = 62;

    @NotNull
    private final HeadNode listHead;
    private int size;
    private final int maxLevel;
    private int levels = 1;

    /**
     * 构造跳表
     *
     * @param maxLevel 跳表链接最大层数。不能小于 {@code 1}
     * @throws IllegalArgumentException 跳表链接最大层数小于 {@code 1}
     */
    public IntSkipList(int maxLevel) {
        if (maxLevel < 1) {
            throw new IllegalArgumentException("跳表至少要有一层链");
        }
        this.maxLevel = maxLevel;
        this.listHead = new HeadNode(this.maxLevel);
    }

    /**
     * 构造跳表。跳表链接最大层数为 {@value #DEFAULT_MAX_LEVEL}
     */
    public IntSkipList() {
        this(DEFAULT_MAX_LEVEL);
    }

    public int size() {
        return this.size;
    }

    @NotNull
    private Node findLastLessThan(int target) {
        int level = this.levels;
        Node llt = this.listHead;
        while (level > 0 && llt.compareTo(target) < 0) { // 可以向右找
            Node next = llt.nextAtLevel(level);
            if (next != null && next.compareTo(target) < 0) {
                // 右侧仍有小于 target 的值
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
     * 查找并返回目标值是否在跳表中
     *
     * @param target 要查找的值
     * @return 如果跳表中包含目标值，返回 {@code true} ；否则，返回 {@code false}。
     */
    public boolean contains(int target) {
        Node previous = this.findLastLessThan(target);
        Node node = previous.nextAtLevel(1);
        return node != null && node.value == target;
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
     * 插入值
     *
     * @param value 要插入的值
     */
    public void add(int value) {
        int levels = this.randomLevel();
        Node newNode = new Node(value, levels);
        Node previous = this.listHead;
        int level = levels;
        while (level > 0 && previous.compareTo(value) <= 0) {
            Node next = previous.nextAtLevel(level);
            if (next != null && next.compareTo(value) <= 0) {
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
     * 从跳表中删除值。
     * @param value 要删除的值。
     * @return 如果找到并删除了值，返回 {@code true} ；否则，返回 {@code false}。
     */
    public boolean erase(int value) {
        Node lastLessThan = this.findLastLessThan(value);
        Node node = lastLessThan.nextAtLevel(1);
        if (node == null || node.value != value) return false;
        int level = node.forwards.length;
        Node previous = this.listHead;
        while (level > 0) {
            Node next = previous.nextAtLevel(level);
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
        Node head = this.listHead.nextAtLevel(1);
        return head == null ? "[]" : "[" + head.toString() + "]";
    }

    public String toGraphString() {
        return this.toGraphString(2);
    }

    public String toGraphString(int nodeChars) {
        nodeChars = Integer.min(3, nodeChars);
        StringBuilder sb = new StringBuilder();
        for (int level = this.levels; level > 0; level--) {
            sb.append(String.format("%2d", level)).append(": *");
            Node node = this.listHead.nextAtLevel(1);
            Node atLevel = this.listHead.nextAtLevel(level);
            while (node != null) {
                if (atLevel == null || atLevel != node) {
                    sb.append("---").append("-".repeat(nodeChars + 2));
                } else {
                    String format = " %" + nodeChars + "s ";
                    sb.append("-->")
                            .append(String.format(format, atLevel.value));
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
}
