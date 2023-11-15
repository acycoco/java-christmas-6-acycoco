package christmas.dto;

import christmas.domain.model.Order;
import christmas.domain.model.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

    @DisplayName("주문 내역 dto객체 생성에 성공한다.")
    @Test
    void createFromOrder() {
        Order order = Order.from(List.of(
                new OrderRequest("해산물파스타", 1),
                new OrderRequest("레드와인", 2)
        ));

        OrderDto orderDto = OrderDto.from(order);
        Map<String, Integer> orderItems = orderDto.getOrderItems();
        //then
        assertThat(orderItems.size())
                .isEqualTo(2);
        assertThat(orderItems.get("해산물파스타"))
                .isEqualTo(1);
        assertThat(orderItems.get("레드와인"))
                .isEqualTo(2);
    }
}