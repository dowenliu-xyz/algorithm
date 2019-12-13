package xyz.dowenliu.study.algo._28_heap;

import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * <p>create at 2019/12/13</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntMaxHeapTest {
    @Test
    public void test() {
        IntMaxHeap heap = new IntMaxHeap(5);
        assertThat(heap.size()).isEqualTo(0);
        assertThat(heap.toArray()).isEqualTo(new int[0]);
        try {
            heap.getMax();
            fail("peek from an empty heap");
        } catch (NoSuchElementException ignored) {
        }
        try {
            heap.removeMax();
            fail("pop from an empty heap");
        } catch (NoSuchElementException ignored) {
        }

        assertThat(heap.add(3)).isTrue();
        assertThat(heap.add(2)).isTrue();
        assertThat(heap.add(4)).isTrue();
        assertThat(heap.add(1)).isTrue();
        assertThat(heap.add(5)).isTrue();
        assertThat(heap.add(6)).isFalse();
        assertThat(heap.size()).isEqualTo(5);
        assertThat(heap.getMax()).isEqualTo(5);
        System.out.println(Arrays.toString(heap.toArray()));
        assertThat(validateMaxHeap(heap.toArray())).isTrue();
        assertThat(heap.removeMax()).isEqualTo(5);
        assertThat(heap.size()).isEqualTo(4);
        System.out.println(Arrays.toString(heap.toArray()));
        assertThat(validateMaxHeap(heap.toArray())).isTrue();
    }

    private boolean validateMaxHeap(int[] data) {
        int i = 0;
        while (true) {
            int l = i * 2 + 1;
            int r = l * 2 + 2;
            if (l >= data.length) break;
            if (data[i] < data[l]) return false;
            if (r >= data.length) break;
            if (data[i] < data[r]) return false;
            i++;
        }
        return true;
    }
}
