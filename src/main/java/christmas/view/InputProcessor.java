package christmas.view;

import christmas.dto.OrderRequestDto;

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

    public List<OrderRequestDto> toOrderRequests(String input) {
        validateNotNull(input);
        input = input.trim();

        List<String> orderItems = parseWithMark(input, COMMA);
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

    private List<String> parseWithMark(String input, String mark) {
        return Arrays.stream(input.split(mark))
                .map(String::trim)
                .toList();
    }

    private OrderRequestDto parseOrderItem(String orderItem, String mark) {
        List<String> orderItemParts = parseWithMark(orderItem, mark);
        validateOrderItemPartsLength(orderItemParts);

        String menuName = orderItemParts.get(0);
        int quantity = Integer.parseInt(orderItemParts.get(1));
        return new OrderRequestDto(menuName, quantity);
    }

    private void validateOrderItemPartsLength(List<String> orderItemParts) {
        if (orderItemParts.size() != 2) {
            throw new IllegalArgumentException();
        }
    }

}

