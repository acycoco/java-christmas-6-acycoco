package christmas.view;

import christmas.dto.GiftMenuDto;
import christmas.dto.MoneyDto;
import christmas.dto.OrderDto;

import java.math.BigDecimal;
import java.util.Map;

public class OutputView {

    private static final String ORDER_MENU_PROMPT = "<주문메뉴>\n";
    private static final String TOTAL_ORDER_PRICE_PROMPT = "<할인 전 총주문 금액>\n";
    private static final String GIFT_MENU_PROMPT = "<증정 메뉴>\n";

    public void printOrderMenu(OrderDto orderDto) {
        StringBuilder line = new StringBuilder(ORDER_MENU_PROMPT);
        Map<String, Integer> orderItems = orderDto.getOrderItems();

        orderItems.forEach((menuName, quantity) -> {
            line.append(menuName).append(" ").append(quantity).append("개\n");
        });

        System.out.println(line);
    }

    public void printTotalOrderPrice(MoneyDto totalOrderPrice) {
        StringBuilder line = new StringBuilder(TOTAL_ORDER_PRICE_PROMPT);
        line.append(formatMoney(totalOrderPrice.getMoney()));
        System.out.println(line);
    }

    public void printGiftMenu(GiftMenuDto giftMenuDto) {
        StringBuilder line = new StringBuilder(GIFT_MENU_PROMPT);

        Map<String, Integer> giftMenus = giftMenuDto.getGiftMenu();

        if (giftMenus.isEmpty()) {
            line.append("없음");
        }

        if (!giftMenus.isEmpty()) {
            giftMenus.forEach((menuName, quantity) -> {
                line.append(menuName).append(" ").append(quantity).append("개\n");
            });
        }

        System.out.println(line);
    }

    private String formatMoney(BigDecimal money) {
        return String.format("%,.0f원", money);
    }
}
