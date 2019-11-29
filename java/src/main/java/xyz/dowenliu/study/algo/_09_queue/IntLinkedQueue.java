package xyz.dowenliu.study.algo._09_queue;

import java.util.NoSuchElementException;

/**
 * 基于单链表的 {@code int} 链式队列
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntLinkedQueue implements IntQueue {
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.value);
            Node next = this.next;
            while (next != null) {
                sb.append(", ").append(next.value);
                next = next.next;
            }
            return sb.toString();
        }
    }

    private Node head;
    private Node tail;

    @Override
    public boolean enqueue(int value) {
        if (this.tail == null) {
            Node node = new Node(value, null);
            this.head = this.tail = node;
        } else {
            this.tail.next = new Node(value, null);
            this.tail = this.tail.next;
        }
        return true;
    }

    @Override
    public int dequeue() throws NoSuchElementException {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        int ret = this.head.value;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        return ret;
    }

    @Override
    public int head() throws NoSuchElementException {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        return this.head.value;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "IntLinkedQueue[]";
        }
        return "IntLinkedQueue[" + this.head.toString() + "]";
    }
}
