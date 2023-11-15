package christmas.dto;

import christmas.domain.model.eventcalculator.EventBadge;

public class EventBadgeDto {

    private final String badgeName;

    private EventBadgeDto(String badgeName) {
        this.badgeName = badgeName;
    }

    public static EventBadgeDto from(EventBadge eventBadge) {
        return new EventBadgeDto(eventBadge.getName());
    }

    public String getBadgeName() {
        return badgeName;
    }
}
