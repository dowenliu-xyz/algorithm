package xyz.dowenliu.study.algo._11_sort;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * {@code int} 数组快速排序
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class QuickIntArraySort implements IntArraySort {
    @Override
    public void sort(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length <= 1) {
            return;
        }
        this.sortRange(array, 0, array.length - 1);
    }

    private void sortRange(@NotNull int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotPosition = this.partition(array, left, right);
        this.sortRange(array, left, pivotPosition - 1);
        this.sortRange(array, pivotPosition + 1, right);
    }

    private int partition(@NotNull int[] array, int left, int right) {
        int pivot = array[right];
        int storeIndex = left;
        for (int i = left; i <= right; i++) {
            if (array[i] < pivot) {
                int tmp = array[storeIndex];
                array[storeIndex++] = array[i];
                array[i] = tmp;
            }
        }

        array[right] = array[storeIndex];
        array[storeIndex] = pivot;

        return storeIndex;
    }
}
