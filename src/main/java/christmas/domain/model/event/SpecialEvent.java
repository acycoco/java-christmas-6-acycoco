package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;

public class SpecialEvent implements Event{

    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public boolean canDiscount(OrderInfo orderInfo) {
        return orderInfo.getDate().hasStar();
    }

    @Override
    public Money discountAmount(OrderInfo orderInfo) {
        return Money.from(SPECIAL_DISCOUNT);
    }
}
