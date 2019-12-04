package xyz.dowenliu.study.algo._18_hashtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class HashTableTest {
    @Test
    public void test() {
        HashTable<String, String> upperCase = new HashTable<>();
        assertThat(upperCase.size()).isEqualTo(0);
        assertThat(upperCase.exist("c")).isFalse();
        assertThat(upperCase.get("c")).isNull();
        assertThat(upperCase.remove("c")).isNull();

        assertThat(upperCase.put("a", "A")).isNull();
        assertThat(upperCase.put("b", "B")).isNull();
        assertThat(upperCase.put("c", "c")).isNull();
        assertThat(upperCase.put("d", "D")).isNull();
        assertThat(upperCase.size()).isEqualTo(4);
        assertThat(upperCase.exist("c")).isTrue();
        assertThat(upperCase.get("c")).isEqualTo("c");

        assertThat(upperCase.put("c", "C")).isEqualTo("c");
        assertThat(upperCase.size()).isEqualTo(4);

        assertThat(upperCase.remove("e")).isNull();
        assertThat(upperCase.size()).isEqualTo(4);
        assertThat(upperCase.remove("d")).isEqualTo("D");
        assertThat(upperCase.size()).isEqualTo(3);
        assertThat(upperCase.exist("d")).isFalse();
        assertThat(upperCase.get("d")).isNull();
        assertThat(upperCase.remove("d")).isNull();
    }
}
