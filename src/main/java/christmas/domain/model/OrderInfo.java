package christmas.domain.model;

import java.time.LocalDate;

public class OrderInfo {

    private final LocalDate date;

    public OrderInfo(LocalDate date) {
        this.date = date;
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
}
