package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;

    @BeforeEach
    void setUp() {
        OrderInfo orderInfo = new OrderInfo(
                Order.from(
                        List.of(
                                new OrderRequest("티본스테이크", 1),
                                new OrderRequest("바비큐립", 1),
                                new OrderRequest("초코케이크", 2),
                                new OrderRequest("제로콜라", 1)
                        )), December.from(3));
        discountCalculator = new DiscountCalculator(orderInfo);
    }

    @DisplayName("할인 전 총 주문 금액을 계산한다.")
    @Test
    void calculateTotalPriceTest() {
        //when
        Money totalPrice = discountCalculator.calculateTotalPrice();

        //then
        assertThat(totalPrice)
                .isEqualTo(Money.from(142000));
    }


}