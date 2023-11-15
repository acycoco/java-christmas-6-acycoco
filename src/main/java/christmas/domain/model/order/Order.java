package christmas.domain.model.order;

import christmas.domain.model.Money;
import christmas.dto.OrderRequestDto;

import java.util.Collections;
import java.util.List;

public class Order {

    private static final int MAX_ORDER_TOTAL_QUANTITY = 20;
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Order from(List<OrderRequestDto> orderRequestDtos) {
        List<OrderItem> orderItems = orderRequestDtos.stream()
                .map(orderRequest -> OrderItem.of(orderRequest.getMenuName(), orderRequest.getQuantity()))
                .toList();

        validateOrderSize(orderItems);
        validateUniqueMenu(orderItems);
        validateNotAllBeverageOrder(orderItems);

        return new Order(orderItems);
    }

    public Money calculateTotalPrice() {
        List<Money> orderItemsPrice = orderItems.stream()
                .map(OrderItem::calculatePrice)
                .toList();

        return Money.sum(orderItemsPrice);
    }

    public int countDessertOrders() {
        return orderItems.stream()
                .filter(orderItem -> orderItem.getMenu().isDessert())
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public int countMainOrders() {
        return orderItems.stream()
                .filter(orderItem -> orderItem.getMenu().isMain())
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    private static void validateOrderSize(List<OrderItem> orderItems) {
        int totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();

        if (totalQuantity > MAX_ORDER_TOTAL_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateUniqueMenu(List<OrderItem> orderItems) {
        long distinctMenuCount = orderItems.stream()
                .map(OrderItem::getMenu)
                .distinct()
                .count();

        if (distinctMenuCount != orderItems.size()) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateNotAllBeverageOrder(List<OrderItem> orderItems) {
        if (isAllBeverageOrder(orderItems)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isAllBeverageOrder(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getMenu)
                .allMatch(Menu::isBeverage);
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
