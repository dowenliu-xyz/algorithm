package xyz.dowenliu.study.algo._09_queue;

import java.util.NoSuchElementException;

/**
 * {@code int} 队列
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface IntQueue {
    /**
     * 入队操作
     *
     * @param value 入队的值
     * @return 入队成功，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean enqueue(int value);

    /**
     * 出队操作
     *
     * @return 队头的值
     * @throws NoSuchElementException 队列为空
     */
    int dequeue() throws NoSuchElementException;

    /**
     * 查看队头的值
     *
     * @return 队头的值
     * @throws NoSuchElementException 队列为空
     */
    int head() throws NoSuchElementException;

    /**
     * 返回队列是否为空
     * @return 队列为空，返回 {@code true} ；否则，返回 {@code false}
     */
    boolean isEmpty();
}
