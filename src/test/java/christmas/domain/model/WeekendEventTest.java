package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigDecimal;
import java.util.List;

class WeekendEventTest {

    @DisplayName("방문일자가 금,토이고 메인 류를 1개이상 주문했으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {8, 9})
    void canDiscountIfWeekendOrOrderMain(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequest("티본스테이크", 1)));
        December date = December.from(day);
        OrderInfo orderInfo = new OrderInfo(order, date);
        WeekendEvent weekendEvent = new WeekendEvent();

        //when & then
        assertThat(weekendEvent.canDiscount(orderInfo))
                .isEqualTo(true);
    }

    @DisplayName("방문일자가 금,토가 아니거나 메인 류를 주문하지 않았으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"8, 초코케이크", "7, 해산물파스타"})
    void cantDiscountIfNotWeekendOrNotOrderMain(int day, String menuName) {
        //given
        Order order = Order.from(List.of(new OrderRequest(menuName, 1)));
        December date = December.from(day);
        OrderInfo orderInfo = new OrderInfo(order, date);
        WeekendEvent weekendEvent = new WeekendEvent();

        //when & then
        assertThat(weekendEvent.canDiscount(orderInfo))
                .isEqualTo(false);
    }

    @DisplayName("메인류 1개당 2023원 할인 금액이 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 2023",
            "2, 4046",
    })
    void calculateDiscountAmountBasedOnMainQuantity(int quantity, BigDecimal discount) {
        //given
        Order order = Order.from(List.of(new OrderRequest("해산물파스타", quantity)));
        December date = December.from(8);
        OrderInfo orderInfo = new OrderInfo(order, date);
        WeekendEvent weekendEvent = new WeekendEvent();

        //when & then
        assertThat(weekendEvent.discountAmount(orderInfo).getAmount())
                .isEqualTo(discount);
    }
}