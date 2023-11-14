package christmas.domain.model;

import java.math.BigDecimal;

public interface Event {

    boolean canDiscount(OrderInfo orderInfo);

    Money discountAmount(OrderInfo orderInfo);
}
