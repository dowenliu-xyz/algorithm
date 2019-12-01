package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;
import xyz.dowenliu.study.algo._11_sort.IntArraySort;

/**
 * 基数排序
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntRadixSort implements IntArraySort {
    @SuppressWarnings("DuplicatedCode")
    @Override
    public void sort(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length <= 1) {
            return;
        }
        // 扫描数据范围
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if (min > i) min = i;
            if (max < i) max = i;
        }
        // 执行偏移，使所有值为非负
        for (int i = 0; i < array.length; i++) {
            array[i] -= min;
        }
        // 从个位开始，对数组进行按"指数"进行排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            this.countingSort(array, exp);
        }
        // 恢复偏移
        for (int i = 0; i < array.length; i++) {
            array[i] += min;
        }
    }

    private void countingSort(@NotNull int[] array, int exp) {
        int[] counts = new int[10];

        for (int value : array) {
            counts[(value / exp) % 10]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            result[counts[(array[i] / exp) % 10] - 1] = array[i];
            counts[(array[i] / exp) % 10]--;
        }

        System.arraycopy(result, 0, array, 0, result.length);
    }
}
