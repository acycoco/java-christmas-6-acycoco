package christmas.domain.model;

import java.math.BigDecimal;

public interface Event {

    boolean canDiscount();

    BigDecimal discountAmount();
}
