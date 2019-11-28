package xyz.dowenliu.study.algo._07_linked_list;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@code int} 循环双向链表
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntCircularDoublyLinkedList implements IntLinkedList {
    /**
     * 双向链表节点
     */
    public static class Node implements IntLinkedList.Node {
        private int value;
        @NotNull
        private Node next; // 在循环链表中，next始终不为null
        @NotNull
        private Node previous; // 在循环链表中，previous始终不为null

        /**
         * 构建头节点
         *
         * @param value 节点存储的值
         */
        private Node(int value) {
            this.value = value;
            this.next = this;
            this.previous = this;
        }

        /**
         * 构建节点
         *
         * @param value    节点存储的值
         * @param next     后续节点
         * @param previous 前续节点
         * @throws NullPointerException 后续或前续节点为空
         */
        private Node(int value, @NotNull Node next, @NotNull Node previous) {
            this.value = value;
            this.next = Objects.requireNonNull(next);
            this.previous = Objects.requireNonNull(previous);
        }

        @Override
        public int getValue() {
            return this.value;
        }

        @Override
        @NotNull
        public Node getNext() {
            return this.next;
        }

        /**
         * 返回节点的前续节点
         *
         * @return 节点的前续节点
         */
        @NotNull
        public Node getPrevious() {
            return this.previous;
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

        @Nullable
        public Cursor previous() {
            if (this.previous == null || this.position == 0) {
                return null;
            }
            return new Cursor(this.previous.previous, this.previous,
                    this.node, this.position - 1);
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
     * {@code index} 大于等于 {@link #size()} ，返回 {@code null} ；
     * 否则，返回索引位置的游标
     */
    @SuppressWarnings("DuplicatedCode")
    @Nullable
    private Cursor cursorAt(int index) {
        if (index < 0 || index > this.size) {
            return null;
        }
        if (index == this.size) {
            return new Cursor(
                    this.tail,
                    this.head,
                    this.head == null ? null : this.head.next,
                    this.size
            );
        }
        boolean forward = index <= this.size / 2;
        Cursor cursor = forward ?
                new Cursor(
                        this.tail,
                        this.head,
                        this.head == null ? null : this.head.next,
                        0
                ) :
                new Cursor(
                        this.tail == null ? null : this.tail.previous,
                        this.tail,
                        this.head,
                        this.size - 1
                );
        while (cursor != null && cursor.position != index) {
            cursor = forward ? cursor.next() : cursor.previous();
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
    private Cursor find(@Nullable Cursor cursor, int value, boolean forward) {
        while (cursor != null && cursor.node != null) {
            if (cursor.position < 0 || cursor.position >= this.size) {
                break;
            }
            if (cursor.node.value == value) {
                return cursor;
            }
            cursor = forward ? cursor.next() : cursor.previous();
        }
        return null;
    }

    @Override
    @Nullable
    public Node nodeOfValue(int value) {
        Cursor cursor = this.find(this.cursorAt(0), value, true);
        return cursor == null ? null : cursor.node;
    }

    @Override
    public int set(int index, int value) throws IndexOutOfBoundsException {
        Node node = this.getNode(index);
        int origin = node.value;
        node.value = value;
        return origin;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void addBefore(int index, int value)
            throws IndexOutOfBoundsException {
        this.checkInsertIndex(index);
        if (this.isEmpty()) {
            this.tail = this.head = new Node(value);
            this.size++;
            return;
        }
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "链表非空，index 合法，游标必定不是 null";
        Node next = cursor.node;
        Node previous = cursor.previous;
        assert previous != null && next != null :
                "循环链表非空，各节点不可能是 null";
        Node node = new Node(value, next, previous);
        previous.next = node;
        if (index == 0) {
            this.head = node;
        }
        next.previous = node;
        if (index == this.size) {
            this.tail = node;
        }
        this.size++;
    }

    @Override
    public void addAfter(int index, int value)
            throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index检查已通过，链表必定不为空，游标不可能为 null";
        Node previous = cursor.node;
        Node next = cursor.next;
        assert previous != null && next != null :
                "index检查已通过，节点不可能为 null";
        Node node = new Node(value, next, previous);
        previous.next = node;
        next.previous = node;
        if (index == this.size - 1) {
            this.tail = node;
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
        assert previous != null && next != null :
                "当前节点不是 null，那么前后节点也不会是 null";
        if (this.size == 1 && cursor.position == 0) {
            // 只有一个节点
            this.tail = this.head = null;
        } else {
            previous.next = next;
            if (cursor.position == 0) {
                this.head = next;
            }
            next.previous = previous;
            if (cursor.position == this.size - 1) {
                this.tail = previous;
            }
        }
        //noinspection ConstantConditions
        node.next = null; // help GC
        this.size--;
        return node;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public int remove(int index) throws IndexOutOfBoundsException {
        this.checkAccessIndex(index);
        Cursor cursor = this.cursorAt(index);
        assert cursor != null : "index检查已通过，游标不可能是 null";
        Node removed = this.removeByCursor(cursor);
        assert removed != null : "index检查已通过，要删除的节点不可能是 null";
        return removed.value;
    }

    @Override
    public boolean removeBy(int value) {
        Cursor cursor = this.find(this.cursorAt(0), value, true);
        if (cursor == null) {
            return false;
        }
        Node removed = this.removeByCursor(cursor);
        return removed != null;
    }

    @Override
    public int indexOf(int value) {
        Cursor cursor = this.find(this.cursorAt(0), value, true);
        if (cursor == null) {
            return -1;
        }
        return cursor.position;
    }

    @Override
    public int lastIndexOf(int value) {
        Cursor cursor = this.find(this.cursorAt(this.size - 1), value, false);
        if (cursor == null) {
            return -1;
        }
        return cursor.position;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void reverse() {
        if (this.isEmpty()) {
            return;
        }
        Node previous = this.tail;
        Node node = this.head;
        assert node != null && previous != null :
                "链表不为空，头尾节点不可能是 null";
        Node next = node.next;
        int position = 0;
        while (position < this.size) {
            node.next = previous;
            node.previous = next;
            previous = node;
            node = next;
            if (next != this.head) {
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
        IntCircularDoublyLinkedList clone;
        try {
            clone = (IntCircularDoublyLinkedList) super.clone();
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
        return "IntCirclularDoublyLinkedList" + Arrays.toString(this.toArray());
    }
}
