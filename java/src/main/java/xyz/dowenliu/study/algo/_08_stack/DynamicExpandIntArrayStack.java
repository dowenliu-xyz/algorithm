package xyz.dowenliu.study.algo._08_stack;

import java.util.Arrays;

/**
 * 自动扩容数组 {@code int} 栈
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class DynamicExpandIntArrayStack extends IntArrayStack {
    /**
     * 构造一个自动扩容线性 {@code int} 栈。初始容量为 {@code 8}
     */
    public DynamicExpandIntArrayStack() {
        this(8);
    }

    /**
     * 构造一个自动扩容线性 {@code int} 栈。具有指定初始容量
     *
     * @param capacity 初始容量。需要大于 {@code 0}
     * @throws IllegalArgumentException 指定容量参数小于等于 {@code 0}
     */
    public DynamicExpandIntArrayStack(int capacity) {
        super(capacity);
    }

    @Override
    public boolean push(int value) {
        boolean pushed = super.push(value);
        if (!pushed) {
            int[] originData = this.data;
            int[] data = new int[originData.length * 2];
            System.arraycopy(originData, 0, data, 0, originData.length);
            this.data = data;
            return super.push(value);
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "DynamicExpandIntArrayStack[empty][capacity:" +
                    this.data.length + "]";
        }
        int[] values = Arrays.copyOfRange(
                this.data, 0, this.position + 1);
        return "DynamicExpandIntArrayStack" +
                Arrays.toString(values) +
                "[capacity:" + this.data.length + "]";
    }
}
