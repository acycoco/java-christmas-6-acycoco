package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.VisitDate;

public class WeekendEvent implements Event{

    private static final Money DISCOUNT_PER_MAIN = Money.from(2023);

    @Override
    public boolean canDiscount(Order order, VisitDate visitDate) {
        return visitDate.isWeekend() && order.countMainOrders() > 0;
    }

    @Override
    public Money discountAmount(Order order, VisitDate visitDate) {
        return Money.from(order.countMainOrders()).multiply(DISCOUNT_PER_MAIN);
    }
}
