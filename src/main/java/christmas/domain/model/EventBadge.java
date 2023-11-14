package christmas.domain.model;

import java.util.Arrays;

public enum EventBadge {

    SANTA(Money.from(20000), "산타"),
    TREE(Money.from(10000), "트리"),
    STAR(Money.from(5000), "별"),
    NONE(Money.from(0), "없음");

    private final Money benefitAmountThreshold;
    private final String name;

    EventBadge( Money benefitAmountThreshold, String name) {
        this.benefitAmountThreshold = benefitAmountThreshold;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EventBadge fromBenefitAmounts(Money benefitAmounts) {
        return Arrays.stream(values())
                .filter(badge -> benefitAmounts.isGreaterThanOrEqualTo(badge.benefitAmountThreshold))
                .findFirst()
                .orElse(NONE);
    }
}
