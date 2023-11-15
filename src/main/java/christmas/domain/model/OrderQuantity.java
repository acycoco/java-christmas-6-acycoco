package christmas.domain.model;

import java.util.Objects;

public class OrderQuantity {

    private final int quantity;

    private static final int MIN_ORDER_QUANTITY = 1;
    private OrderQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static OrderQuantity from(int quantity) {
        validateRange(quantity);
        return new OrderQuantity(quantity);
    }

    public static void validateRange(int quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderQuantity that)) {
            return false;
        }
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    public int getQuantity() {
        return quantity;
    }
}
