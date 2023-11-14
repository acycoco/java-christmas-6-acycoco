package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class GiftEventTest {

    private GiftEvent giftEvent;
    private OrderInfo orderInfo;

    @BeforeEach
    void setUp() {
        Order order = Order.from(
                List.of(new OrderRequest("해산물파스타", 1),
                        new OrderRequest("크리스마스파스타", 1),
                        new OrderRequest("레드와인", 1))
        );
        December date = December.from(25);
        orderInfo = new OrderInfo(order, date);
        giftEvent = new GiftEvent();

    }

    @DisplayName("주문이 12만원이상이면 true를 반환한다.")
    @Test
    void canDiscountIfTotalPriceIsGreaterThan120000() {
        //when & then
        assertThat(giftEvent.canDiscount(orderInfo))
                .isEqualTo(true);
    }

    @DisplayName("주문이 12만원미만이면 false를 반환한다.")
    @Test
    void cantDiscountIfTotalPriceIsNotGreaterThan120000() {
        Order order = Order.from(
                List.of(new OrderRequest("티본스테이크", 1),
                        new OrderRequest("해산물파스타", 1),
                        new OrderRequest("크리스마스파스타", 1),
                        new OrderRequest("제로콜라", 1))
        );
        December date = December.from(25);
        OrderInfo orderInfoFalse = new OrderInfo(order, date);
        GiftEvent giftEventFalse = new GiftEvent();

        //when & then
        assertThat(giftEventFalse.canDiscount(orderInfoFalse))
                .isEqualTo(false);
    }

    @DisplayName("할인액이 증정상품인 샴페인의 가격과 같다.")
    @Test
    void discountAmountIs25000() {
        //when & then
        assertThat(giftEvent.discountAmount(orderInfo))
                .isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    @DisplayName("증정상품이 샴페인이다.")
    @Test
    void getGift() {
        //when & then
        assertThat(giftEvent.getGift())
                .isEqualTo(Menu.CHAMPAGNE);
    }
}