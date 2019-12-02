package xyz.dowenliu.study.algo._16_binary_search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import xyz.dowenliu.study.algo._15_binary_search.IntBinarySearch;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/2</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class FirstMatchingOrLargerThanIntBinarySearchTest {
    private int[] array;
    private int keyExists;
    private int halfBound;
    private int lessThanAll;
    private int greaterThanAll;

    @Before
    public void prepareData() {
        //*/
        int length = 100_000;
        int bound = 10_000;
        /*/
        int length = 10;
        int bound = 5;
        //*/
        this.halfBound = bound / 2;
        this.greaterThanAll = bound + 1;
        this.lessThanAll = -1;
        this.array = new int[length];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.array.length; i++) {
            int value = random.nextInt(bound);
            this.array[i] = value == this.halfBound ? value - 1 : value;
        }
        this.keyExists = this.array[0];
        Arrays.sort(this.array);
    }

    @Rule
    public Timeout timeout = new Timeout(5, TimeUnit.SECONDS);

    @Test
    public void test() {
        IntBinarySearch binarySearch = new FirstMatchingOrLargerThanIntBinarySearch();
        assertThat(binarySearch.search(this.array, this.greaterThanAll)).isEqualTo(-1);
        assertThat(binarySearch.search(this.array, this.lessThanAll)).isEqualTo(0);
        int position = binarySearch.search(this.array, this.keyExists);
        assertThat(position).isGreaterThan(-1);
        if (position > 0) {
            assertThat(this.array[position - 1]).isLessThan(this.array[position]);
        } else {
            System.out.println("The key first appears at index " + position);
        }
        if (position < this.array.length) {
            assertThat(this.array[position]).isLessThanOrEqualTo(this.array[position + 1]);
        }
        assertThat(this.array[position]).isEqualTo(this.keyExists);

        position = binarySearch.search(this.array, this.halfBound);
        assertThat(position).isGreaterThan(-1);
        assertThat(this.array[position]).isGreaterThan(this.halfBound);
        assertThat(this.array[position - 1]).isLessThan(this.halfBound);
    }
}
