package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class BucketSort<T> implements OuterSort<T> {
    @FunctionalInterface
    public interface BucketGenerator<T> {
        @NotNull
        Bucket<T> apply(@NotNull T min, boolean minInclusive,
                        @NotNull T max, boolean maxInclusive,
                        @NotNull Comparator<T> comparator);
    }

    @FunctionalInterface
    public interface BucketDivider<T> {
        @NotNull
        Bucket<T> apply(@NotNull T min, @NotNull T max,
                        @NotNull BucketGenerator<T> generator,
                        int parts,
                        @NotNull Comparator<T> comparator);
    }

    @NotNull
    private final BucketGenerator<T> bucketGenerator;
    @NotNull
    private final BucketDivider<T> bucketDivider;
    private final int bucketSize;

    public BucketSort(@NotNull BucketGenerator<T> bucketGenerator,
                      @NotNull BucketDivider<T> bucketDivider, int bucketSize) {
        this.bucketGenerator = Objects.requireNonNull(bucketGenerator);
        this.bucketDivider = Objects.requireNonNull(bucketDivider);
        if (bucketSize <= 0) {
            throw new IllegalArgumentException("bucketSize <= 0");
        }
        this.bucketSize = bucketSize;
    }

    @Override
    public void sort(@NotNull Storage<T> source,
                     @NotNull Comparator<T> comparator,
                     @NotNull Storage<T> result) {
        // scan max, min and counting
        DataReader<T> reader = source.openForRead();
        T min = null;
        T max = null;
        int count = 0;
        try {
            while (true) {
                T data = reader.next();
                if (data == null) {
                    break;
                }
                if (min == null || comparator.compare(min, data) > 0) {
                    min = data;
                }
                if (max == null || comparator.compare(max, data) < 0) {
                    max = data;
                }
                count++;
            }
        } finally {
            try {
                reader.close();
            } catch (Exception ignored) {
            }
        }
        if (count == 0) {
            return;
        }
        // 分桶
        int parts = count / this.bucketSize;
        Bucket<T> firstBucket =
                this.bucketDivider.apply(min, max, this.bucketGenerator, parts,
                        comparator);
        reader = source.openForRead();
        try {
            while (true) {
                T data = reader.next();
                if (data == null) {
                    break;
                }
                Bucket<T> b = firstBucket;
                while (b != null) {
                    if (b.accept(data)) {
                        b.append(data);
                        break;
                    }
                    b = b.getNext();
                }
            }
        } finally {
            try {
                reader.close();
            } catch (Exception ignored) {
            }
        }
        Bucket<T> b = firstBucket;
        while (b != null) {
            b.closeForAppend();
            b = b.getNext();
        }
        // 各桶排序
        b = firstBucket;
        Bucket<T> p = null;
        while (b != null) {
            Bucket<T> br = this.bucketGenerator.apply(b.getMin(),
                    b.isMinInclusive(), b.getMax(), b.isMaxInclusive(),
                    b.getComparator());
            if (b.appended() <= this.bucketSize) {
                // 内存排序
                this.sortBucket(b, comparator, br);
            } else {
                // 还是太大，再进行桶排序
                this.sort(b, comparator, br);
            }
            Bucket<T> next = b.getNext();
            br.setNext(next);
            if (p != null) {
                p.setNext(br);
            } else {
                firstBucket = br;
            }
            try {
                b.close(); // 关闭无用的桶
            } catch (Exception ignored) {
            }
            p = br;
            b = next;
        }
        // 合并桶结果
        b = firstBucket;
        while (b != null) {
            DataReader<T> br = b.openForRead();
            while (true) {
                T next = br.next();
                if (next == null) {
                    break;
                }
                result.append(next);
            }
            b = b.getNext();
        }
        result.closeForAppend();
    }

    private void sortBucket(@NotNull Bucket<T> bucket,
                            @NotNull Comparator<T> comparator,
                            @NotNull Bucket<T> bucketResult) {
        //noinspection unchecked
        T[] data = (T[]) new Object[bucket.appended()];
        DataReader<T> reader = bucket.openForRead();
        int p = 0;
        while (true) {
            T t = reader.next();
            if (t == null) {
                break;
            }
            data[p++] = t;
            if (p == data.length) {
                break;
            }
        }
        Arrays.sort(data, comparator);
        for (T t : data) {
            bucketResult.append(t);
        }
        bucketResult.closeForAppend();
    }
}
