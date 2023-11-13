package christmas.domain.model;

public class WeekdayEvent implements Event{

    private static final Money DISCOUNT_PER_DESSERT = Money.from(2023);

    private final OrderInfo orderInfo;

    public WeekdayEvent(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public boolean canDiscount() {
        return orderInfo.getDate().isWeekday() && orderInfo.getOrder().countDessertOrders() > 0;
    }

    @Override
    public Money discountAmount() {
        return Money.from(orderInfo.getOrder().countDessertOrders()).multiply(DISCOUNT_PER_DESSERT);
    }
}
