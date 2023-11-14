package christmas.domain.model;

import java.util.*;

public class DiscountCalculator {

    private static final Money ALL_EVENT_THRESHOLD = Money.from(10000);
    private final OrderInfo orderInfo;

    public DiscountCalculator(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Money calculateTotalPrice() {
        return orderInfo.getOrder().calculateTotalPrice();
    }

    public Optional<OrderItem> calculateGiftMenu() {
        GiftEvent giftEvent = new GiftEvent();

        if (giftEvent.canDiscount(orderInfo)) {
            return Optional.of(giftEvent.getGiftItem());
        }

        return Optional.empty();
    }

    public DiscountDetails calculateDiscountDetails() {
        Map<EventType, Money> discountDetails = new HashMap<>();

        if (canApplyDiscountEvent()) {
            Arrays.stream(EventType.values())
                    .filter(event -> event.canDiscount(orderInfo))
                    .forEach(event -> discountDetails.put(event, event.discountAmount(orderInfo)));
        }

        return new DiscountDetails(discountDetails);
    }

    public Money calculateTotalDiscounts() {
        DiscountDetails discountDetails = calculateDiscountDetails();
        return discountDetails.getTotalDiscount();
    }

    private boolean canApplyDiscountEvent() {
        Money totalPrice = calculateTotalPrice();
        return totalPrice.isGreaterThanOrEqualTo(ALL_EVENT_THRESHOLD);
    }
}
