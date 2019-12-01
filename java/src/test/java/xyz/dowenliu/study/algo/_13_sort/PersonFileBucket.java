package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class PersonFileBucket extends Bucket<Person> {
    private final Path file;
    private final PersonFileStorage storage;

    public PersonFileBucket(@NotNull Person min, boolean minInclusive,
                            @NotNull Person max, boolean maxInclusive,
                            @NotNull Comparator<Person> comparator) {
        super(min, minInclusive, max, maxInclusive, comparator);
        try {
            this.file = Files.createTempFile("bucket", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.storage = new PersonFileStorage(this.file);
    }

    @Override
    public void close() throws Exception {
        try {
            this.storage.closeForAppend();
        } catch (Exception ignored) {
        }
        Files.deleteIfExists(this.file);
    }

    @Override
    public void append(@NotNull Person data) throws IllegalStateException {
        this.storage.append(data);
    }

    @Override
    public int appended() {
        return this.storage.appended();
    }

    @Override
    public void closeForAppend() throws IllegalStateException {
        this.storage.closeForAppend();
    }

    @Override
    public @NotNull DataReader<Person> openForRead()
            throws IllegalStateException {
        return this.storage.openForRead();
    }
}
