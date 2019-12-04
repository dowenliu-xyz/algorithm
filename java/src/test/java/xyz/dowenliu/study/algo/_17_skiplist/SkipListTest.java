package xyz.dowenliu.study.algo._17_skiplist;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SkipListTest {
    @Test
    public void test() {
        SkipList<Integer> list = new SkipList<>(4, Integer::compareTo);
        assertThat(list.size()).isEqualTo(0);
        assertThat(list.toString()).isEqualTo("[]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(1);
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.toString()).isEqualTo("[1]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(2);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.toString()).isEqualTo("[1, 2]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(6);
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.toString()).isEqualTo("[1, 2, 6]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(7);
        assertThat(list.size()).isEqualTo(4);
        assertThat(list.toString()).isEqualTo("[1, 2, 6, 7]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(8);
        assertThat(list.size()).isEqualTo(5);
        assertThat(list.toString()).isEqualTo("[1, 2, 6, 7, 8]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(3);
        assertThat(list.size()).isEqualTo(6);
        assertThat(list.toString()).isEqualTo("[1, 2, 3, 6, 7, 8]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isFalse();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(4);
        assertThat(list.size()).isEqualTo(7);
        assertThat(list.toString()).isEqualTo("[1, 2, 3, 4, 6, 7, 8]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isTrue();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        list.add(5);
        assertThat(list.size()).isEqualTo(8);
        assertThat(list.toString()).isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isTrue();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        assertThat(list.erase(9)).isFalse();
        assertThat(list.size()).isEqualTo(8);
        assertThat(list.toString()).isEqualTo("[1, 2, 3, 4, 5, 6, 7, 8]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isTrue();
        System.out.println(list.toGraphString(2));
        System.out.println("#".repeat(20));
        assertThat(list.erase(5)).isTrue();
        assertThat(list.size()).isEqualTo(7);
        assertThat(list.toString()).isEqualTo("[1, 2, 3, 4, 6, 7, 8]");
        assertThat(list.contains(9)).isFalse();
        assertThat(list.contains(4)).isTrue();
        assertThat(list.contains(5)).isFalse();
        System.out.println(list.toGraphString(2));
    }
}
