package christmas.domain.model.eventcalculator;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventBadgeTest {

    @DisplayName("혜택금액으로 해당되는 이벤트 뱃지를 구한다.")
    @ParameterizedTest
    @CsvSource({"0, NONE",
            "4900, NONE",
            "5000, STAR",
            "9999, STAR",
            "10000, TREE",
            "19999, TREE",
            "20000, SANTA",
            "150000, SANTA",
    })
    void returnEventBadgeFromBenefitAmounts(int benefitAmounts, EventBadge expectedEventBadge) {
        //when
        EventBadge eventBadge = EventBadge.fromBenefitAmounts(Money.from(benefitAmounts));

        //then
        assertThat(eventBadge)
                .isEqualTo(expectedEventBadge);
    }
}