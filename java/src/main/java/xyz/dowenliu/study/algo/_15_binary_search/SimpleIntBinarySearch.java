package xyz.dowenliu.study.algo._15_binary_search;

import org.jetbrains.annotations.NotNull;

/**
 * 简单二分查找。假设数组没有重复元素
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SimpleIntBinarySearch implements IntBinarySearch {
    @Override
    public int search(@NotNull int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] < key) left = mid + 1;
            else if (array[mid] > key) right = mid - 1;
            else return mid;
        }
        return -1;
    }
}
