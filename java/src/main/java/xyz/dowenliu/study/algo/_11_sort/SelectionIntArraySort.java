package xyz.dowenliu.study.algo._11_sort;

/**
 * {@code int} 数组选择排序
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SelectionIntArraySort implements IntArraySort {
    @Override
    public void sort(int[] array) {
        if (array == null) {
            return;
        }
        if (array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minPosition = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPosition]) {
                    minPosition = j;
                }
            }

            int temp = array[i];
            array[i] = array[minPosition];
            array[minPosition] = temp;
        }
    }
}
