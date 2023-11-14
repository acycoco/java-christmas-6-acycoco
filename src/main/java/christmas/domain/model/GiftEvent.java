package christmas.domain.model;

public class GiftEvent implements Event{

    private static final Money GIFT_EVENT_THRESHOLD = Money.from(120000);

    private final OrderInfo orderInfo;

    public GiftEvent(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public boolean canDiscount() {
        Money totalPrice = orderInfo.getOrder().calculateTotalPrice();
        return totalPrice.isGreaterThanOrEqualTo(GIFT_EVENT_THRESHOLD);
    }

    @Override
    public Money discountAmount() {
        return getGift().getPrice();
    }

    public Menu getGift() {
        return Menu.CHAMPAGNE;
    }
}
