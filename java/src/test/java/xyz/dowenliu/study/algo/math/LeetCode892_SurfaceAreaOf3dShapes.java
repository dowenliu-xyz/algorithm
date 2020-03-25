package xyz.dowenliu.study.algo.math;

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
public class LeetCode892_SurfaceAreaOf3dShapes {
    @Parameterized.Parameters
    public static Iterable<Object[]> params() {
        return Arrays.asList(
                new Object[]{
                        new int[][]{
                                {2},
                        },
                        10
                },
                new Object[]{
                        new int[][]{
                                {1, 2},
                                {3, 4},
                        },
                        34
                },
                new Object[]{
                        new int[][]{
                                {1, 0},
                                {0, 2},
                        },
                        16
                },
                new Object[]{
                        new int[][]{
                                {1, 1, 1},
                                {1, 0, 1},
                                {1, 1, 1},
                        },
                        32
                },
                new Object[]{
                        new int[][]{
                                {2, 2, 2},
                                {2, 1, 2},
                                {2, 2, 2},
                        },
                        46
                }
        );
    }

    private final int[][] grid;
    private final int expected;

    public LeetCode892_SurfaceAreaOf3dShapes(int[][] grid, int expected) {
        this.grid = grid;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertThat(new Solution().surfaceArea(grid)).isEqualTo(expected);
    }

    static class Solution {
        public int surfaceArea(int[][] grid) {
        if (grid == null) return 0;
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) continue;
                // 上下表面
                area += 2;
                // 后面
                if (i == 0) {
                    // case1: 最边上
                    area += grid[i][j];
                } else {
                    // case2: 不是最边上，计算没被挡住的面
                    area += Integer.max(0, grid[i][j] - grid[i - 1][j]);
                }
                // 左面
                if (j == 0) {
                    // case1: 最边上
                    area += grid[i][j];
                } else {
                    // case2：不是最边上，计算没有被挡住的面
                    area += Integer.max(0, grid[i][j] - grid[i][j - 1]);
                }
                // 前面
                if (i == grid.length - 1) {
                    // case1: 最边上
                    area += grid[i][j];
                } else {
                    // case2：不是最边上，计算没有被挡住的面
                    area += Integer.max(0, grid[i][j] - grid[i + 1][j]);
                }
                // 右面
                if (j == grid[i].length - 1) {
                    // case1: 最边上
                    area += grid[i][j];
                } else {
                    // case2：不是最边上，计算没有被挡住的面
                    area += Integer.max(0, grid[i][j] - grid[i][j + 1]);
                }
            }
        }
        return area;
        }
    }
}
