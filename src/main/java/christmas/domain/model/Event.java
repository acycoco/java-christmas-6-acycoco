package christmas.domain.model;

public interface Event {

    boolean canDiscount(OrderInfo orderInfo);

    Money discountAmount(OrderInfo orderInfo);
}
