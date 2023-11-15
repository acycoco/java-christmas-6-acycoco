package christmas.view;

import christmas.domain.model.OrderRequest;

import java.util.Arrays;
import java.util.List;

public class InputProcessor {

    private static final String COMMA = ",";
    private static final String DASH = "-";

    public int toDate(String input) {
        validateNotNull(input);
        input = input.trim();
        return toNumber(input);
    }

    public List<OrderRequest> toOrderRequests(String input) {
        validateNotNull(input);
        input = input.trim();

        List<String> orderItems = parseWith(input, COMMA);
        return orderItems.stream()
                .map(orderItem -> parseOrderItem(orderItem, DASH))
                .toList();
    }


    private void validateNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
    }

    private Integer toNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private List<String> parseWith(String input, String mark) {
        return Arrays.stream(input.split(mark))
                .map(String::trim)
                .toList();
    }

    private OrderRequest parseOrderItem(String orderItem, String mark) {
        List<String> orderItemParts = parseWith(orderItem, mark);
        validateOrderItemPartsLength(orderItemParts);

        String menuName = orderItemParts.get(0);
        int quantity = Integer.parseInt(orderItemParts.get(1));
        return new OrderRequest(menuName, quantity);
    }

    private void validateOrderItemPartsLength(List<String> orderItemParts) {
        if (orderItemParts.size() != 2) {
            throw new IllegalArgumentException();
        }
    }

}

