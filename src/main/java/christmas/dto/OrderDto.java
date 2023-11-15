package christmas.dto;

import christmas.domain.model.Order;
import christmas.domain.model.OrderItem;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDto {

    private final Map<String, Integer> orderItems;

    private OrderDto(Map<String, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public static OrderDto from(Order order) {
        Map<String, Integer> orderItemsMap = order.getOrderItems()
                .stream()
                .collect(Collectors.toMap(
                        OrderItem::getMenuName,
                        OrderItem::getQuantity
                ));

        return new OrderDto(orderItemsMap);
    }

    public Map<String, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderItems);
    }
}
