package xyz.dowenliu.study.algo._18_hashtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/4</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class SimpleLRUCacheTest {
    @Test
    public void test() {
        SimpleLRUCache<String, String> cache = new SimpleLRUCache<>(2);
        assertThat(cache.get("abc")).isNull();

        cache.cache("abc", "ABC");
        assertThat(cache.get("abc")).isNotNull();
        cache.cache("def", "DEF");
        cache.cache("xyz", "XYZ");
        assertThat(cache.get("abc")).isNull();
    }
}
