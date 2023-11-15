package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.December;
import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Order;

public class ChristmasDdayEvent implements Event {

    private static final Money BASE_DISCOUNT = Money.from(1000);
    private static final Money DAILY_DISCOUNT = Money.from(100);

    @Override
    public boolean canDiscount(Order order, VisitDate visitDate) {
        return visitDate.isAfterOrEqual(December.FIRST_DAY.getDay()) && visitDate.isBeforeOrEqual(December.CHRISTMAS_DAY.getDay());
    }

    @Override
    public Money discountAmount(Order order, VisitDate visitDate) {
        return BASE_DISCOUNT.add(DAILY_DISCOUNT.multiply(Money.from(visitDate.getDay() - December.FIRST_DAY.getDay())));
    }
}
