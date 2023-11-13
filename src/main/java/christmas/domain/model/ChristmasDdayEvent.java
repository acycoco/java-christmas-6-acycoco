package christmas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ChristmasDdayEvent implements Event {

    private static final LocalDate DECEMBER_DAY1 = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);
    private static final BigDecimal BASE_DISCOUNT = BigDecimal.valueOf(1000);
    private static final BigDecimal DAILY_DISCOUNT = BigDecimal.valueOf(100);


    private final OrderInfo orderInfo;

    public ChristmasDdayEvent(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public boolean canDiscount() {
        return !orderInfo.isBefore(DECEMBER_DAY1) && !orderInfo.isAfter(CHRISTMAS_DAY);
    }

    @Override
    public BigDecimal discountAmount() {
        return BASE_DISCOUNT.add(DAILY_DISCOUNT.multiply(BigDecimal.valueOf(orderInfo.getDay() - DECEMBER_DAY1.getDayOfMonth())));
    }
}
