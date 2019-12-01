package xyz.dowenliu.study.algo._13_sort;

import java.time.LocalDateTime;

/**
 * <p>create at 2019/12/1</p>
 *
 * @author liufl
 * @since version 1.0
 */
public class Person {
    private String id;
    private LocalDateTime birthday;

    public Person() {
    }

    public Person(String id, LocalDateTime birthday) {
        this.id = id;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}
