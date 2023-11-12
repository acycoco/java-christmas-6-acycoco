package christmas.domain.model;

import java.time.LocalDate;

public class ChristmasDdayEvent implements Event {

    private static final LocalDate DECEMBER_DAY1 = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);

    private final OrderInfo orderInfo;

    public ChristmasDdayEvent(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }


    @Override
    public boolean canDiscount() {
        return !orderInfo.isBefore(DECEMBER_DAY1) && !orderInfo.isAfter(CHRISTMAS_DAY);
    }
}
