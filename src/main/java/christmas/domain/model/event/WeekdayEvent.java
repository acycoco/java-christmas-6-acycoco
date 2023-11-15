package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;

public class WeekdayEvent implements Event{

    private static final Money DISCOUNT_PER_DESSERT = Money.from(2023);

    @Override
    public boolean canDiscount(OrderInfo orderInfo) {
        return orderInfo.getDate().isWeekday() && orderInfo.getOrder().countDessertOrders() > 0;
    }

    @Override
    public Money discountAmount(OrderInfo orderInfo) {
        return Money.from(orderInfo.getOrder().countDessertOrders()).multiply(DISCOUNT_PER_DESSERT);
    }
}
