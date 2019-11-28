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
         * 构建节点
         *
         * @param value 节点存储的值
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
        @Nullable
        final Node next;
        final int position;

        public Cursor(@Nullable Node previous, @Nullable Node node,
                      @Nullable Node next, int position) {
            this.previous = previous;
            this.node = node;
            this.next = next;
            this.position = position;
        }

        @Nullable
        public Cursor next() {
            if (this.node == null) {
                return null;
            }
            return new Cursor(this.node, this.node.next,
                    this.next == null ? null : this.next.next,
                    this.position + 1);
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
        if (index == this.size) {
            return new Cursor(this.tail, null, null, this.size);
        }
        Cursor cursor = new Cursor(
                null,
                this.head,
                this.head == null ? null : this.head.next,
                0
        );
        while (cursor != null && cursor.position != index) {
            cursor = cursor.next();
        }
        return cursor;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    @NotNull
    public Node getNode(int index) throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        assert this.size != 0 && this.head != null && this.tail != null :
                "index 已通过检查，则 size 至少为 1 ，头尾节点不为空";
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
        Cursor cursor = this.cursorAt(index);
        assert cursor != null;
        Node next = cursor.node;
        Node previous = cursor.previous;
        Node node = new Node(value, next);
        if (previous != null) {
            previous.next = node;
        }
        if (index == 0) {
            this.head = node;
        }
        if (index == this.size) {
            this.tail = node;
        }
        this.size++;
    }

    @Override
    public void addAfter(int index, int value)
            throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        if (index == this.size - 1) {
            Node node = new Node(value, null);
            assert this.tail != null : "索引指向尾节点，尾节点不应为 null";
            this.tail.next = node;
            this.tail = node;
        } else {
            Cursor cursor = this.cursorAt(index);
            assert cursor != null : "index检查已通过，链表必定不为空，游标不可能为 null";
            Node previous = cursor.node;
            assert previous != null : "index 已通过检查，节点不可能是 null";
            previous.next = new Node(value, previous.next);
        }
        this.size++;
    }

    @SuppressWarnings("DuplicatedCode")
    @Nullable
    private Node removeByCursor(@NotNull Cursor cursor) {
        Node node = cursor.node;
        if (node == null) {
            return null; // nothing to remove here
        }
        Node previous = cursor.previous;
        Node next = cursor.next;
        if (previous != null) {
            previous.next = next;
        }
        if (cursor.position == 0) {
            this.head = next;
        }
        if (cursor.position == this.size - 1) {
            this.tail = previous;
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

    @SuppressWarnings("DuplicatedCode")
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
        Node temp = this.head;
        this.head = previous;
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public IntList clone() {
        IntSinglyLinkedList clone;
        try {
            clone = (IntSinglyLinkedList) super.clone();
            clone.head = null;
            clone.tail = null;
            clone.size = 0;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(); // should not happen
        }
        Node cursor = this.head;
        int position = 0;
        while (cursor != null && position < this.size) {
            clone.addTail(cursor.value);
            cursor = cursor.next;
            position++;
        }
        return clone;
    }

    @Override
    public String toString() {
        return "NoHeadIntSinglyLinkedList" + Arrays.toString(this.toArray());
    }
}
