package christmas.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BenefitDetails {

    private final Map<EventType, Money> details;

    public BenefitDetails(Map<EventType, Money> details) {
        this.details = details;
    }

    public Money getTotalBenefitAmounts() {
        List<Money> benefits = details.values()
                .stream()
                .toList();
        return Money.sum(benefits);
    }
    public Map<EventType, Money> getDetails() {
        return Collections.unmodifiableMap(details);
    }
}
