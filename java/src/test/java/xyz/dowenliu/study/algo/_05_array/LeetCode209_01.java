package xyz.dowenliu.study.algo._05_array;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2020/3/23</p>
 *
 * @author liufl
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class LeetCode209_01 {
    @Parameterized.Parameters
    public static Collection<TestParam> params() {
        return Arrays.asList(
                new TestParam(new Input(7, new int[]{2, 3, 1, 2, 4, 3}), 2),
                new TestParam(new Input(15, new int[]{1, 2, 3, 4, 5}), 5)
        );
    }

    private Input input;
    private int expected;

    public LeetCode209_01(TestParam param) {
        this.input = param.input;
        this.expected = param.expected;
    }

    @Test(timeout = 1000)
    public void test() {
        final Solution solution = new Solution();
        assertThat(solution.minSubArrayLen(input.s, input.nums)).isEqualTo(expected);
    }

    private static class Input {
        int s;
        int[] nums;

        public Input(int s, int[] nums) {
            this.s = s;
            this.nums = nums;
        }
    }

    private static class TestParam {
        Input input;
        int expected;

        public TestParam(Input input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // 双指针法，前后指针维护一个动态窗口，窗口中数值之和保持 >= s
        // 滑动窗口，比较满足条件窗口宽度，最小宽度即所求解

        if (nums == null || nums.length == 0) return 0;

        // 窗口前后指针
        int left = 0, right = left;
        // 窗口数值之和
        int sum = 0;
        // 窗口最小宽度记录
        int minWidth = Integer.MAX_VALUE;
        // 逐渐向右扩展窗口
        while (right < nums.length) {
            sum += nums[right++];
            // 当窗口加和 >= s 时，从左收缩窗口
            while (sum >= s) {
                // 更新最小宽度
                minWidth = Integer.min(right - left, minWidth);
                sum -= nums[left++];
            }
        }
        // 最小宽度应该小于数组长度。否则没有找到，返回 0
        return minWidth <= nums.length ? minWidth : 0;
    }
}
