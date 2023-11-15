package christmas.domain.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.Money;
import christmas.domain.model.order.December;
import christmas.domain.model.order.Order;
import christmas.domain.model.order.OrderInfo;
import christmas.dto.OrderRequestDto;
import org.junit.jupiter.api.DisplayName;
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
        December date = December.from(day);
        OrderInfo orderInfo = new OrderInfo(order, date);
        SpecialEvent specialEvent = new SpecialEvent();

        //when & then
        assertThat(specialEvent.canDiscount(orderInfo))
                .isEqualTo(true);
    }

    @DisplayName("방문일자가 별이 없는 날이면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 9, 11, 16, 18, 23, 26, 30})
    void cantDiscountIfHasNotStar(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("초코케이크", 1)));
        December date = December.from(day);
        OrderInfo orderInfo = new OrderInfo(order, date);
        SpecialEvent specialEvent = new SpecialEvent();

        //when & then
        assertThat(specialEvent.canDiscount(orderInfo))
                .isEqualTo(false);
    }

    @DisplayName("디저트류 1개당 2023원 할인 금액이 증가한다.")
    void returnDiscountAmount() {
        //given
        Order order = Order.from(List.of(new OrderRequestDto("초코케이크", 5)));
        December date = December.from(3);
        OrderInfo orderInfo = new OrderInfo(order, date);
        SpecialEvent specialEvent = new SpecialEvent();

        //when & then
        assertThat(specialEvent.discountAmount(orderInfo))
                .isEqualTo(Money.from(1000));
    }
}