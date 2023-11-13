package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

class OrderTest {

    @DisplayName("유효하지 않은 주문일 경우 에러가 발생한다.")
    @ParameterizedTest
    @MethodSource("createInvalidOrderRequests")
    void createOrderFail(List<OrderRequest> orderRequests) {
        assertThatThrownBy(() -> Order.from(orderRequests))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 주문일 경우 에러가 발생하지 않는다.")
    @ParameterizedTest
    @MethodSource("createValidOrderRequests")
    void createOrderSuccess(List<OrderRequest> orderRequests) {
        assertDoesNotThrow(() -> Order.from(orderRequests));
    }

    private static Stream<Arguments> createInvalidOrderRequests() {
        return Stream.of(
                // 중복된 메뉴가 있는 주문
                Arguments.of(
                        List.of(
                                new OrderRequest("해산물파스타", 1),
                                new OrderRequest("해산물파스타", 1)
                        )
                ),

                // 모든 주문이 음료인 주문
                Arguments.of(
                        List.of(
                                new OrderRequest("제로콜라", 1),
                                new OrderRequest("사이다", 1)
                        )
                ),

                // 존재하지 않는 메뉴를 시킨 주문
                Arguments.of(
                        List.of(
                                new OrderRequest("치킨", 1),
                                new OrderRequest("사이다", 1)
                        )
                ),

                // 주문 크기가 허용된 크기보다 큰 경우
                Arguments.of(
                        List.of(
                                new OrderRequest("해산물파스타", 21)
                        )
                )
        );
    }

    private static Stream<Arguments> createValidOrderRequests() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new OrderRequest("해산물파스타", 1),
                                new OrderRequest("레드와인", 1)
                        )
                ),

                Arguments.of(
                        List.of(
                                new OrderRequest("티본스테이크", 1),
                                new OrderRequest("양송이수프", 19)
                        )
                )
        );
    }
}
