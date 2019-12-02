package xyz.dowenliu.study.algo._16_binary_search;

import org.jetbrains.annotations.NotNull;
import xyz.dowenliu.study.algo._15_binary_search.IntBinarySearch;

/**
 * 二分查找。查找第一个值等于给定值的元素
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class FirstMatchingIntBinarySearch implements IntBinarySearch {
    @Override
    public int search(@NotNull int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] == key) {
                if (mid == 0 || array[mid - 1] < key) return mid;
                right = mid - 1;
            } else if (array[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
