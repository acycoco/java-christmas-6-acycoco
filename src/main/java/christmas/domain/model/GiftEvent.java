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
        return getGift().getPrice();
    }

    public Menu getGift() {
        return Menu.CHAMPAGNE;
    }
}
