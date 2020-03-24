package xyz.dowenliu.study.algo._40_dynamic_programing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2020/3/24</p>
 *
 * @author liufl
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class LeetCode120_Triangle_01 {
    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[]{
                        Arrays.asList(
                                Arrays.asList(2),
                                Arrays.asList(3, 4),
                                Arrays.asList(6,5,7),
                                Arrays.asList(4,1,8,3)
                        ),
                        11
                },
                new Object[] {
                        Arrays.asList(Arrays.asList(2)),
                        2
                }
        );
    }

    private final List<List<Integer>> input;
    private final int expected;

    public LeetCode120_Triangle_01(List<List<Integer>> input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void testRecursion() {
        Solution solution = new Solution();
        assertThat(solution.minimumTotal(input)).isEqualTo(expected);
    }

    static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null) {
                return 0;
            }
            final List<Integer> bottom = triangle.get(triangle.size() - 1);
            int[] min = new int[bottom.size()];
            for (int i = 0; i < bottom.size(); i++) {
                min[i] = bottom.get(i);
            }
            for (int i = triangle.size() - 2; i >= 0; i--) {
                final List<Integer> level = triangle.get(i);
                for (int j = 0; j < level.size(); j++) {
                    min[j] = level.get(j) + Integer.min(min[j], min[j + 1]);
                }
            }
            return min[0];
        }
    }
}
