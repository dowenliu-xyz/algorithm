package xyz.dowenliu.study.algo._08_stack;

import java.util.NoSuchElementException;

/**
 * 泛型栈
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 * @param <E> 元素类型
 */
public interface Stack<E> {
    /**
     * 返回当前栈是否为空
     * @return 如果栈空，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean isEmpty();

    /**
     * 将元素压入栈
     *
     * @param e 要压入的元素
     * @return 如果成功入栈，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean push(E e);

    /**
     * 返回栈顶的元素
     * @return 栈顶的元素
     * @throws NoSuchElementException 如果栈是空的
     */
    E peek() throws NoSuchElementException;

    /**
     * 弹出栈顶的元素
     * @return 栈顶的元素
     * @throws NoSuchElementException 如果栈是空的
     */
    E pop() throws NoSuchElementException;
}
