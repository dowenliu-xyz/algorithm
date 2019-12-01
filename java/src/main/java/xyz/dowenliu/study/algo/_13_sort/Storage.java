package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public interface Storage<T> {
    void append(@NotNull T data) throws IllegalStateException;

    int appended();

    void closeForAppend() throws IllegalStateException;

    @NotNull
    DataReader<T> openForRead() throws IllegalStateException;
}
