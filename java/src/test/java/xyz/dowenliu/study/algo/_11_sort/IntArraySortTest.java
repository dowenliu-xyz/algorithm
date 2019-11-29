package xyz.dowenliu.study.algo._11_sort;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
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
    @Ignore
    public void testBobble() {
        testTemplate(this.array, new BobbleIntArraySort());
    }

    @Test
    @Ignore
    public void testBetterBobble1() {
        testTemplate(this.array, new BetterBobbleIntArraySort1());
    }

    @Test
    @Ignore
    public void testBetterBobble2() {
        testTemplate(this.array, new BetterBobbleIntArraySort2());
    }

    @Test
    public void testInsertion() {
        testTemplate(this.array, new InsertionIntArraySort());
    }

    @Test
    public void testSelection() {
        testTemplate(this.array, new SelectionIntArraySort());
    }

    @Test
    public void getMerge() {
        testTemplate(this.array, new MergeIntArraySort());
    }
}
