package christmas.domain.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Order;
import christmas.dto.OrderRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigDecimal;
import java.util.List;

class ChristmasDdayEventTest {

    @DisplayName("2023년 12월 1~25일이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {25, 24, 1})
    void canDiscountIfBetweenDec1And25(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("해산물파스타", 1)));
        VisitDate date = VisitDate.from(day);

        ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();

        //when & then
        assertThat(christmasDdayEvent.canDiscount(order, date))
                .isEqualTo(true);
    }

    @DisplayName("2023년 12월 26일~ 전이면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 31})
    void cantDiscountIfAfterDec25(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("해산물파스타", 1)));
        VisitDate date = VisitDate.from(day);

        ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();

        //when & then
        assertThat(christmasDdayEvent.canDiscount(order, date))
                .isEqualTo(false);
    }

    @DisplayName("1,000원으로 시작해 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 1000",
            "2, 1100",
            "25, 3400"
    })
    void calculateDiscountAmountBasedOnChristmasDDay(int christmasDDay, BigDecimal discount) {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("해산물파스타", 1)));
        VisitDate date = VisitDate.from(christmasDDay);

        ChristmasDdayEvent christmasDdayEvent = new ChristmasDdayEvent();

        //when & then
        assertThat(christmasDdayEvent.discountAmount(order, date).getAmount())
                .isEqualTo(discount);
    }
}