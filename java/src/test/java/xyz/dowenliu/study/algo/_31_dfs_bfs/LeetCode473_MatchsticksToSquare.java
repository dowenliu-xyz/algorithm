package xyz.dowenliu.study.algo._31_dfs_bfs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2020/3/25</p>
 *
 * @author liufl
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class LeetCode473_MatchsticksToSquare {
    @Parameterized.Parameters
    public static Iterable<Object[]> params() {
        return Arrays.asList(
                new Object[]{
                        new int[]{1, 1, 2, 2, 2},
                        true
                },
                new Object[]{
                        new int[]{3, 3, 3, 3, 4},
                        false
                }
        );
    }

    private final int[] nums;
    private final boolean expected;

    public LeetCode473_MatchsticksToSquare(int[] nums, boolean expected) {
        this.nums = nums;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertThat(new Solution().makesquare(nums)).isEqualTo(expected);
    }

static class Solution {

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        final int perimeter = Arrays.stream(nums).sum();
        if (perimeter % 4 != 0) return false;
        int squareWidth = perimeter / 4;
        Arrays.sort(nums);
        int[] sides = new int[4];
        return dfs(nums, sides, squareWidth, nums.length - 1);
    }

    private boolean dfs(int[] nums, int[] sides, int squareWidth, int index) {
        if (index == -1) {
            return sides[0] == sides[1] && sides[1] == sides[2] && sides[2] == sides[3];
        }
        for (int i = 0; i < 4; i++) {
            if (sides[i] + nums[index] > squareWidth) continue;
            sides[i] += nums[index];
            if (dfs(nums, sides, squareWidth, index - 1)) return true;
            sides[i] -= nums[index];
        }
        return false;
    }
}
}
