package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.Nullable;

/**
 * 数据读取器
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface DataReader<T> extends AutoCloseable {
    @Nullable
    T next();
}
