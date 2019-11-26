package xyz.dowenliu.study.algo._05_array;

import java.util.Random;

/**
 * 合并两个已排序的数组
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntSortedArrayMerging {
    public static void main(String[] args) {
        IntArray array1 = new SizeFixedIntArray(10);
        IntArray array2 = new SizeFixedIntArray(10);
        // fill arrays
        Random random = new Random(System.currentTimeMillis());
        while (!array1.isFull()) {
            array1.append(random.nextInt(1000));
        }
        System.out.println("array1: " + array1);
        while (!array2.isFull()) {
            array2.append(random.nextInt(1000));
        }
        System.out.println("array2: " + array2);
        // sort arrays
        array1.sort();
        System.out.println("Sorted array1: " + array1);
        array2.sort();
        System.out.println("Sorted array2: " + array2);
        // merge
        IntArray merged = merge(array1, array2);
        System.out.println("Merge result: " + merged);
    }

    private static IntArray merge(IntArray source1, IntArray source2) {
        IntArray result = new SizeFixedIntArray(
                source1.capacity() + source2.capacity());
        while (!source1.isEmpty() && !source2.isEmpty()) {
                if (source1.get(0) <= source2.get(0)) {
                    result.append(source1.removeAt(0));
                } else {
                    result.append(source2.removeAt(0));
                }
        }
        if (source1.isEmpty()) {
            while (!source2.isEmpty()) {
                result.append(source2.removeAt(0));
            }
        } else {
            while (!source1.isEmpty()) {
                result.append(source1.removeAt(0));
            }
        }
        return result;
    }
}
