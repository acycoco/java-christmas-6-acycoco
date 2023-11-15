package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Order;
import java.time.LocalDate;

public class ChristmasDdayEvent implements Event {

    private static final LocalDate DECEMBER_DAY1 = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);
    private static final Money BASE_DISCOUNT = Money.from(1000);
    private static final Money DAILY_DISCOUNT = Money.from(100);

    @Override
    public boolean canDiscount(Order order, VisitDate visitDate) {
        return !visitDate.isBefore(DECEMBER_DAY1) && !visitDate.isAfter(CHRISTMAS_DAY);
    }

    @Override
    public Money discountAmount(Order order, VisitDate visitDate) {
        return BASE_DISCOUNT.add(DAILY_DISCOUNT.multiply(Money.from(visitDate.getDay() - DECEMBER_DAY1.getDayOfMonth())));
    }
}
