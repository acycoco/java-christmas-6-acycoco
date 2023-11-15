package christmas.dto;

import christmas.domain.model.order.Order;
import christmas.domain.model.order.OrderItem;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDto {

    private final Map<String, Integer> orderItems;

    private OrderDto(Map<String, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public static OrderDto from(Order order) {
        Map<String, Integer> orderItems = order.getOrderItems()
                .stream()
                .collect(Collectors.toMap(
                        OrderItem::getMenuName,
                        OrderItem::getQuantity
                ));

        return new OrderDto(orderItems);
    }

    public Map<String, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderItems);
    }
}
