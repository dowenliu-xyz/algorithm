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
    public static class Node {
        protected final int value;
        @Nullable
        protected final Node @NotNull [] forwards;

        public Node(int value, int levels) {
            this.value = value;
            assert levels > 0;
            this.forwards = new Node[levels];
        }

        public int compareTo(int value) {
            return Integer.compare(this.value, value);
        }

        @Nullable
        public Node nextAtLevel(int level) {
            if (level <= 0 || level > this.forwards.length) return null;
            // should never happen;
            return this.forwards[level - 1];
        }

        public void setNextAtLevel(int level, @Nullable Node next) {
            if (level <= 0 || level > this.forwards.length) return;
            // should never happen;
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

    private static class HeadNode extends Node {
        public HeadNode(int levels) {
            super(0, levels);
        }

        @Override
        public int compareTo(int value) {
            return -1;
        }
    }

    private static final int DEFAULT_MAX_LEVEL = 62;

    @NotNull
    private final HeadNode listHead;
    private int size;
    private final int maxLevel;
    private int level = 1;

    public IntSkipList(int maxLevel) {
        if (maxLevel < 1) {
            throw new IllegalArgumentException("跳表至少要有一层链");
        }
        this.maxLevel = maxLevel;
        this.listHead = new HeadNode(this.maxLevel);
    }

    public IntSkipList() {
        this(DEFAULT_MAX_LEVEL);
    }

    public int size() {
        return this.size;
    }

    @NotNull
    private Node findLastLessThan(int value) {
        int level = this.level;
        Node llt = this.listHead;
        while (level > 0 && llt.compareTo(value) < 0) { // 可以向右找
            Node next = llt.nextAtLevel(level);
            if (next != null && next.compareTo(value) < 0) {
                // 右侧仍有小于value的值
                llt = next;
            } else {
                // 右侧的值都大于等于 value
                // 下降到下一层
                level--;
            }
        }
        return llt;
    }

    public boolean search(int value) {
        Node previous = this.findLastLessThan(value);
        Node node = previous.nextAtLevel(1);
        return node != null && node.value == value;
    }

    private int randomLevel() {
        Random random = ThreadLocalRandom.current();
        int level = 1;
        while (random.nextInt() % 2 == 1 && level < this.maxLevel) {
            level++;
        }
        return level;
    }

    public void add(int value) {
        int levels = this.randomLevel();
        Node newNode = new Node(value, levels);
        Node previous = this.listHead;
        int level = levels;
        while (level > 0 && previous.compareTo(value) < 0) {
            Node next = previous.nextAtLevel(level);
            if (next != null && next.compareTo(value) < 0) {
                previous = next;
            } else {
                newNode.setNextAtLevel(level, next);
                previous.setNextAtLevel(level, newNode);
                level--;
            }
        }
        if (levels > this.level) {
            this.level = levels;
        }
        this.size++;
    }

    public boolean erase(int value) {
        Node lastLessThan = this.findLastLessThan(value);
        Node node = lastLessThan.nextAtLevel(1);
        if (node == null || node.value != value) return false;
        int level = node.forwards.length;
        Node previous = this.listHead;
        while (level > 0) {
            Node next = previous.nextAtLevel(level);
            if (next == null) {
                level--; // this should never happen
            } else if (next != node) {
                previous = next;
            } else {
                previous.setNextAtLevel(level, next.nextAtLevel(level));
                next.setNextAtLevel(level, null); // help GC
                level--;
            }
        }
        this.size--;
        return true;
    }

    @Override
    public String toString() {
        Node head = this.listHead.nextAtLevel(1);
        return head == null ? "[]" : "[" + head.toString() + "]";
    }

    public String toGraphString() {
        StringBuilder sb = new StringBuilder();
        for (int level = this.level; level > 0; level--) {
            sb.append(String.format("%2d", level)).append(": *");
            Node node = this.listHead.nextAtLevel(1);
            Node atLevel = this.listHead.nextAtLevel(level);
            while (node != null) {
                if (atLevel == null || atLevel != node) {
                    sb.append("---").append("----");
                } else {
                    sb.append("-->").append(String.format("%3d ", atLevel.value));
                    atLevel = atLevel.nextAtLevel(level);
                }
                node = node.nextAtLevel(1);
            }
            sb.append("--> NIL\n");
        }
        return sb.toString();
    }
}
