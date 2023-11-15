package christmas.domain.model.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.model.order.VisitDate;
import christmas.domain.model.order.Menu;
import christmas.domain.model.order.Order;
import christmas.dto.OrderRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class GiftEventTest {

    private GiftEvent giftEvent;
    private Order order;
    private VisitDate date;

    @BeforeEach
    void setUp() {
        order = Order.from(
                List.of(new OrderRequestDto("해산물파스타", 1),
                        new OrderRequestDto("크리스마스파스타", 1),
                        new OrderRequestDto("레드와인", 1))
        );
        date = VisitDate.from(25);
        giftEvent = new GiftEvent();

    }

    @DisplayName("주문이 12만원이상이면 true를 반환한다.")
    @Test
    void canDiscountIfTotalPriceIsGreaterThan120000() {
        //when & then
        assertThat(giftEvent.canDiscount(order, date))
                .isEqualTo(true);
    }

    @DisplayName("주문이 12만원미만이면 false를 반환한다.")
    @Test
    void cantDiscountIfTotalPriceIsNotGreaterThan120000() {
        order = Order.from(
                List.of(new OrderRequestDto("티본스테이크", 1),
                        new OrderRequestDto("해산물파스타", 1),
                        new OrderRequestDto("크리스마스파스타", 1),
                        new OrderRequestDto("제로콜라", 1))
        );
        date = VisitDate.from(25);
        GiftEvent giftEventFalse = new GiftEvent();

        //when & then
        assertThat(giftEventFalse.canDiscount(order, date))
                .isEqualTo(false);
    }

    @DisplayName("할인액이 증정상품인 샴페인의 가격과 같다.")
    @Test
    void discountAmountIs25000() {
        //when & then
        assertThat(giftEvent.discountAmount(order, date))
                .isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    @DisplayName("증정상품이 샴페인 1개이다.")
    @Test
    void getGift() {
        //when & then
        assertThat(giftEvent.getGiftItem().getMenu())
                .isEqualTo(Menu.CHAMPAGNE);

        assertThat(giftEvent.getGiftItem().getQuantity())
                .isEqualTo(1);
    }
}