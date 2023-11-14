package christmas.domain.model;

public class SpecialEvent implements Event{

    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public boolean canDiscount(OrderInfo orderInfo) {
        return orderInfo.getDate().hasStar();
    }

    @Override
    public Money discountAmount(OrderInfo orderInfo) {
        return Money.from(SPECIAL_DISCOUNT);
    }
}
