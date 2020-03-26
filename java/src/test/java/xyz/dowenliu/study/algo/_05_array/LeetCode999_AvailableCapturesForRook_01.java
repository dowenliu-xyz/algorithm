package xyz.dowenliu.study.algo._05_array;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2020/3/26</p>
 *
 * @author liufl
 * @since 1.0
 */
@RunWith(Parameterized.class)
public class LeetCode999_AvailableCapturesForRook_01 {
    @Parameterized.Parameters
    public static Iterable<Object[]> params() {
        return Arrays.asList(
                new Object[]{
                        new char[][]{
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'}
                        },
                        3
                },
                new Object[]{
                        new char[][]{
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                                {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
                                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'}
                        },
                        0
                },
                new Object[]{
                        new char[][]{
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                                {'p', 'p', '.', 'R', '.', 'p', 'B', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'},
                                {'.', '.', '.', 'B', '.', '.', '.', '.'},
                                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                                {'.', '.', '.', '.', '.', '.', '.', '.'}
                        },
                        3
                }
        );
    }

    private final char[][] board;
    private final int expected;

    public LeetCode999_AvailableCapturesForRook_01(char[][] board, int expected) {
        this.board = board;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertThat(new Solution().numRookCaptures(board)).isEqualTo(expected);
    }

    static class Solution {
        public int numRookCaptures(char[][] board) {
            if (board == null) return 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 'R') {
                        // 找到唯一的白车
                        int num = 0; // 可用计数
                        // 向上找
                        for (int row = i - 1; row >= 0; row--) {
                            if (board[row][j] == 'p') {
                                // 第一个黑卒
                                num++;
                                break;
                            }
                            if (board[row][j] == 'B') break; // 白象
                        }
                        // 向下找
                        for (int row = i + 1; row < 8; row++) {
                            if (board[row][j] == 'p') {
                                // 第一个黑卒
                                num++;
                                break;
                            }
                            if (board[row][j] == 'B') break; // 白象
                        }
                        // 向左找
                        for (int col = j - 1; col >= 0; col--) {
                            if (board[i][col] == 'p') {
                                // 第一个黑卒
                                num++;
                                break;
                            }
                            if (board[i][col] == 'B') break; // 白象
                        }
                        // 向右找
                        for (int col = j + 1; col < 8; col++) {
                            if (board[i][col] == 'p') {
                                // 第一个黑卒
                                num++;
                                break;
                            }
                            if (board[i][col] == 'B') break; // 白象
                        }

                        return num;
                    }
                }
            }
            return 0;
        }
    }
}
