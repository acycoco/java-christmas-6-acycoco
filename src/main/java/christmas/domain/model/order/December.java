package christmas.domain.model.order;

import java.util.Arrays;
import java.util.List;

public enum December {

    FIRST_DAY(1),
    LAST_DAY(31),
    CHRISTMAS_DAY(25),
    STAR_DAY(3, 10, 17, 24, 25, 31);

    private final List<Integer> days;

    December(Integer... days) {
        this.days = Arrays.asList(days);
    }

    public int getDay() {
        return days.get(0);
    }

    public boolean contains(int day) {
        return days.contains(day);
    }
}
