package xyz.dowenliu.study.algo._20_hashtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class LRUCacheTest {
    @Test
    public void test() {
        LRUCache<String, String> cache = new LRUCache<>(2);
        assertThat(cache.get("a")).isNull();
        cache.remove("a");

        cache.add("a", "A");
        cache.add("b", "B");
        assertThat(cache.get("a")).isEqualTo("A");

        cache.add("a", "a");
        assertThat(cache.get("a")).isEqualTo("a");
        assertThat(cache.get("b")).isEqualTo("B");

        cache.add("c", "C");
        assertThat(cache.get("a")).isNull();

        cache.remove("b");
        assertThat(cache.get("b")).isNull();
        assertThat(cache.get("c")).isNotNull();
    }
}
