package christmas.view;

import christmas.dto.OrderDto;

import java.util.Map;

public class OutputView {

    private static final String ORDER_MENU_PROMPT = "<주문메뉴>\n";
    public void printOrderMenu(OrderDto orderDto) {
        StringBuilder line = new StringBuilder(ORDER_MENU_PROMPT);
        Map<String, Integer> orderItems = orderDto.getOrderItems();

        orderItems.forEach((menuName, quantity) -> {
            line.append(menuName).append(" ").append(quantity).append("개\n");
        });

        System.out.println(line);
    }
}
