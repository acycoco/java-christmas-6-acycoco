package christmas.domain.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.Money;
import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Order;
import christmas.dto.OrderRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;

class SpecialEventTest {

    @DisplayName("방문일자가 별이 있는 날이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void canDiscountIfHasStar(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("초코케이크", 1)));
        VisitDate date = VisitDate.from(day);

        SpecialEvent specialEvent = new SpecialEvent();

        //when & then
        assertThat(specialEvent.canDiscount(order, date))
                .isEqualTo(true);
    }

    @DisplayName("방문일자가 별이 없는 날이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 9, 11, 16, 18, 23, 26, 30})
    void cantDiscountIfHasNotStar(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("초코케이크", 1)));
        VisitDate date = VisitDate.from(day);

        SpecialEvent specialEvent = new SpecialEvent();

        //when & then
        assertThat(specialEvent.canDiscount(order, date))
                .isEqualTo(false);
    }

    @DisplayName("디저트류 1개당 2023원 할인 금액이 증가한다.")
    @Test
    void returnDiscountAmount() {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("초코케이크", 5)));
        VisitDate date = VisitDate.from(3);

        SpecialEvent specialEvent = new SpecialEvent();

        //when & then
        assertThat(specialEvent.discountAmount(order, date))
                .isEqualTo(Money.from(1000));
    }
}