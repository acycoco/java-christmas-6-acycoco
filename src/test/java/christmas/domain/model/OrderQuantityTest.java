package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class OrderQuantityTest {

    @DisplayName("주문 개수가 1미만일 경우 에러가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void validateRangeFail(int quantity) {
        //when & then
        assertThatThrownBy(() -> OrderQuantity.from(quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 개수가 1미만일 경우 생성에 성공한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    void validateRangeSuccess(int quantity) {
        //when
        OrderQuantity orderQuantity = OrderQuantity.from(quantity);

        //then
        assertThat(orderQuantity.getQuantity())
                .isEqualTo(quantity);
    }

    @DisplayName("개수가 같으면 같은 동등성을 가진다.")
    @Test
    void testEquals() {
        //given
        OrderQuantity orderQuantity1 = OrderQuantity.from(5);
        OrderQuantity orderQuantity2 = OrderQuantity.from(5);

        //when & then
        assertThat(orderQuantity1.equals(orderQuantity2))
                .isEqualTo(true);
    }
}