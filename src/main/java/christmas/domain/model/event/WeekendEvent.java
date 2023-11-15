package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;

public class WeekendEvent implements Event{

    private static final Money DISCOUNT_PER_MAIN = Money.from(2023);

    @Override
    public boolean canDiscount(OrderInfo orderInfo) {
        return orderInfo.getDate().isWeekend() && orderInfo.getOrder().countMainOrders() > 0;
    }

    @Override
    public Money discountAmount(OrderInfo orderInfo) {
        return Money.from(orderInfo.getOrder().countMainOrders()).multiply(DISCOUNT_PER_MAIN);
    }
}
