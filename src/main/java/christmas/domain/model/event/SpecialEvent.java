package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.VisitDate;

public class SpecialEvent implements Event {

    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public boolean canDiscount(Order order, VisitDate visitDate) {
        return visitDate.hasStar();
    }

    @Override
    public Money discountAmount(Order order, VisitDate visitDate) {
        return Money.from(SPECIAL_DISCOUNT);
    }
}
