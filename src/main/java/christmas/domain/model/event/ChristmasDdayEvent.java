package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;

import java.time.LocalDate;

public class ChristmasDdayEvent implements Event {

    private static final LocalDate DECEMBER_DAY1 = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);
    private static final Money BASE_DISCOUNT = Money.from(1000);
    private static final Money DAILY_DISCOUNT = Money.from(100);

    @Override
    public boolean canDiscount(OrderInfo orderInfo) {
        return !orderInfo.getDate().isBefore(DECEMBER_DAY1) && !orderInfo.getDate().isAfter(CHRISTMAS_DAY);
    }

    //TODO 디미터법칙 위반
    @Override
    public Money discountAmount(OrderInfo orderInfo) {
        return BASE_DISCOUNT.add(DAILY_DISCOUNT.multiply(Money.from(orderInfo.getDate().getDay() - DECEMBER_DAY1.getDayOfMonth())));
    }
}
