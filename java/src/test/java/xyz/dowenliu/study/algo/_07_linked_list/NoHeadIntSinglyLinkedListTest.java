package xyz.dowenliu.study.algo._07_linked_list;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * <p>create at 2019/11/26</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class NoHeadIntSinglyLinkedListTest {
    @Test
    public void test() {
        IntList list = new NoHeadIntSinglyLinkedList();
        // for empty list
        assertThat(list.size()).isEqualTo(0);
        assertThat(list.isEmpty()).isTrue();
        try {
            list.get(0);
            fail("get value at index 0 from an empty list");
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertThat(list.contains(1)).isFalse();
        try {
            list.set(0, 0);
            fail("set value at index 0 to 0 in an empty list");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.addBefore(1, 1);
            fail("add value before index 1 in an empty list");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.addAfter(0, 1);
            fail("add value after head in an empty list");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.remove(2);
            fail("remove value at index 2 from an empty list");
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertThat(list.removeBy(3)).isFalse();
        assertThat(list.indexOf(3)).isEqualTo(-1);
        assertThat(list.lastIndexOf(3)).isEqualTo(-1);
        assertThat(list.toArray()).isEqualTo(new int[0]);
        list.clear();

        list.addHead(0);
        // list having one value
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.get(0)).isEqualTo(0);
        try {
            list.get(1);
            fail("get value at index 1 from a list having one value");
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertThat(list.contains(0)).isTrue();
        assertThat(list.contains(1)).isFalse();
        assertThat(list.set(0, 1)).isEqualTo(0);
        try {
            list.set(2, 5);
            fail("set value at index 2 with value 5 in a list having one value");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.addBefore(9, 2);
            fail("add value before index 9 in a list having one value");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.addAfter(1, 1);
            fail("add value after index 1 in a list having one value");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            list.remove(3);
            fail("remove value at index 3 from a list having one value");
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertThat(list.remove(0)).isEqualTo(1);
        list.addHead(0);
        assertThat(list.removeBy(1)).isFalse();
        assertThat(list.removeBy(0)).isTrue();
        assertThat(list.size()).isEqualTo(0);
        list.addHead(0);
        assertThat(list.indexOf(1)).isEqualTo(-1);
        assertThat(list.indexOf(0)).isEqualTo(0);
        assertThat(list.lastIndexOf(1)).isEqualTo(-1);
        assertThat(list.lastIndexOf(0)).isEqualTo(0);
        assertThat(list.toArray()).isEqualTo(new int[]{0});
        list.addAfter(0, 1);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 1});
        list.clear();
        assertThat(list.isEmpty());

        // list having more values
        list.addBefore(0, 0);
        list.addBefore(1, 1);
        list.addBefore(2, 2);
        list.addBefore(3, 3);
        list.addBefore(4, 4);
        assertThat(list.size()).isEqualTo(5);
        assertThat(list.isEmpty()).isFalse();
        try {
            list.get(5);
            fail("get value at index 5 from a list having 5 values");
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertThat(list.get(2)).isEqualTo(2);
        assertThat(list.contains(2)).isTrue();
        assertThat(list.contains(5)).isFalse();
        try {
            list.set(5, 1);
            fail("set value at index 5 in a list having 5 values");
        } catch (IndexOutOfBoundsException ignored) {
        }
        assertThat(list.set(3, 1)).isEqualTo(3);
        try {
            list.addBefore(6, 0);
            fail("add value before index 6 to a list having 5 values");
        } catch (IndexOutOfBoundsException ignored) {
        }
        list.addHead(0);
        assertThat(list.size()).isEqualTo(6);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 0, 1, 2, 1, 4});
        try {
            list.addAfter(6, 6);
            fail("add value after index 6 to a list having 6 values");
        } catch (IndexOutOfBoundsException ignored) {
        }
        list.addAfter(5, 5);
        assertThat(list.size()).isEqualTo(7);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 0, 1, 2, 1, 4, 5});
        list.addTail(0);
        assertThat(list.size()).isEqualTo(8);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 0, 1, 2, 1, 4, 5, 0});
        try {
            list.remove(8);
            fail("remove value at index 8 from a list having 8 values");
        } catch (IndexOutOfBoundsException ignored) {
        }
        list.remove(1);
        assertThat(list.size()).isEqualTo(7);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 1, 2, 1, 4, 5, 0});
        assertThat(list.removeBy(6)).isFalse();
        assertThat(list.size()).isEqualTo(7);
        assertThat(list.removeBy(5)).isTrue();
        assertThat(list.size()).isEqualTo(6);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 1, 2, 1, 4, 0});
        assertThat(list.removeBy(1)).isTrue();
        assertThat(list.size()).isEqualTo(5);
        assertThat(list.toArray()).isEqualTo(new int[]{0, 2, 1, 4, 0});
        assertThat(list.indexOf(1)).isEqualTo(2);
        assertThat(list.lastIndexOf(0)).isEqualTo(4);
        list.clear();
        assertThat(list.isEmpty());
    }
}
