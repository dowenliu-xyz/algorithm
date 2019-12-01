package xyz.dowenliu.study.algo._13_sort;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Scanner;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class PersonFileStorage implements Storage<Person> {
    @NotNull
    private final Path file;
    @Nullable
    private Writer writer;
    private boolean canAppend = true;
    private int appendCount = 0;

    public PersonFileStorage(@NotNull Path file) {
        this.file = Objects.requireNonNull(file);
    }

    @Override
    public void append(@NotNull Person data) throws IllegalStateException {
        if (!this.canAppend) {
            throw new IllegalStateException("Appending is not allowed now");
        }
        if (this.writer == null) {
            try {
                this.writer = Files.newBufferedWriter(file,
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            this.writer.write(this.personLine(data));
            this.appendCount++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String personLine(@NotNull Person person) {
        StringBuilder sb = new StringBuilder();
        long epochSecond = person.getBirthday()
                .toEpochSecond(ZoneOffset.ofHours(+8));
        sb.append(person.getId()).append(" ").append(epochSecond)
                .append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    public int appended() {
        return this.appendCount;
    }

    @Override
    public void closeForAppend() throws IllegalStateException {
        if (!this.canAppend) {
            throw new IllegalStateException();
        }
        try {
            if (this.writer != null) {
                this.writer.flush();
                this.writer.close();
            }
            this.canAppend = false;
            this.writer = null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull DataReader<Person> openForRead()
            throws IllegalStateException {
        if (this.writer != null) {
            throw new IllegalStateException("Appending!");
        }
        if (this.canAppend) {
            this.canAppend = false;
        }
        try {
            return new DataReader<>() {
                private final Scanner scanner =
                        new Scanner(PersonFileStorage.this.file,
                                StandardCharsets.UTF_8);

                @Nullable
                @Override
                public Person next() {
                    if (!scanner.hasNextLine()) {
                        return null;
                    }
                    String line = scanner.nextLine();
                    while (line.trim().isEmpty()) {
                        line = scanner.nextLine();
                    }
                    String[] parts = line.split("\\s+");
                    return new Person(
                            parts[0],
                            LocalDateTime.ofEpochSecond(
                                    Long.parseLong(parts[1]),
                                    0,
                                    ZoneOffset.ofHours(8)
                            )
                    );
                }

                @Override
                public void close() {
                    this.scanner.close();
                }
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
