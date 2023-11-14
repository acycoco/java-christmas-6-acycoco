package christmas.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DiscountDetails {

    private final Map<EventType, Money> details;

    public DiscountDetails(Map<EventType, Money> details) {
        this.details = details;
    }

    public Money getTotalDiscount() {
        List<Money> discounts = details.values()
                .stream()
                .toList();
        return Money.sum(discounts);
    }
    public Map<EventType, Money> getDetails() {
        return Collections.unmodifiableMap(details);
    }
}
