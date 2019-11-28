package xyz.dowenliu.study.algo._08_stack;

import java.util.NoSuchElementException;

/**
 * {@code int} 栈
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface IntStack extends Cloneable {
    /**
     * 返回当前栈是否为空
     * @return 如果栈空，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean isEmpty();

    /**
     * 将值压入栈
     *
     * @param value 要压入的值
     * @return 如果成功入栈，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean push(int value);

    /**
     * 返回栈顶的值
     * @return 栈顶的值
     * @throws NoSuchElementException 如果栈是空的
     */
    int peek() throws NoSuchElementException;

    /**
     * 弹出栈顶的值
     * @return 栈顶的值
     * @throws NoSuchElementException 如果栈是空的
     */
    int pop() throws NoSuchElementException;
}
