package xyz.dowenliu.study.algo._07_linked_list;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 无头 {@code int} 单链表
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntSinglyLinkedList implements IntList, Serializable {
    public static class Node implements Serializable {
        private int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node head;
    private int size;

    @Override
    public int size() {
        return this.size;
    }

    private Node nodeAt(int index) {
        if (index < 0) {
            return null;
        }
        Node cursor = this.head;
        int position = 0;
        while (position != index && cursor != null) {
            cursor = cursor.next;
            position++;
        }

        return cursor;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }

    public Node getNode(int index) throws IndexOutOfBoundsException {
        this.checkIndex(index);
        Node node = this.nodeAt(index);
        assert node != null : "index checking passed, node should not be null";
        return node;
    }

    @Override
    public int get(int index) throws IndexOutOfBoundsException {
       return this.getNode(index).value;
    }

    private Node nodeBeforeValue(int value) {
        Node cursor = this.head;
        if (cursor == null) {
            // no node in this list
            return null;
        }
        if (cursor.value == value) {
            // this node with value is head, and no node is before head.
            return null;
        }
        boolean found = false;
        while (cursor.next != null) {
            if (cursor.next.value == value) {
                found = true;
                break;
            }
            cursor = cursor.next;
        }
        return found ? cursor : null;
    }

    private Node nodeOfValue(int value) {
        Node beforeValue = this.nodeBeforeValue(value);
        if (beforeValue == null) {
            if (this.head != null && this.head.value == value) {
                return this.head;
            }
            return null;
        }
        return beforeValue.next;
    }

    @Override
    public boolean contains(int value) {
        return nodeOfValue(value) != null;
    }

    @Override
    public int set(int index, int value) throws IndexOutOfBoundsException {
        this.checkIndex(index);
        Node node = this.nodeAt(index);
        assert node != null : "index checking passed, node should not be null";
        int origin = node.value;
        node.value = value;
        return origin;
    }

    private void addHeader(Node node) {
        Objects.requireNonNull(node);
        node.next = head;
        head = node;
        this.size++;
    }

    private void addHeader(int value) {
        Node node = new Node(value, null);
        this.addHeader(node);
    }

    @Override
    public void addBefore(int index, int value)
            throws IndexOutOfBoundsException {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        if (index == 0) {
            this.addHeader(value);
            return;
        }

        // 1 <= index <= size
        // 0 <= index - 1 <= size - 1
        this.addAfter(index - 1, value);
    }

    @Override
    public void addAfter(int index, int value)
            throws IndexOutOfBoundsException {
        this.checkIndex(index);
        Node node = this.nodeAt(index);
        assert node != null : "index checking passed, node should not be null";
        node.next = new Node(value, node.next);
        this.size++;
    }

    @Override
    public int remove(int index) throws IndexOutOfBoundsException {
        this.checkIndex(index);
        Node previous = this.nodeAt(index - 1);
        Node node;
        if (previous == null) {
            assert index == 0 : "only head node has no previous node";
            node = this.head; //
            assert node != null : "index checking is passed，" +
                    "so head node, the node at index 0, must exist.";
            this.head = node.next;
        } else {
            node = previous.next;
            assert node != null : "index checking is passed," +
                    " so the node at index " + index + " must exist.";
            previous.next = node.next;
        }
        node.next = null; // help GC
        this.size--;
        return node.value;
    }

    @Override
    public boolean removeBy(int value) {
        Node previous = this.nodeBeforeValue(value);
        if (previous != null) {
            Node node = previous.next;
            assert node != null;
            previous.next = node.next;
            node.next = null; // help GC
            this.size--;
            return true;
        }
        if (this.head != null && this.head.value == value) {
            remove(0);
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(int value) {
        Node cursor = this.head;
        int position = 0;
        boolean found = false;
        while (cursor != null) {
            if (cursor.value == value) {
                found = true;
                break;
            }
            cursor = cursor.next;
            position++;
        }
        return found ? position : -1;
    }

    @Override
    public int lastIndexOf(int value) {
        Node cursor = this.head;
        int position = 0;
        int lastPosition = -1;
        while (cursor != null) {
            if (cursor.value == value) {
                lastPosition = position;
            }
            cursor = cursor.next;
            position++;
        }
        return lastPosition;
    }

    @Override
    public int[] toArray() {
        int[] array = new int[this.size];
        Node cursor = this.head;
        int index = 0;
        while (cursor != null) {
            array[index] = cursor.value;
            cursor = cursor.next;
            index++;
        }
        return array;
    }

    @Override
    public void clear() {
        while (this.head != null) {
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
        Node next = node.next;
        while (node != null) {
            node.next = previous;
            previous = node;
            node = next;
            if (next != null) {
                next = next.next;
            }
        }
        this.head = previous;
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
    public String toString() {
        return "NoHeadIntSinglyLinkedList[" +
                Arrays.toString(this.toArray()) + "]";
    }
}
