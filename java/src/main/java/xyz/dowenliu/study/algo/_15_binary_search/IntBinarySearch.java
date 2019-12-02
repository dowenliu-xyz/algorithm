package xyz.dowenliu.study.algo._15_binary_search;

/**
 * 对 {@code int} 进行二分查找
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface IntBinarySearch {
    /**
     * 执行二分查找
     * @param array 已升序排好序的数组
     * @param key 要查找的值
     * @return 值所在位置索引。如果没有找到，返回 {@code -1}
     */
    int search(int[] array, int key);
}
