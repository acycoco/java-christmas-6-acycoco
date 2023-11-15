package christmas.domain.model.eventcalculator;

import christmas.domain.model.Money;
import christmas.domain.model.event.EventType;
import christmas.domain.model.event.GiftEvent;
import christmas.domain.model.order.OrderInfo;
import christmas.domain.model.order.OrderItem;

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

    public BenefitDetails calculateBenefitDetails() {
        Map<EventType, Money> benefitDetails = new HashMap<>();

        if (canApplyDiscountEvent()) {
            Arrays.stream(EventType.values())
                    .filter(event -> event.canDiscount(orderInfo))
                    .forEach(event -> benefitDetails.put(event, event.discountAmount(orderInfo)));
        }

        return new BenefitDetails(benefitDetails);
    }

    public Money calculateTotalBenefitAmounts() {
        BenefitDetails benefitDetails = calculateBenefitDetails();
        return benefitDetails.getTotalBenefitAmounts();
    }

    public Money calculateExpectedPayment() {
        Money totalPrice = calculateTotalPrice();
        Money totalDiscountAmounts = calculateTotalDiscountAmounts();
        return totalPrice.subtract(totalDiscountAmounts);
    }

    public EventBadge calculateEventBadge() {
        Money totalBenefitAmounts = calculateTotalBenefitAmounts();
        return EventBadge.fromBenefitAmounts(totalBenefitAmounts);
    }

    private boolean canApplyDiscountEvent() {
        Money totalPrice = calculateTotalPrice();
        return totalPrice.isGreaterThanOrEqualTo(ALL_EVENT_THRESHOLD);
    }

    private Money calculateTotalDiscountAmounts() {
        Money totalBenefitAmounts = calculateTotalBenefitAmounts();
        Optional<OrderItem> giftBenefit = calculateGiftMenu();

        if (giftBenefit.isPresent()) {
            OrderItem giftItem = giftBenefit.get();
            return totalBenefitAmounts.subtract(giftItem.calculatePrice());
        }

        return totalBenefitAmounts;
    }
}
