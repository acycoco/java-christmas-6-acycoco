package christmas.view;

import christmas.dto.BenefitDetailsDto;
import christmas.dto.GiftMenuDto;
import christmas.dto.MoneyDto;
import christmas.dto.OrderDto;
import christmas.dto.EventBadgeDto;
import christmas.message.ErrorMessage;
import christmas.message.OutputMessage;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;



public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printOrderMenu(OrderDto orderDto) {
        Map<String, Integer> orderItems = orderDto.getOrderItems();

        String content = orderItems.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining(LINE_SEPARATOR));

        printPromptWithContent(OutputMessage.ORDER_MENU, content);
    }

    public void printTotalOrderPrice(MoneyDto totalOrderPrice) {
        String content = formatMoney(totalOrderPrice.getMoney());
        printPromptWithContent(OutputMessage.TOTAL_ORDER_PRICE, content);
    }

    public void printGiftMenu(GiftMenuDto giftMenuDto) {
        Map<String, Integer> giftMenus = giftMenuDto.getGiftMenu();

        if (giftMenus.isEmpty()) {
            printPromptWithContent(OutputMessage.GIFT_MENU, OutputMessage.NONE.getMessage());
            return;
        }

        String content = giftMenus.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining(LINE_SEPARATOR));

        printPromptWithContent(OutputMessage.GIFT_MENU, content);
    }

    public void printBenefitDetails(BenefitDetailsDto benefitDetailsDto) {
        Map<String, BigDecimal> detailsDetails = benefitDetailsDto.getDetails();

        if (detailsDetails.isEmpty()) {
            printPromptWithContent(OutputMessage.BENEFIT_DETAILS, OutputMessage.NONE.getMessage());
            return;
        }

        String content = detailsDetails.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + formatMinusMoney(entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));

        printPromptWithContent(OutputMessage.BENEFIT_DETAILS, content);
    }


    public void printTotalBenefitAmounts(MoneyDto totalBenefitAmounts) {
        BigDecimal totalBenefitAmountsMoney = totalBenefitAmounts.getMoney();

        if (totalBenefitAmountsMoney.equals(BigDecimal.ZERO)) {
            printPromptWithContent(OutputMessage.TOTAL_BENEFIT_AMOUNTS, OutputMessage.NONE.getMessage());
            return;
        }

        String content = formatMinusMoney(totalBenefitAmounts.getMoney());
        printPromptWithContent(OutputMessage.TOTAL_BENEFIT_AMOUNTS, content);
    }

    public void printExpectedPayment(MoneyDto expectedPayment) {
        String content = formatMoney(expectedPayment.getMoney());
        printPromptWithContent(OutputMessage.EXPECTED_PAYMENT, content);
    }

    public void printEventBadge(EventBadgeDto eventBadgeDto) {
        String content = eventBadgeDto.getBadgeName();
        printPromptWithContent(OutputMessage.EVENT_BADGE, content);
    }

    public void printErrorMessage(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

    public void printMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }

    public void printEmptyLine() {
        System.out.println();
    }

    private void printPromptWithContent(OutputMessage outputMessage, String content) {
        StringBuilder line = new StringBuilder(outputMessage.getMessage());
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
