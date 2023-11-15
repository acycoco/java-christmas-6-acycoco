package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @DisplayName("증정이벤트에 해당하는 경우 증정상품을 구한다.")
    @Test
    void calculateGiftMenuIfAvailableTest() {
        //when
        Optional<OrderItem> optionalGiftItem = discountCalculator.calculateGiftMenu();

        //then
        assertThat(optionalGiftItem.isPresent())
                .isEqualTo(true);
        assertThat(optionalGiftItem.get().getMenu())
                .isEqualTo(Menu.CHAMPAGNE);

        assertThat(optionalGiftItem.get().getQuantity())
                .isEqualTo(1);
    }

    @DisplayName("증정이벤트에 해당하지 않는 경우 증정상품이 없다.")
    @Test
    void calculateGiftMenuIfNotAvailableTest() {
        //given
        OrderInfo orderInfo = new OrderInfo(
                Order.from(
                        List.of(
                                new OrderRequest("티본스테이크", 1)
                        )), December.from(3));
        discountCalculator = new DiscountCalculator(orderInfo);

        //when
        Optional<OrderItem> optionalGiftItem = discountCalculator.calculateGiftMenu();

        //then
        assertThat(optionalGiftItem.isEmpty())
                .isEqualTo(true);
    }

    @DisplayName("혜택내역을 계산한다.")
    @Test
    void calculateBenefitDetailsTest() {
        //when
        BenefitDetails discountDetails = discountCalculator.calculateBenefitDetails();
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

    @DisplayName("총 혜택 금액을 계산한다.")
    @Test
    void calculateTotalBenfits() {
        //when
        Money totalDiscounts = discountCalculator.calculateTotalBenefitAmounts();

        assertThat(totalDiscounts)
                .isEqualTo(Money.from(31246));
    }

    @DisplayName("할인 후 총 예상 결제 금액을 계산한다.")
    @Test
    void calculateExpectedPaymentTest() {
        //when
        Money expectedPayment = discountCalculator.calculateExpectedPayment();

        //then
        assertThat(expectedPayment)
                .isEqualTo(Money.from(135754));
    }

    @DisplayName("해당되는 이벤트 뱃지를 구한다.")
    @Test
    void calculateEventBadgeTest() {
        //when
        EventBadge eventBadge = discountCalculator.calculateEventBadge();

        //then
        assertThat(eventBadge)
                .isEqualTo(EventBadge.SANTA);
    }
}