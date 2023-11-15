package christmas.domain.model.event;

import christmas.domain.model.order.Menu;
import christmas.domain.model.Money;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.OrderItem;
import christmas.domain.model.order.VisitDate;

public class GiftEvent implements Event{

    private static final Money GIFT_EVENT_THRESHOLD = Money.from(120000);

    @Override
    public boolean canDiscount(Order order, VisitDate visitDate) {
        Money totalPrice = order.calculateTotalPrice();
        return totalPrice.isGreaterThanOrEqualTo(GIFT_EVENT_THRESHOLD);
    }

    @Override
    public Money discountAmount(Order order, VisitDate visitDate) {
        return getGiftItem().calculatePrice();
    }

    public OrderItem getGiftItem() {
        return OrderItem.of(Menu.CHAMPAGNE.getName(), 1);
    }
}
