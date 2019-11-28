package xyz.dowenliu.study.algo._08_stack;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * <p>create at 2019/11/28</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class LinkedStackTest {
    @Test
    public void test() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        assertThat(stack.isEmpty()).isTrue();
        System.out.println(stack);
        try {
            stack.pop();
            fail("pop from an empty stack");
        } catch (NoSuchElementException ignored) {
        }
        try {
            stack.peek();
            fail("peek in an empty stack");
        } catch (NoSuchElementException ignored) {
        }
        for (int i = 0; i < 8; i++) {
            assertThat(stack.push(i + 1)).isTrue();
            assertThat(stack.isEmpty()).isFalse();
            assertThat(stack.peek()).isEqualTo(i + 1);
        }
        System.out.println(stack);
        assertThat(stack.push(100)).isTrue();
        System.out.println(stack);
        assertThat(stack.pop()).isEqualTo(100);
        System.out.println(stack);
        for (int i = 8; i > 0; i--) {
            assertThat(stack.pop()).isEqualTo(i);
        }
        assertThat(stack.isEmpty());
        System.out.println(stack);
    }
}
