package christmas.domain.model;

import java.time.LocalDate;
import java.util.List;

public class DecemberCalendar {

    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final List<Integer> STAR_DAY = List.of(3, 10, 17, 24, 25, 31);

    private final LocalDate date;


    private DecemberCalendar(int day) {
        this.date = LocalDate.of(2023, 12, day);
    }

    public static DecemberCalendar from(int day) {
        validateRange(day);
        return new DecemberCalendar(day);
    }

    public boolean hasStar() {
        return STAR_DAY.contains(date.getDayOfMonth());
    }

    private static void validateRange(int day) {
        if (day < FIRST_DAY || day > LAST_DAY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
