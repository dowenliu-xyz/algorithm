package xyz.dowenliu.study.algo._08_stack;

import java.util.NoSuchElementException;

/**
 * {@code int} 链式栈
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntLinkedStack implements IntStack {
    /**
     * 单链表节点
     */
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

    private Node top;

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public boolean push(int value) {
        this.top = new Node(value, this.top);
        return true;
    }

    @Override
    public int peek() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.top.value;
    }

    @Override
    public int pop() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node toRemove = this.top;
        this.top = toRemove.next;
        return toRemove.value;
    }

    @Override
    public String toString() {
        return "IntLinkedStack[" +
                (this.top == null ? "" : this.top.toString()) +
                ']';
    }
}
