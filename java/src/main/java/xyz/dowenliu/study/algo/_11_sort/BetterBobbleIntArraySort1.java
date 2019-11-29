package xyz.dowenliu.study.algo._11_sort;

/**
 * 优化的 {@code int} 数组冒泡排序。
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class BetterBobbleIntArraySort1 extends BobbleIntArraySort {
    @Override
    public void sort(int[] array) {
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break; // 这一轮没有交换，说明已经是有序的了
        }
    }
}
