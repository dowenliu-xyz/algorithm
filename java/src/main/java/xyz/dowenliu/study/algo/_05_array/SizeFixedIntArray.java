package xyz.dowenliu.study.algo._05_array;

/**
 * 定长int数组。提供数据的插入、删除、按照下标随机访问操作
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SizeFixedIntArray {
    // 内部数组，用于保存数据
    private int[] data;
    // 数组中实际元素个数
    private int size;

    /**
     * 构建定长int数组
     *
     * @param capacity 存储容量
     */
    public SizeFixedIntArray(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    public int getCapacity() {
        return this.data.length;
    }

    public int getSize() {
        return size;
    }

    /**
     * 根据索引，查找并返回数组对应位置存储的值
     *
     * @param index 索引
     * @return 找到的值
     * @throws IndexOutOfBoundsException 如果索引小于0或大于等于{@link #getSize()}
     */
    public int get(int index)
            throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
        return this.data[index];
    }

    /**
     * 插入值
     *
     * @param index 插入位置。在{@code index} 指定的位置前插入
     * @param value 要插入的值
     * @return {@code true} 成功插入；{@code false} 插入失败，数据未变更
     * @throws IndexOutOfBoundsException 尝试在有效位置外插入值，破坏存储的连续性
     */
    public boolean insertAt(int index, int value)
            throws IndexOutOfBoundsException {
        if (this.size == this.data.length) {
            System.out.println("数组已满，没有可插入的空间"); // 日志
            return false;
        }
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(index);
        }
        for (int i = this.size; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[index] = value;
        this.size++;
        return true;
    }

    /**
     * 删除值
     *
     * @param index 要删除值的索引位置
     * @throws IndexOutOfBoundsException 索引位置没有有效值
     */
    public void removeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(index);
        }
        for (int i = index + 1; i < this.size; i++) {
            data[i - 1] = data[i];
        }
        this.data[size - 1] = 0;
        this.size--;
    }

    public void printAll() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SizeFixedIntArray array = new SizeFixedIntArray(5);
        array.printAll();
        try {
            array.insertAt(2, 3);
        } catch (IndexOutOfBoundsException e) {
            array.printAll();
            System.out.println("insert at 2, catch " + e.getMessage());
        }
        array.insertAt(0, 3); // 3
        array.printAll();
        array.insertAt(0, 4); // 4 3
        array.printAll();
        array.insertAt(1, 5); // 4 5 3
        array.printAll();
        array.insertAt(3, 9); // 4 5 3 9
        array.printAll();
        array.insertAt(3, 10); // 4 5 3 10 9
        array.printAll();
        boolean success = array.insertAt(0, 0);
        if (!success) {
            System.out.println("Failed to insert, cause the array is full");
        } else {
            System.out.println("Oh, inserted!");
            array.printAll();
        }
        array.removeAt(0);
        array.printAll();
        try {
            array.removeAt(4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("catch " + e.getMessage());
        }
        System.out.println("value at index 1 is " + array.get(1));
        try {
            System.out.println("value at index 4 is " + array.get(4));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get value at 4, catch " + e.getMessage());
        }
    }
}
