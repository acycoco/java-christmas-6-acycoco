package christmas.view;

import christmas.domain.model.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputProcessorTest {

    private InputProcessor inputProcessor;

    @BeforeEach
    void setUp() {
        inputProcessor = new InputProcessor();
    }

    @DisplayName("날짜를 숫자로 변환하는 데 성공한다.")
    @Test
    void toDateSuccess() {
        Integer date = inputProcessor.toDate(" 13 ");

        assertThat(date)
                .isEqualTo(13);
    }

    @DisplayName("문자가 들어있어서 날짜를 숫자로 변환하는 데 실패한다.")
    @Test
    void toDateFail() {
        assertThatThrownBy(() -> inputProcessor.toDate(",13 "))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("주문을 orderRequests로 변환하는 데 성공한다.")
    @Test
    void totoOrderRequestsSuccess() {
        List<OrderRequest> orderRequests = inputProcessor.toOrderRequests("타파스 - 1, 제로콜라-2  ");

        assertThat(orderRequests.size())
                .isEqualTo(2);
        assertThat(orderRequests.get(0).getMenuName())
                .isEqualTo("타파스");
        assertThat(orderRequests.get(0).getQuantity())
                .isEqualTo(1);
        assertThat(orderRequests.get(1).getMenuName())
                .isEqualTo("제로콜라");
        assertThat(orderRequests.get(1).getQuantity())
                .isEqualTo(2);
    }

    @DisplayName("주문개수에 문자가 들어있어서 orderRequests로 변환하는 데 실패한다.")
    @Test
    void toOrderRequestFailIfContainString() {
        assertThatThrownBy(() -> inputProcessor.toOrderRequests("타파스-1,제로콜라-b  "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문하나가 메뉴, 개수말고 다른 게 포함된 경우 orderRequests로 변환하는 데 실패한다.")
    @Test
    void toOrderRequestFailIfLengthIsNotTwo() {
        assertThatThrownBy(() -> inputProcessor.toOrderRequests("타파스-1-2,제로콜라-1  "))
                .isInstanceOf(IllegalArgumentException.class);
    }

}