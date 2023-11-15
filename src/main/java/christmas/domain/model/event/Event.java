package christmas.domain.model.event;

import christmas.domain.model.Money;
import christmas.domain.model.order.OrderInfo;

public interface Event {

    boolean canDiscount(OrderInfo orderInfo);

    Money discountAmount(OrderInfo orderInfo);
}
