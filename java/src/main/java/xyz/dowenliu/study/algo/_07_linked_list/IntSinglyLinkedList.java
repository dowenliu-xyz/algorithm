package xyz.dowenliu.study.algo._07_linked_list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * {@code int} 单链表
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntSinglyLinkedList implements IntLinkedList {
    /**
     * 链表节点
     */
    public static class Node implements IntLinkedList.Node {
        private int value;
        @Nullable
        private Node next;

        /**
         * 创建节点
         *
         * @param value 节点值
         * @param next  后续节点
         */
        private Node(int value, @Nullable Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public int getValue() {
            return this.value;
        }

        @Override
        @Nullable
        public Node getNext() {
            return this.next;
        }
    }

    /**
     * 内部使用的游标
     */
    private static class Cursor {
        @Nullable
        final Node previous;
        @Nullable
        final Node node;
        final int position;

        public Cursor(@Nullable Node previous, @Nullable Node node,
                      int position) {
            this.previous = previous;
            this.node = node;
            this.position = position;
        }

        @Nullable
        public Cursor next() {
            if (this.node == null) {
                return null;
            }
            return new Cursor(this.node, this.node.next, this.position + 1);
        }
    }

    @Nullable
    private Node head;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public @Nullable Node getFirstNode() {
        return this.head;
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
    @SuppressWarnings("DuplicatedCode")
    @Nullable
    private Cursor cursorAt(int index) {
        if (index < 0 || index > this.size) {
            return null;
        }
        Cursor cursor = new Cursor(null, this.head, 0);
        while (cursor != null && cursor.position != index) {
            cursor = cursor.next();
        }
        return cursor;
    }

    @Override
    @NotNull
    public Node getNode(int index) throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        assert this.size != 0 && this.head != null :
                "index 已通过检查，则 size 至少为1，头节点不为空";
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index 已通过检查，游标不可能是 null";
        Node node = cursor.node;
        assert node != null : "index 已通过检查，节点不可能是 null";
        return node;
    }

    @Nullable
    private Cursor find(@Nullable Cursor cursor, int value) {
        while (cursor != null && cursor.node != null) {
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
        Cursor cursor = this.find(this.cursorAt(0), value);
        return cursor == null ? null : cursor.node;
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
            this.head = new Node(value, null);
        } else {
            Cursor cursor = this.cursorAt(index);
            assert cursor != null : "链表非空，index 合法，游标必定不是 null";
            Node next = cursor.node;
            Node node = new Node(value, next);
            Node previous = cursor.previous;
            if (previous != null) {
                previous.next = node;
            }
            // 新增节点之后存在其他节点，所以新增的肯定不会是尾节点，但可能是头节点
            if (next == this.head) { // 更新头节点
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
        assert cursor != null : "index检查已通过，链表必定不为空，游标不可能为 null";
        assert cursor.node != null : "index 已通过检查，节点不可能是 null";
        Node nextNext = cursor.node.next;
        cursor.node.next = new Node(value, nextNext);
        this.size++;
    }

    @Nullable
    private Node removeByCursor(@NotNull Cursor cursor) {
        Node node = cursor.node;
        if (node == null) {
            return null; // nothing to remove here
        }
        Node previous = cursor.previous;
        Node next = node.next;
        if (previous == null) {
            // 删除的是头节点
            this.head = next;
        } else {
            previous.next = next;
        }
        node.next = null; // help GC
        this.size--;
        return node;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public int remove(int index) throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index 已通过检查，游标不可能是 null";
        Node removed = this.removeByCursor(cursor);
        assert removed != null : "index 已通过检查，要删除的节点不可能是 null";
        return removed.value;
    }

    @Override
    public boolean removeBy(int value) {
        Cursor cursor = this.find(this.cursorAt(0), value);
        if (cursor == null) {
            return false;
        }
        Node removed = this.removeByCursor(cursor);
        return removed != null;
    }

    @Override
    public int indexOf(int value) {
        Cursor cursor = this.find(this.cursorAt(0), value);
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
        Node previous = null;
        Node node = this.head;
        assert node != null : "链表不为空，头节点不可能是 null";
        Node next = node.next;
        int position = 0;
        while (position < this.size) {
            assert node != null : "索引未越界，node 不能为 null";
            node.next = previous;
            previous = node;
            node = next;
            if (next != null) {
                next = next.next;
            }
            position++;
        }
        this.head = previous;
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
        IntSinglyLinkedList clone;
        try {
            clone = (IntSinglyLinkedList) super.clone();
            clone.head = null;
            clone.size = 0;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(); // should not happen
        }
        Node cursor = this.head;
        while (cursor != null) {
            clone.addTail(cursor.value);
            cursor = cursor.next;
        }
        return clone;
    }

    @Override
    public String toString() {
        return "NoHeadIntSinglyLinkedList" + Arrays.toString(this.toArray());
    }
}
