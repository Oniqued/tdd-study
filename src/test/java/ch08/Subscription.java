package ch08;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Subscription {
    private String productId;
    private boolean finished;
    private Grade grade;
    private LocalDate date;

    public Subscription(LocalDate date, Grade grade) {
        this.grade = grade;
        this.date = date;
    }

    public boolean isFinished(LocalDate date) {
        return false;
    }
}
