package christmas.dto;

import christmas.domain.model.BenefitDetails;
import christmas.domain.model.EventType;
import christmas.domain.model.Money;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BenefitDetailsDto {

    private final Map<String, BigDecimal> details;

    private BenefitDetailsDto(Map<String, BigDecimal> details) {
        this.details = details;
    }

    public static BenefitDetailsDto from(BenefitDetails benefitDetails) {
        Map<String, BigDecimal> details = new HashMap<>();

        benefitDetails.getDetails().forEach((eventType, money) -> {
            details.put(eventType.getName(), money.getAmount());
        });

        return new BenefitDetailsDto(details);
    }

    public Map<String, BigDecimal> getDetails() {
        return Collections.unmodifiableMap(details);
    }
}
