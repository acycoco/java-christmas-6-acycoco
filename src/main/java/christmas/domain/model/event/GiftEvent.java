package christmas.domain.model.event;

import christmas.domain.model.order.Menu;
import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;
import christmas.domain.model.order.OrderItem;

public class GiftEvent implements Event{

    private static final Money GIFT_EVENT_THRESHOLD = Money.from(120000);

    @Override
    public boolean canDiscount(OrderInfo orderInfo) {
        Money totalPrice = orderInfo.getOrder().calculateTotalPrice();
        return totalPrice.isGreaterThanOrEqualTo(GIFT_EVENT_THRESHOLD);
    }

    @Override
    public Money discountAmount(OrderInfo orderInfo) {
        return getGiftItem().calculatePrice();
    }

    public OrderItem getGiftItem() {
        return OrderItem.of(Menu.CHAMPAGNE.getName(), 1);
    }
}
