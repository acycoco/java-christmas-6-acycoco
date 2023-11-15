package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Order;

public interface Event {

    boolean canDiscount(Order order, VisitDate visitDate);

    Money discountAmount(Order order, VisitDate visitDate);
}
