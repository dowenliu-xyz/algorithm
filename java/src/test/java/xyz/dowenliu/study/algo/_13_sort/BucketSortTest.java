package xyz.dowenliu.study.algo._13_sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class BucketSortTest {
    private Path source;
    private Path result;

    @Before
    public void before() {
        this.source = Paths.get(System.getProperty("user.home"), ".temp", "person-age.txt");
        this.fillSource(this.source);
        this.result = Paths.get(System.getProperty("user.home"), ".temp", "person-age-sorted.txt");
    }

    private void fillSource(Path file) {
        PersonFileStorage storage = new PersonFileStorage(file);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            Person person = new Person(
                    String.valueOf(i + 1),
                    LocalDateTime.of(
                            1950 + random.nextInt(60),
                            1 + random.nextInt(12),
                            1 + random.nextInt(28),
                            random.nextInt(24),
                            random.nextInt(60),
                            random.nextInt(60)
                    )
            );
            storage.append(person);
        }
        storage.closeForAppend();
    }

    @Test
    public void test() {
        BucketSort<Person> sort = new BucketSort<>(
                PersonFileBucket::new,
                (min, max, generator, parts, comparator) -> {
                    LocalDate minDate = min.getBirthday().toLocalDate();
                    LocalDate maxDate = max.getBirthday().toLocalDate();
                    long days = Duration.between(minDate.atStartOfDay(), maxDate.plusDays(1).atStartOfDay())
                            .dividedBy(parts).toDays();
                    LocalDate date = maxDate;
                    Bucket<Person> bucket = null;
                    while (date.isAfter(minDate)) {
                        LocalDate nextDate = date.plusDays(1 - days);
                        Person bMin = new Person("0", nextDate.atStartOfDay());
                        Person bMax = new Person("0", date.atStartOfDay());
                        Bucket<Person> b = generator.apply(bMin, true, bMax, false, comparator);
                        b.setNext(bucket);
                        bucket = b;
                        date = nextDate;
                    }
                    assert bucket != null;
                    return bucket;
                },
                10
        );
        sort.sort(new PersonFileStorage(this.source), Comparator.comparing(Person::getBirthday), new PersonFileStorage(this.result));
        PersonFileStorage storage = new PersonFileStorage(this.result);
        storage.closeForAppend();
        DataReader<Person> reader = storage.openForRead();
        Person pre = reader.next();
        assertThat(pre).isNotNull();
        while (true) {
            Person person = reader.next();
            if (person != null) {
                assertThat(pre.getBirthday()).isBeforeOrEqualTo(person.getBirthday());
            } else {
                break;
            }
        }
    }

    @After
    public void clean() {
        try {
            Files.deleteIfExists(this.source);
        } catch (Exception ignored) {
        }
        try {
            Files.deleteIfExists(this.result);
        } catch (Exception ignored) {
        }
    }
}
