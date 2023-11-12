package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

class ChristmasDdayEventTest {

    @DisplayName("2023년 12월 1~25일이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {25, 24, 1})
    void canDiscountIfBetweenDec1And25(int day) {
        //given
        OrderInfo orderInfo = new OrderInfo(LocalDate.of(2023, 12, day));
        ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent(orderInfo);

        //when & then
        assertThat(christmasDdayEvent.canDiscount())
                .isEqualTo(true);
    }

    @DisplayName("2023년 12월 26일~ 전이면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 31})
    void cantDiscountIfAfterDec25(int day) {
        //given
        OrderInfo orderInfo = new OrderInfo(LocalDate.of(2023, 12, day));
        ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent(orderInfo);

        //when & then
        assertThat(christmasDdayEvent.canDiscount())
                .isEqualTo(false);
    }
}