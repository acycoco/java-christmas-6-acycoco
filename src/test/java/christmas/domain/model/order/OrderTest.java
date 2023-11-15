package christmas.domain.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.domain.model.Money;
import christmas.dto.OrderRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

class OrderTest {

    @DisplayName("유효하지 않은 주문일 경우 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("createInvalidOrderRequests")
    void createOrderFail(List<OrderRequestDto> orderRequestDtos) {
        assertThatThrownBy(() -> Order.from(orderRequestDtos))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 주문일 경우 에러가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createValidOrderRequests")
    void createOrderSuccess(List<OrderRequestDto> orderRequestDtos) {
        assertDoesNotThrow(() -> Order.from(orderRequestDtos));
    }

    @DisplayName("주문 리스트로 총금액을 계산한다.")
    @Test
    void calculateTotalPriceTest() {
        //given
        Order order = Order.from(
                List.of(new OrderRequestDto("해산물파스타", 1),
                        new OrderRequestDto("레드와인", 1))
        );

        //when
        Money totalPrice = order.calculateTotalPrice();

        //then
        assertThat(totalPrice)
                .isEqualTo(Money.from(95000));
    }

    private static Stream<Arguments> createInvalidOrderRequests() {
        return Stream.of(
                // 중복된 메뉴가 있는 주문
                Arguments.of(
                        List.of(
                                new OrderRequestDto("해산물파스타", 1),
                                new OrderRequestDto("해산물파스타", 1)
                        )
                ),

                // 모든 주문이 음료인 주문
                Arguments.of(
                        List.of(
                                new OrderRequestDto("제로콜라", 1),
                                new OrderRequestDto("사이다", 1)
                        )
                ),

                // 존재하지 않는 메뉴를 시킨 주문
                Arguments.of(
                        List.of(
                                new OrderRequestDto("치킨", 1),
                                new OrderRequestDto("사이다", 1)
                        )
                ),

                // 주문 크기가 허용된 크기보다 큰 경우
                Arguments.of(
                        List.of(
                                new OrderRequestDto("해산물파스타", 21)
                        )
                )
        );
    }

    private static Stream<Arguments> createValidOrderRequests() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new OrderRequestDto("해산물파스타", 1),
                                new OrderRequestDto("레드와인", 1)
                        )
                ),

                Arguments.of(
                        List.of(
                                new OrderRequestDto("티본스테이크", 1),
                                new OrderRequestDto("양송이수프", 19)
                        )
                )
        );
    }
}
