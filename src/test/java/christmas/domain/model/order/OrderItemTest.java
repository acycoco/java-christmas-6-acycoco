package christmas.domain.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderItemTest {

    @DisplayName("메뉴가격과 개수로 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1, 35000", "2, 70000"})
    void calculatePrice(int quantity, int expectedPrice) {
        OrderItem orderItem = OrderItem.of("해산물파스타", quantity);

        //when
        Money price = orderItem.calculatePrice();

        //then
        assertThat(price)
                .isEqualTo(Money.from(expectedPrice));
    }
}