package christmas.domain.model;

public class WeekendEvent implements Event{

    private static final Money DISCOUNT_PER_MAIN = Money.from(2023);

    private final OrderInfo orderInfo;

    public WeekendEvent(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public boolean canDiscount() {
        return orderInfo.getDate().isWeekend() && orderInfo.getOrder().countMainOrders() > 0;
    }

    @Override
    public Money discountAmount() {
        return Money.from(orderInfo.getOrder().countMainOrders()).multiply(DISCOUNT_PER_MAIN);
    }
}
