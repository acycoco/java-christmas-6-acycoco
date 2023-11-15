package christmas.domain.model.order;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDate {

    private final LocalDate date;

    private VisitDate(int day) {
        this.date = LocalDate.of(2023, 12, day);
    }

    public static VisitDate from(int day) {
        validateRange(day);
        return new VisitDate(day);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean hasStar() {
        return December.STAR_DAY.contains(date.getDayOfMonth());
    }

    public boolean isBeforeOrEqual(int day) {
        return date.getDayOfMonth() <= day;
    }

    public boolean isAfterOrEqual(int day) {
        return date.getDayOfMonth() >= day;
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    private static void validateRange(int day) {
        if (day < December.FIRST_DAY.getDay() || day > December.LAST_DAY.getDay()) {
            throw new IllegalArgumentException();
        }
    }
}
