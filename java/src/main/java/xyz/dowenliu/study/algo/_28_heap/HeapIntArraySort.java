package xyz.dowenliu.study.algo._28_heap;

import org.jetbrains.annotations.NotNull;
import xyz.dowenliu.study.algo._11_sort.IntArraySort;

/**
 * {@code int} 数组堆排序
 * <p>create at 2019/12/13</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class HeapIntArraySort implements IntArraySort {
    @Override
    public void sort(int[] array) {
        if (array == null) return;
        buildHeap(array);
        int k = array.length - 1;
        while (k > 0) {
            swap(array, 0, k); // 将堆顶交换到堆外
            --k; // 缩小堆
            heapify(array, k + 1, 0); // 对缩小的堆从上往下堆化
        }
    }

    // 建堆
    private static void buildHeap(int @NotNull [] array) {
        // 从子树逐层向上做从上往下堆化
        for (int i = (array.length - 1) / 2; i >= 0; i--) {
            heapify(array, array.length, i);
        }
    }

    // 从上往下堆化
    private static void heapify(int[] array, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 + 1 < n && array[maxPos] < array[i * 2 + 1])
                maxPos = i * 2 + 1;
            if (i * 2 + 2 < n && array[maxPos] < array[i * 2 + 2])
                maxPos = i * 2 + 2;
            if (maxPos == i) break;
            swap(array, i, maxPos);
            i = maxPos;
        }
    }

    private static void swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }
}
