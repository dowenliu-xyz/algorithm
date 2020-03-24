package xyz.dowenliu.study.algo._40_dynamic_programing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2020/3/25</p>
 *
 * @author liufl
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class LeetCode198_HouseRobber_01 {
    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[]{
                        new int[]{1, 2, 3, 1},
                        4
                },
                new Object[]{
                        new int[]{2, 7, 9, 3, 1},
                        12
                },
                new Object[]{
                        new int[]{6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3},
                        39
                }
        );
    }

    private final int[] input;
    private final int expected;

    public LeetCode198_HouseRobber_01(int[] input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertThat(new Solution().rob(input)).isEqualTo(expected);
    }

    static class Solution {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Integer.max(nums[0], nums[1]);
            }
            // dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
            int[] max = new int[nums.length];
            max[0] = nums[0];
            max[1] = Integer.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                max[i] = Integer.max(max[i - 2] + nums[i], max[i - 1]);
            }
            return Integer.max(max[max.length - 1], max[max.length - 2]);
        }
    }
}
