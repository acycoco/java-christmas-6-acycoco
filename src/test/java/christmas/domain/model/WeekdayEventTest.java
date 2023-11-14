package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigDecimal;
import java.util.List;



class WeekdayEventTest {

    @DisplayName("방문일자가 일~목이고 디저트 류를 1개이상 주문했으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 7})
    void canDiscountIfWeekdayOrOrderDessert(int day) {
        //given
        Order order = Order.from(List.of(new OrderRequest("초코케이크", 1)));
        December date = December.from(day);
        OrderInfo orderInfo = new OrderInfo(order, date);
        WeekdayEvent weekdayEvent = new WeekdayEvent();

        //when & then
        assertThat(weekdayEvent.canDiscount(orderInfo))
                .isEqualTo(true);
    }

    @DisplayName("방문일자가 일~목이 아니거나 디저트 류를 주문하지 않았으면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource({"7, 해산물파스타", "8, 초코케이크"})
    void cantDiscountIfNotWeekdayOrNotOrderDessert(int day, String menuName) {
        //given
        Order order = Order.from(List.of(new OrderRequest(menuName, 1)));
        December date = December.from(day);
        OrderInfo orderInfo = new OrderInfo(order, date);
        WeekdayEvent weekdayEvent = new WeekdayEvent();

        //when & then
        assertThat(weekdayEvent.canDiscount(orderInfo))
                .isEqualTo(false);
    }

    @DisplayName("디저트류 1개당 2023원 할인 금액이 증가한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 2023",
            "2, 4046",
    })
    void calculateDiscountAmountBasedOnDessertQuantity(int quantity, BigDecimal discount) {
        //given
        Order order = Order.from(List.of(new OrderRequest("초코케이크", quantity)));
        December date = December.from(7);
        OrderInfo orderInfo = new OrderInfo(order, date);
        WeekdayEvent weekdayEvent = new WeekdayEvent();

        //when & then
        assertThat(weekdayEvent.discountAmount(orderInfo).getAmount())
                .isEqualTo(discount);
    }
}