package xyz.dowenliu.study.algo._40_dynamic_programing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2020/3/24</p>
 *
 * @author liufl
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class ClimbingStairs_01 {
    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[]{2, 2},
                new Object[]{3, 3},
                new Object[]{45, 1836311903}
        );
    }

    private final int input;
    private final int expected;

    public ClimbingStairs_01(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test(timeout = 1000)
    public void testRecursion() {
        final Solution solution = new Recursion();
        assertThat(solution.climbStairs(input)).isEqualTo(expected);
    }

    @Test
    public void testRecursionWithMemory() {
        final Solution solution = new RecursionWithMemory();
        assertThat(solution.climbStairs(input)).isEqualTo(expected);
    }

    @Test
    public void testDynamicProgramming() {
        final Solution solution = new DynamicProgramming();
        assertThat(solution.climbStairs(input)).isEqualTo(expected);
    }

    @Test
    public void testDynamicProgrammingLessMemory() {
        final Solution solution = new DynamicProgrammingLessMemory();
        assertThat(solution.climbStairs(input)).isEqualTo(expected);
    }

    interface Solution {
        int climbStairs(int n);
    }

    static class Recursion implements Solution {
        @Override
        public int climbStairs(int n) {
            return n <= 2 ? n : climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    static class RecursionWithMemory implements Solution {
        private int[] memory;
        @Override
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            if (this.memory == null) {
                this.memory = new int[n];
                memory[0] = 1;
                memory[1] = 2;
            }
            if (memory[n - 1] == 0) {
                memory[n - 1] = climbStairs(n - 1) + climbStairs(n - 2);
            }
            return memory[n - 1];
        }
    }

    static class DynamicProgramming implements Solution {
        @Override
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] mem = new int[n];
            mem[0] = 1;
            mem[1] = 2;
            for (int i = 2; i < n; i++) {
                mem[i] = mem[i - 1] + mem[i - 2];
            }
            return mem[n - 1];
        }
    }

    static class DynamicProgrammingLessMemory implements Solution {
        @Override
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int steps = 2, lastSteps = 1;
            int temp;
            for (int i = 2; i < n; i++) {
                temp = steps;
                steps += lastSteps;
                lastSteps = temp;
            }
            return steps;
        }
    }
}

