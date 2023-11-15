package christmas.view;

import christmas.dto.BenefitDetailsDto;
import christmas.dto.GiftMenuDto;
import christmas.dto.MoneyDto;
import christmas.dto.OrderDto;
import christmas.dto.EventBadgeDto;
import christmas.exception.ErrorMessage;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ORDER_MENU_PROMPT = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE_PROMPT = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_PROMPT = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_PROMPT = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNTS_PROMPT = "<총혜택 금액>";
    private static final String EXPECTED_PAYMENT_PROMPT = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_PROMPT = "<12월 이벤트 배지>";

    public void printOrderMenu(OrderDto orderDto) {
        Map<String, Integer> orderItems = orderDto.getOrderItems();

        String content = orderItems.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining(LINE_SEPARATOR));

        printPromptWithContent(ORDER_MENU_PROMPT, content);
    }

    public void printTotalOrderPrice(MoneyDto totalOrderPrice) {
        String content = formatMoney(totalOrderPrice.getMoney());
        printPromptWithContent(TOTAL_ORDER_PRICE_PROMPT, content);
    }

    public void printGiftMenu(GiftMenuDto giftMenuDto) {
        Map<String, Integer> giftMenus = giftMenuDto.getGiftMenu();

        if (giftMenus.isEmpty()) {
            printPromptWithContent(GIFT_MENU_PROMPT, "없음");
            return;
        }

        String content = giftMenus.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining(LINE_SEPARATOR));

        printPromptWithContent(GIFT_MENU_PROMPT, content);
    }

    public void printBenefitDetails(BenefitDetailsDto benefitDetailsDto) {
        Map<String, BigDecimal> detailsDetails = benefitDetailsDto.getDetails();

        if (detailsDetails.isEmpty()) {
            printPromptWithContent(BENEFIT_DETAILS_PROMPT, "없음");
            return;
        }

        String content = detailsDetails.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + formatMinusMoney(entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));

        printPromptWithContent(BENEFIT_DETAILS_PROMPT, content);
    }


    public void printTotalBenefitAmounts(MoneyDto totalBenefitAmounts) {
        BigDecimal totalBenefitAmountsMoney = totalBenefitAmounts.getMoney();

        if (totalBenefitAmountsMoney.equals(BigDecimal.ZERO)) {
            printPromptWithContent(TOTAL_BENEFIT_AMOUNTS_PROMPT, "없음");
            return;
        }

        String content = formatMinusMoney(totalBenefitAmounts.getMoney());
        printPromptWithContent(TOTAL_BENEFIT_AMOUNTS_PROMPT, content);
    }

    public void printExpectedPayment(MoneyDto expectedPayment) {
        String content = formatMoney(expectedPayment.getMoney());
        printPromptWithContent(EXPECTED_PAYMENT_PROMPT, content);
    }

    public void printEventBadge(EventBadgeDto eventBadgeDto) {
        String content = eventBadgeDto.getBadgeName();
        printPromptWithContent(EVENT_BADGE_PROMPT, content);
    }

    public void printErrorMessage(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

    public void printEmptyLine() {
        System.out.println();
    }

    private void printPromptWithContent(String prompt, String content) {
        StringBuilder line = new StringBuilder(prompt);
        line.append(LINE_SEPARATOR)
                .append(content)
                .append(LINE_SEPARATOR);
        System.out.println(line);
    }

    private String formatMoney(BigDecimal money) {
        return String.format("%,.0f원", money);
    }

    private String formatMinusMoney(BigDecimal money) {
        return String.format("-%,.0f원", money);
    }
}
