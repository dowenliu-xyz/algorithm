package xyz.dowenliu.study.algo._13_sort;

import xyz.dowenliu.study.algo._11_sort.IntArraySort;

/**
 * 计数排序
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntCountingSort implements IntArraySort {
    @SuppressWarnings("DuplicatedCode")
    @Override
    public void sort(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length <= 1) {
            return;
        }
        // 扫描数组中数据范围
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if (min > i) min = i;
            if (max < i) max = i;
        }
        // 计数器数组
        int[] counts = new int[max - min + 1];
        // 数数组中各元素的个数到计数器数组中
        for (int i : array) {
            counts[i - min]++;
        }
        // 累加计数器
        for (int i = 1; i < max - min + 1; i++) {
            counts[i] = counts[i - 1] + counts[i];
        }
        // 结果数组
        int[] result = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) { // 倒序访问源数组
            result[counts[array[i] - min] - 1] = array[i]; // 计数器指明了当前值排序结果位置
            counts[array[i] - min]--; // 计数器减小
        }
        System.arraycopy(result, 0, array, 0, array.length);
    }
}
