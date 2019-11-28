package xyz.dowenliu.study.algo._08_stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 基于数组的线性 {@code int} 栈
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntArrayStack implements IntStack {
    private int[] data;
    private int position = -1;

    public IntArrayStack(int capacity) {
        this.data = new int[capacity];
    }

    @Override
    public boolean isEmpty() {
        return this.position == -1;
    }

    @Override
    public boolean push(int value) {
        if (this.position == this.data.length - 1) {
            return false;
        }
        this.position++;
        this.data[this.position] = value;
        return true;
    }

    @Override
    public int peek() throws NoSuchElementException {
        if (this.position == -1) {
            throw new NoSuchElementException();
        }
        return this.data[this.position];
    }

    @Override
    public int pop() throws NoSuchElementException {
        if (this.position == -1) {
            throw new NoSuchElementException();
        }
        int value = this.data[this.position];
        this.position--;
        return value;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "IntArrayStack[empty][capacity:" + this.data.length + "]";
        }
        int[] values = Arrays.copyOfRange(
                this.data, 0, this.position + 1);
        return "IntArrayStack" +
                Arrays.toString(values) +
                "[capacity:" + this.data.length + "]";
    }
}
