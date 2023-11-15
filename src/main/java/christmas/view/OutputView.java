package christmas.view;

import christmas.dto.BenefitDetailsDto;
import christmas.dto.GiftMenuDto;
import christmas.dto.MoneyDto;
import christmas.dto.OrderDto;

import java.math.BigDecimal;
import java.util.Map;

public class OutputView {

    private static final String ORDER_MENU_PROMPT = "<주문메뉴>\n";
    private static final String TOTAL_ORDER_PRICE_PROMPT = "<할인 전 총주문 금액>\n";
    private static final String GIFT_MENU_PROMPT = "<증정 메뉴>\n";
    private static final String BENEFIT_DETAILS_PROMPT = "<혜택 내역>\n";
    private static final String TOTAL_BENEFIT_AMOUNTS_PROMPT = "<총혜택 금액>\n";
    private static final String EXPECTED_PAYMENT_PROMPT = "<할인 후 예상 결제 금액>\n";

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

    public void printBenefitDetails(BenefitDetailsDto benefitDetailsDto) {
        StringBuilder line = new StringBuilder(BENEFIT_DETAILS_PROMPT);

        Map<String, BigDecimal> detailsDetails = benefitDetailsDto.getDetails();

        if (detailsDetails.isEmpty()) {
            line.append("없음");
        }

        if (!detailsDetails.isEmpty()) {
            detailsDetails.forEach((eventName, money) -> {
                line.append(eventName).append(": ").append(formatMinusMoney(money)).append("원\n");
            });
        }

        System.out.println(line);
    }

    public void printTotalBenefitAmounts(MoneyDto totalBenefitAmounts) {
        StringBuilder line = new StringBuilder(TOTAL_BENEFIT_AMOUNTS_PROMPT);
        line.append(formatMinusMoney(totalBenefitAmounts.getMoney()));
        System.out.println(line);
    }

    public void printExpectedPayment(MoneyDto expectedPayment) {
        StringBuilder line = new StringBuilder(EXPECTED_PAYMENT_PROMPT);
        line.append(formatMoney(expectedPayment.getMoney()));
        System.out.println(line);
    }

    private String formatMoney(BigDecimal money) {
        return String.format("%,.0f원", money);
    }
    private String formatMinusMoney(BigDecimal money) {
        return String.format("-%,.0f원", money);
    }
}
