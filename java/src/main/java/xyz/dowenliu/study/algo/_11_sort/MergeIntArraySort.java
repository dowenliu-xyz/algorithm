package xyz.dowenliu.study.algo._11_sort;

import org.jetbrains.annotations.NotNull;

/**
 * {@code int} 数组归并排序
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class MergeIntArraySort implements IntArraySort {
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
        int middle = (left + right) / 2;
        this.sortRange(array, left, middle);
        this.sortRange(array, middle + 1, right);
        this.merge(array, left, middle, right);
    }

    private void merge(@NotNull int[] array, int left, int middle, int right) {
        int[] leftArray = new int[middle - left + 1];
        int[] rightArray = new int[right - middle];
        // leftArray maybe shorter than rightArray
        System.arraycopy(array, left, leftArray, 0, middle - left + 1);
        System.arraycopy(array, middle + 1, rightArray, 0, right - middle);
        int lp = 0;
        int rp = 0;
        int ap = left;
        while (ap <= right) {
            if (lp == leftArray.length) {
                array[ap++] = rightArray[rp++];
                continue;
            }
            if (rp == rightArray.length) {
                array[ap++] = leftArray[lp++];
                continue;
            }
            if (leftArray[lp] <= rightArray[rp]) {
                array[ap++] = leftArray[lp++];
            } else {
                array[ap++] = rightArray[rp++];
            }
        }
    }
}
