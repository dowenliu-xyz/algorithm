package xyz.dowenliu.study.algo._09_queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * <p>create at 2019/11/29</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class IntLinkedQueueTest {
    @Test
    public void test() {
        IntLinkedQueue queue = new IntLinkedQueue();
        assertThat(queue.isEmpty()).isTrue();
        try {
            queue.dequeue();
            fail("Dequeue from an empty queue");
        } catch (NoSuchElementException ignored) {
        }
        try {
            queue.head();
            fail("Get head from an empty queue");
        } catch (NoSuchElementException ignored) {
        }
        System.out.println(queue.toString());
        assertThat(queue.toString())
                .isEqualTo("IntLinkedQueue[]");

        assertThat(queue.enqueue(0)).isTrue();
        assertThat(queue.enqueue(1)).isTrue();
        assertThat(queue.enqueue(2)).isTrue();
        System.out.println(queue.toString());
        assertThat(queue.toString())
                .isEqualTo("IntLinkedQueue[0, 1, 2]");
        assertThat(queue.enqueue(3)).isTrue();
        System.out.println(queue.toString());
        assertThat(queue.toString())
                .isEqualTo("IntLinkedQueue[0, 1, 2, 3]");
        assertThat(queue.dequeue()).isEqualTo(0);
        assertThat(queue.head()).isEqualTo(1);
        assertThat(queue.dequeue()).isEqualTo(1);
        assertThat(queue.enqueue(3)).isTrue();
        System.out.println(queue.toString());
        assertThat(queue.toString())
                .isEqualTo("IntLinkedQueue[2, 3, 3]");
    }
}
