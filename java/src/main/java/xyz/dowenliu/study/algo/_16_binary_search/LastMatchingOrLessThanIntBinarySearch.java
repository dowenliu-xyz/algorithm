package xyz.dowenliu.study.algo._16_binary_search;

import org.jetbrains.annotations.NotNull;
import xyz.dowenliu.study.algo._15_binary_search.IntBinarySearch;

/**
 * 二分查找。查找最后一个值小于等于给定值的元素
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class LastMatchingOrLessThanIntBinarySearch implements IntBinarySearch {
    @Override
    public int search(@NotNull int[] array, int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (array[mid] <= key) {
                if (mid == array.length - 1 || array[mid + 1] > key) return mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
