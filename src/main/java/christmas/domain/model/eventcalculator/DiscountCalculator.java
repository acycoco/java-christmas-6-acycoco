package christmas.domain.model.eventcalculator;

import christmas.domain.model.Money;
import christmas.domain.model.event.EventType;
import christmas.domain.model.event.GiftEvent;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.OrderItem;
import christmas.domain.model.order.VisitDate;

import java.util.*;

public class DiscountCalculator {

    private static final Money ALL_EVENT_THRESHOLD = Money.from(10000);

    private final Order order;
    private final VisitDate visitDate;

    public DiscountCalculator(Order order, VisitDate visitDate) {
        this.order = order;
        this.visitDate = visitDate;
    }

    public Money calculateTotalPrice() {
        return order.calculateTotalPrice();
    }

    public Optional<OrderItem> calculateGiftMenu() {
        GiftEvent giftEvent = new GiftEvent();

        if (giftEvent.canDiscount(order, visitDate)) {
            return Optional.of(giftEvent.getGiftItem());
        }

        return Optional.empty();
    }

    public BenefitDetails calculateBenefitDetails() {
        Map<EventType, Money> benefitDetails = new HashMap<>();

        if (canApplyDiscountEvent()) {
            Arrays.stream(EventType.values())
                    .filter(event -> event.canDiscount(order, visitDate))
                    .forEach(event -> benefitDetails.put(event, event.discountAmount(order, visitDate)));
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
