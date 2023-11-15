package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.VisitDate;

public class WeekdayEvent implements Event{

    private static final Money DISCOUNT_PER_DESSERT = Money.from(2023);

    @Override
    public boolean canDiscount(Order order, VisitDate visitDate) {
        return visitDate.isWeekday() && order.countDessertOrders() > 0;
    }

    @Override
    public Money discountAmount(Order order, VisitDate visitDate) {
        return Money.from(order.countDessertOrders()).multiply(DISCOUNT_PER_DESSERT);
    }
}
