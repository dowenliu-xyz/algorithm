package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Objects;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public abstract class Bucket<T> implements Storage<T>, AutoCloseable {
    @Nullable
    private Bucket<T> next;

    @Nullable
    public Bucket<T> getNext() {
        return next;
    }

    public void setNext(@Nullable Bucket<T> next) {
        this.next = next;
    }

    @NotNull
    private final T min;
    private boolean minInclusive;
    @NotNull
    private final T max;
    private boolean maxInclusive;
    @NotNull
    private final Comparator<T> comparator;

    public Bucket(@NotNull T min, boolean minInclusive,
                  @NotNull T max, boolean maxInclusive,
                  @NotNull Comparator<T> comparator) {
        this.min = Objects.requireNonNull(min);
        this.minInclusive = minInclusive;
        this.max = Objects.requireNonNull(max);
        this.maxInclusive = maxInclusive;
        this.comparator = Objects.requireNonNull(comparator);
    }

    boolean accept(@NotNull T data) {
        if (this.minInclusive) {
            if (this.comparator.compare(data, this.min) < 0) {
                return false;
            }
        } else {
            if (this.comparator.compare(data, this.min) <= 0) {
                return false;
            }
        }
        if (this.maxInclusive) {
            return this.comparator.compare(data, this.max) <= 0;
        } else {
            return this.comparator.compare(data, this.max) < 0;
        }
    }

    @NotNull
    public T getMin() {
        return min;
    }

    public boolean isMinInclusive() {
        return minInclusive;
    }

    @NotNull
    public T getMax() {
        return max;
    }

    public boolean isMaxInclusive() {
        return maxInclusive;
    }

    @NotNull
    public Comparator<T> getComparator() {
        return comparator;
    }
}
