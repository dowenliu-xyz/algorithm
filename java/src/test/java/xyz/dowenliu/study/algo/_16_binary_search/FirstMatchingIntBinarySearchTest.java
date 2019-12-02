package xyz.dowenliu.study.algo._16_binary_search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import xyz.dowenliu.study.algo._15_binary_search.IntBinarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class FirstMatchingIntBinarySearchTest {
    private int[] array;
    private int keyExists;
    private int keyNotExists;

    @Before
    public void prepareData() {
        //*/
        int length = 100_000;
        int bound = 10_000;
        /*/
        int length = 10;
        int bound = 5;
        //*/
        this.array = new int[length];
        Random random = new Random(System.currentTimeMillis());
        Set<Integer> added = new HashSet<>();
        for (int i = 0; i < this.array.length; i++) {
            int value = random.nextInt(bound);
            if (!added.add(value)) {
                this.keyExists = value;
            }
            this.array[i] = value;
        }
        Arrays.sort(this.array);
        this.keyNotExists = -1;
    }

    @Rule
    public Timeout timeout = new Timeout(5, TimeUnit.SECONDS);

    @Test
    public void test() {
        IntBinarySearch binarySearch = new FirstMatchingIntBinarySearch();
        assertThat(binarySearch.search(this.array, this.keyNotExists)).isEqualTo(-1);
        int position = binarySearch.search(this.array, this.keyExists);
        assertThat(position).isGreaterThan(-1);
        if (position > 0) {
            assertThat(this.array[position - 1]).isLessThan(this.array[position]);
        } else {
            System.out.println("The key first appears at index " + position);
        }
        if (position < this.array.length) {
            assertThat(this.array[position]).isEqualTo(this.array[position + 1]);
        }
        assertThat(this.array[position]).isEqualTo(this.keyExists);
    }
}
