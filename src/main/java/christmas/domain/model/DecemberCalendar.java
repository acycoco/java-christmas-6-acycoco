package christmas.domain.model;

import java.time.LocalDate;
import java.util.List;

public class DecemberCalendar {

    private static final List<Integer> STAR_DAY = List.of(3, 10, 17, 24, 25, 31);

    private final LocalDate date;


    public DecemberCalendar(int day) {
        this.date = LocalDate.of(2023, 12, day);
    }

    public boolean hasStar() {
        return STAR_DAY.contains(date.getDayOfMonth());
    }

}
