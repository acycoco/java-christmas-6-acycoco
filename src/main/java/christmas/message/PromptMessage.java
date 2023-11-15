package christmas.message;

public enum PromptMessage {

    READ_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    READ_ORDER_REQUESTS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_PRICE("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNTS("<총혜택 금액>"),
    EXPECTED_PAYMENT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");

    private final String prompt;

    PromptMessage(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return this.prompt;
    }
}
