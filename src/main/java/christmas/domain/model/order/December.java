package christmas.domain.model.order;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class December {

    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final List<Integer> STAR_DAY = List.of(3, 10, 17, 24, 25, 31);

    private final LocalDate date;

    private December(int day) {
        this.date = LocalDate.of(2023, 12, day);
    }

    public static December from(int day) {
        validateRange(day);
        return new December(day);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean hasStar() {
        return STAR_DAY.contains(date.getDayOfMonth());
    }

    public boolean isBefore(LocalDate specialDate) {
        return date.isBefore(specialDate);
    }

    public boolean isAfter(LocalDate specialDate) {
        return date.isAfter(specialDate);
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    private static void validateRange(int day) {
        if (day < FIRST_DAY || day > LAST_DAY) {
            throw new IllegalArgumentException();
        }
    }
}
