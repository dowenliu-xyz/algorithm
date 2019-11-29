package xyz.dowenliu.study.algo._11_sort;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntArraySortTest {
    private int[] array;

    @Before
    public void before() {
        this.array = new int[100_000];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = random.nextInt(1_000_000);
        }
    }

    private static void testTemplate(int[] array, IntArraySort sort) {
        Instant start = Instant.now();
        sort.sort(array);
        System.out.println(sort.getClass().getSimpleName() + " casts " + Duration.between(start, Instant.now()));
        for (int i = 0; i < array.length - 1; i++) {
            assertThat(array[i]).isLessThanOrEqualTo(array[i + 1]);
        }
    }

    @Test
    public void testBobble() {
        int[] array = new int[this.array.length];
        System.arraycopy(this.array, 0, array, 0, this.array.length);
        testTemplate(array, new BobbleIntArraySort());
    }
}
