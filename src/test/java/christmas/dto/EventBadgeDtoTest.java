package christmas.dto;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventBadgeDtoTest {

    @DisplayName("이벤트 뱃지로 이벤트 뱃지 dto객체 생성에 성공한다.")
    @ParameterizedTest
    @CsvSource({"NONE, 없음",
            "STAR, 별",
            "TREE, 트리",
            "SANTA, 산타",
    })
    void createEventBadgeDtoFromEventBadge(EventBadge eventBadge, String eventBadgeName) {
        //when
        EventBadgeDto eventBadgeDto = EventBadgeDto.from(eventBadge);

        //then
        assertThat(eventBadgeDto.getBadgeName())
                .isEqualTo(eventBadgeName);
    }
}