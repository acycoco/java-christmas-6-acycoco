package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

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

    @DisplayName("할인내역을 계산한다.")
    @Test
    void calculateDiscountDetailsTest() {
        //when
        DiscountDetails discountDetails = discountCalculator.calculateDiscountDetails();
        Map<EventType, Money> details = discountDetails.getDetails();

        //then
        assertThat(details.size())
                .isEqualTo(4);
        assertThat(details.get(EventType.CHRISTMAS_DDAY_DISCOUNT))
                .isEqualTo(Money.from(1200));
        assertThat(details.get(EventType.WEEKDAY_DISCOUNT))
                .isEqualTo(Money.from(4046));
        assertThat(details.get(EventType.SPECIAL_DISCOUNT))
                .isEqualTo(Money.from(1000));
        assertThat(details.get(EventType.GIFT_EVENT))
                .isEqualTo(Money.from(25000));
    }

    //    <할인 전 총주문 금액>
//            142,000원
//
//<증정 메뉴>
//    샴페인 1개
//
//<혜택 내역>
//    크리스마스 디데이 할인: -1,200원
//    평일 할인: -4,046원
//    특별 할인: -1,000원
//    증정 이벤트: -25,000원
//
//<총혜택 금액>
//-31,246원

}