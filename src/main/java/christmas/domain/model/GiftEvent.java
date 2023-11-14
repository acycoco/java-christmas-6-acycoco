package christmas.domain.model;

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
