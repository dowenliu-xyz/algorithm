package xyz.dowenliu.study.algo._05_array;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * <p>create at 2019/11/25</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class DynamicExpandIntArrayTest {
    @Test
    public void test() {
        IntArray array = new DynamicExpandIntArray(5);
        assertThat(array.capacity()).isEqualTo(5);
        assertThat(array.values()).isEqualTo(new int[]{});
        assertThat(array.isEmpty()).isTrue();
        try {
            array.insertAt(2, 3);
            fail("should throw IndexOutOfBoundException");
        } catch (IndexOutOfBoundsException ignored) {
            // ignored
        }
        assertThat(array.insertAt(0, 3)).isTrue(); // 3
        assertThat(array.values()).isEqualTo(new int[]{3});
        assertThat(array.size()).isEqualTo(1);
        assertThat(array.insertAt(0, 4)).isTrue(); // 4 3
        assertThat(array.values()).isEqualTo(new int[]{4, 3});
        assertThat(array.size()).isEqualTo(2);
        assertThat(array.insertAt(1, 5)).isTrue(); // 4 5 3
        assertThat(array.values()).isEqualTo(new int[]{4, 5, 3});
        assertThat(array.size()).isEqualTo(3);
        assertThat(array.append(9)).isTrue(); // 4 5 3 9
        assertThat(array.values()).isEqualTo(new int[]{4, 5, 3, 9});
        assertThat(array.size()).isEqualTo(4);
        assertThat(array.insertAt(3, 10)).isTrue(); // 4 5 3 10 9
        assertThat(array.values()).isEqualTo(new int[]{4, 5, 3, 10, 9});
        assertThat(array.size()).isEqualTo(5);
        assertThat(array.isFull()).isTrue();
        assertThat(array.append(0)).isTrue();
        assertThat(array.values()).isEqualTo(new int[]{4, 5, 3, 10, 9, 0});
        assertThat(array.size()).isEqualTo(6);
        assertThat(array.isFull()).isFalse();
        assertThat(array.size()).isGreaterThan(5);
        assertThat(array.removeAt(0)).isEqualTo(4); // 5 3 10 9 0
        assertThat(array.values()).isEqualTo(new int[]{5, 3, 10, 9, 0});
        assertThat(array.size()).isEqualTo(5);
        assertThat(array.removeTail()).isEqualTo(0);
        try {
            array.removeAt(4);
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException ignored) {
            // ignored
        }
        assertThat(array.get(1)).isEqualTo(3);
        try {
            System.out.println("value at index 4 is " + array.get(4));
            fail("should throw NoSuchElementException");
        } catch (NoSuchElementException ignored) {
            // ignored
        }
        assertThat(array.removeTail()).isEqualTo(9);
        assertThat(array.size()).isEqualTo(3);
        assertThat(array.values()).isEqualTo(new int[]{5, 3, 10});
        IntStream intStream = array.parallelStream();
        assertThat(intStream).isNotNull();
        assertThat(intStream.isParallel()).isTrue();
        assertThat(array.set(1, 7)).isEqualTo(3);
        assertThat(array.values()).isEqualTo(new int[]{5, 7, 10});
        array.reverse();
        assertThat(array.values()).isEqualTo(new int[]{10, 7, 5});
        array.sort();
        assertThat(array.values()).isEqualTo(new int[]{5, 7, 10});
        array.clear();
        assertThat(array.size()).isEqualTo(0);
        assertThat(array.values()).isEqualTo(new int[]{});
    }
}
