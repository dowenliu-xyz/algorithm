package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface OuterSort<T> {
    void sort(@NotNull Storage<T> source,
              @NotNull Comparator<T> comparator,
              @NotNull Storage<T> result);
}
