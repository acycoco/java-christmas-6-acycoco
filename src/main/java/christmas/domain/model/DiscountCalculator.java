package christmas.domain.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DiscountCalculator {

    private static final Money ALL_EVENT_THRESHOLD = Money.from(10000);
    private final OrderInfo orderInfo;

    public DiscountCalculator(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Money calculateTotalPrice() {
        return orderInfo.getOrder().calculateTotalPrice();
    }

}
