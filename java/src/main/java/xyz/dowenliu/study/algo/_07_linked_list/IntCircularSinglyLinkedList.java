package xyz.dowenliu.study.algo._07_linked_list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@code int} 循环单链表
 * <p>create at 2019/11/27</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntCircularSinglyLinkedList implements IntLinkedList {
    /**
     * 链表节点
     */
    public static class Node implements IntLinkedList.Node {
        private int value;
        @NotNull
        private Node next; // 在循环链表中，next始终不为null

        /**
         * 创建头节点
         *
         * @param value 节点值
         */
        private Node(int value) {
            this.value = value;
            this.next = this;
        }

        /**
         * 创建节点
         *
         * @param value 节点值
         * @param next  后续节点。不允许为 {@code null}
         * @throws NullPointerException 后续节点为空
         */
        private Node(int value, @NotNull Node next) {
            this.value = value;
            this.next = Objects.requireNonNull(next);
        }

        @Override
        public int getValue() {
            return value;
        }

        @Override
        @NotNull
        public Node getNext() {
            return next;
        }
    }

    /**
     * 内部使用的游标
     */
    private static class Cursor {
        @NotNull
        final Node previous;
        @NotNull
        final Node node;
        final int position;

        public Cursor(@NotNull Node previous, @NotNull Node node,
                      int position) {
            this.previous = Objects.requireNonNull(previous);
            this.node = Objects.requireNonNull(node);
            this.position = position;
        }

        @NotNull
        public Cursor next() {
            return new Cursor(this.node, this.node.next, this.position + 1);
        }
    }

    @Nullable
    private Node head;
    @Nullable
    private Node tail;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    private void checkAccessIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }

    /**
     * 获取索引位置的游标
     *
     * @param index 索引
     * @return 如果 {@code index} 小于 {@code 0} 或者
     * {@code index } 大于等于 {@link #size()} ，返回 {@code null} ；
     * 否则，返回索引位置的游标
     */
    @Nullable
    private Cursor cursorAt(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        assert this.tail != null && this.head != null :
                "index 检查通过，size必定大于0，链表非空";
        Cursor cursor = new Cursor(this.tail, this.head, 0);
        while (cursor.position != index) {
            cursor = cursor.next();
        }
        return cursor;
    }

    @Override
    @NotNull
    public Node getNode(int index) throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        assert this.size != 0 && this.head != null && this.tail != null :
                "index 已通过检查，则 size 至少为1，头尾节点不为空";
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index 已通过检查，游标不可能是 null";
        return cursor.node;
    }

    @Override
    public int get(int index) throws IndexOutOfBoundsException {
        return this.getNode(index).value;
    }

    @Nullable
    private Cursor find(@Nullable Cursor cursor, int value) {
        while (cursor != null && cursor.position < this.size) {
            if (cursor.node.value == value) {
                return cursor;
            }
            cursor = cursor.next();
        }
        return null;
    }

    @Override
    @Nullable
    public Node nodeOfValue(int value) {
        Cursor cursor = this.find(cursorAt(0), value);
        return cursor == null ? null : cursor.node;
    }

    @Override
    public boolean contains(int value) {
        return this.nodeOfValue(value) != null;
    }

    @Override
    public int set(int index, int value) throws IndexOutOfBoundsException {
        Node node = this.getNode(index);
        int origin = node.value;
        node.value = value;
        return origin;
    }

    @Override
    public void addBefore(int index, int value)
            throws IndexOutOfBoundsException {
        this.checkInsertIndex(index);
        if (this.isEmpty()) {
            this.tail = this.head = new Node(value);
        } else {
            assert this.size >= 1;
            if (index == this.size) {
                this.addAfter(index - 1, value);
                return;
            }
            Cursor cursor = this.cursorAt(index);
            assert cursor != null : "链表非空，index 合法，游标必定不是 null";
            Node next = cursor.node;
            Node node = new Node(value, next);
            Node previous = cursor.previous;
            previous.next = node;
            // 新增节点之后存在其他节点，所以新增的肯定不会是尾节点，但可能是头节点
            if (next == this.head && previous == this.tail) { // 更新头节点
                this.head = node;
            }
        }
        this.size++;
    }

    @Override
    public void addAfter(int index, int value)
            throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index检查已通过，" +
                "链表必定不为空，存在头节点且各节点 next 不为 null，" +
                "游标不可能为 null";
        Node nextNext = cursor.node.next;
        Node next = new Node(value, nextNext);
        cursor.node.next = next;
        // 新增节点之前存在其他节点，所以新增的肯定不会是头节点，但可能是尾节点
        if (nextNext == this.head) { // 更新尾节点
            this.tail = next;
        }
        this.size++;
    }

    @NotNull
    private Node removeByCursor(@NotNull Cursor cursor) {
        Node previous = cursor.previous;
        Node node = cursor.node;
        if (node == previous) {
            // 只有一个节点
            this.tail = null;
            this.head = null;
        } else {
            previous.next = node.next;
            boolean headRemoved = previous == this.tail;
            boolean tailRemoved = node.next == this.head;
            if (headRemoved) {
                this.head = previous.next;
            }
            if (tailRemoved) {
                this.tail = previous;
            }
        }
        //noinspection ConstantConditions
        node.next = null; // help GC
        this.size--;
        return node;
    }

    @Override
    public int remove(int index) throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index检查已通过，" +
                "链表必定不为空，存在头节点且各节点 next 不为 null，" +
                "游标不可能为 null";
        Node removed = removeByCursor(cursor);
        return removed.value;
    }

    @Override
    public boolean removeBy(int value) {
        Cursor cursor = this.find(cursorAt(0), value);
        if (cursor == null) {
            return false;
        }
        this.removeByCursor(cursor);
        return true;
    }

    @Override
    public int indexOf(int value) {
        Cursor cursor = this.find(cursorAt(0), value);
        if (cursor == null) {
            return -1;
        }
        return cursor.position;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public int lastIndexOf(int value) {
        Cursor cursor = this.cursorAt(0);
        int lastPosition = -1;
        while (cursor != null) {
            cursor = this.find(cursor, value);
            if (cursor != null) {
                lastPosition = cursor.position;
                cursor = cursor.next();
            }
        }
        return lastPosition;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public int[] toArray() {
        int[] array = new int[this.size];
        Node cursor = this.head;
        int position = 0;
        while (position < this.size && cursor != null) {
            array[position] = cursor.value;
            cursor = cursor.next;
            position++;
        }
        return array;
    }

    @Override
    public void clear() {
        while (!this.isEmpty()) {
            this.remove(0);
        }
    }

    @Override
    public void reverse() {
        if (this.isEmpty()) {
            return;
        }
        Node pre = null;
        Cursor cursor = cursorAt(0);
        if (cursor == null) {
            return;
        }
        while (pre != this.tail) {
            Node node = cursor.node;
            Node previous = cursor.previous;
            cursor = cursor.next();
            node.next = previous;
            pre = node;
        }
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntList)) return false;
        IntList that = (IntList) o;
        return Arrays.equals(this.toArray(), that.toArray());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.toArray());
    }

    @Override
    public IntList clone() {
        IntCircularSinglyLinkedList clone;
        try {
            clone = (IntCircularSinglyLinkedList) super.clone();
            clone.head = null;
            clone.tail = null;
            clone.size = 0;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(); // should not happen
        }
        Node cursor = this.head;
        while (cursor != null) {
            clone.addTail(cursor.value);
            cursor = cursor.next;
            if (cursor == this.head) {
                break;
            }
        }
        return clone;
    }

    @Override
    public String toString() {
        return "IntCircularSinglyLinkedList[" +
                Arrays.toString(this.toArray()) + "]";
    }
}
