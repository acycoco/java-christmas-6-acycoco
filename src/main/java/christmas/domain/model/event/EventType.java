package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;

public enum EventType {

    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", new ChristmasDdayEvent()),
    WEEKDAY_DISCOUNT("평일 할인", new WeekdayEvent()),
    WEEKEND_DISCOUNT("주말 할인", new WeekendEvent()),
    SPECIAL_DISCOUNT("특별 할인", new SpecialEvent()),
    GIFT_EVENT("증정 이벤트", new GiftEvent());

    private final String name;
    private final Event event;

    EventType(String name, Event event) {
        this.name = name;
        this.event = event;
    }

    public boolean canDiscount(OrderInfo orderInfo) {
        return event.canDiscount(orderInfo);
    }

    public Money discountAmount(OrderInfo orderInfo) {
        return event.discountAmount(orderInfo);
    }

    public String getName() {
        return name;
    }
}
