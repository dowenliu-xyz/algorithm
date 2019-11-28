package xyz.dowenliu.study.algo._08_stack;

import java.util.NoSuchElementException;

/**
 * 基于单链表的链式泛型栈
 * <p>create at 2019/11/28</p>
 *
 * @param <E> 元素类型
 * @author liufl
 * @since version 1.0
 */
public class LinkedStack<E> implements Stack<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E e, Node<E> next) {
            this.element = e;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.element);
            Node<E> next = this.next;
            while (next != null) {
                sb.append(", ").append(next.element);
                next = next.next;
            }
            return sb.toString();
        }
    }

    private Node<E> top;

    @Override
    public boolean isEmpty() {
        return this.top == null;
    }

    @Override
    public boolean push(E e) {
        this.top = new Node<>(e, this.top);
        return true;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.top.element;
    }

    @Override
    public E pop() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> toRemove = this.top;
        this.top = toRemove.next;
        return toRemove.element;
    }

    @Override
    public String toString() {
        return "LinedStack[" +
                (this.top == null ? "" : this.top.toString()) +
                ']';
    }
}
