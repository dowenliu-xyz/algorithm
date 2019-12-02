package xyz.dowenliu.study.algo._15_binary_search;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SimpleIntBinarySearchTest {
    private int[] array;
    private int keyExists;
    private int keyNotExists;

    @Before
    public void prepareData() {
        //*/
        int length = 100_000;
        int bound = 10_000_000;
        /*/
        int length = 10;
        int bound = 100;
        //*/
        this.array = new int[length];
        Random random = new Random(System.currentTimeMillis());
        Set<Integer> added = new HashSet<>();
        for (int i = 0; i < this.array.length; i++) {
            int value;
            while (true) {
                value = random.nextInt(bound);
                if (added.contains(value)) continue;
                added.add(value);
                break;
            }
            this.array[i] = value;
        }
        Arrays.sort(this.array);
        this.keyExists = added.iterator().next();
        int i;
        do {
            i = random.nextInt(bound);
        } while (added.contains(i));
        this.keyNotExists = i;
    }

    @Test
    public void test() {
        IntBinarySearch binarySearch = new SimpleIntBinarySearch();
        assertThat(binarySearch.search(this.array, this.keyNotExists)).isEqualTo(-1);
        int position = binarySearch.search(this.array, this.keyExists);
        assertThat(position).isGreaterThan(-1);
        if (position > 0) {
            assertThat(this.array[position - 1]).isLessThan(this.array[position]);
        }
        if (position < this.array.length) {
            assertThat(this.array[position]).isLessThan(this.array[position + 1]);
        }
        assertThat(this.array[position]).isEqualTo(this.keyExists);
    }
}
