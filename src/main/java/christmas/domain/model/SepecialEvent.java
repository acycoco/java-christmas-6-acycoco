package christmas.domain.model;

public class SepecialEvent implements Event{

    private static final int SPECIAL_DISCOUNT = 1000;

    private final OrderInfo orderInfo;

    public SepecialEvent(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    @Override
    public boolean canDiscount() {
        return orderInfo.getDate().hasStar();
    }

    @Override
    public Money discountAmount() {
        return Money.from(SPECIAL_DISCOUNT);
    }
}
