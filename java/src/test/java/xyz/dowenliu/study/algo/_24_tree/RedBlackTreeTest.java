package xyz.dowenliu.study.algo._24_tree;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/8</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class RedBlackTreeTest {
    @Test
    public void test() {
        // 2, 5, 9, 3, 3, 0, 1, 6
        RedBlackTree<Integer> tree = new RedBlackTree<>(Integer::compareTo);
        assertThat(tree.size()).isEqualTo(0);
        assertThat(tree.contains(2)).isFalse();
        assertThat(tree.add(null)).isFalse();
        assertThat(tree.remove(5)).isFalse();
        assertThat(tree.getMax()).isNull();
        assertThat(tree.getMin()).isNull();

        for (int i : new int[]{2, 5, 9, 3, 3, 0, 1, 6}) {
            assertThat(tree.add(i)).isTrue();
            assertThat(tree.size()).isGreaterThan(0);
            assertThat(tree.contains(i));
            assertThat(tree.add(null)).isFalse();
            assertThat(tree.remove(null)).isFalse();
            assertThat(tree.contains(100)).isFalse();
            System.out.println(tree.toString());
        }

        String toString = tree.toString();
        assertThat(toString).isEqualTo("[0, 1, 2, 3, 3, 5, 6, 9]");
        assertThat(new RedBlackTree<>(Integer::compareTo, 2, 5, 9, 3, 3, 0, 1, 6).toString()).isEqualTo(toString);

        assertThat(tree.getMax()).isEqualTo(9);
        assertThat(tree.getMin()).isEqualTo(0);

        tree.walkPreOrder((e, i) -> System.out.print(e.toString() + ", "));
        System.out.println();
        tree.walkPostOrder((e, i) -> System.out.print(e.toString() + ", "));
        System.out.println();
        tree.walkInOrder((e, i) -> System.out.print(e.toString() + ", "));
        System.out.println();
        tree.walkLevelOrder((e, i) -> System.out.print(e.toString() + ", "));
        System.out.println();

        assertThat(tree.remove(3)).isTrue();
        assertThat(tree.toString()).isEqualTo("[0, 1, 2, 3, 5, 6, 9]");
        assertThat(tree.remove(9)).isTrue();
        assertThat(tree.toString()).isEqualTo("[0, 1, 2, 3, 5, 6]");
        assertThat(tree.remove(1)).isTrue();
        assertThat(tree.toString()).isEqualTo("[0, 2, 3, 5, 6]");
        assertThat(tree.remove(2)).isTrue();
        assertThat(tree.toString()).isEqualTo("[0, 3, 5, 6]");
    }
}
