package christmas.dto;

public class OrderRequestDto {
    private final String menuName;
    private final int quantity;

    public OrderRequestDto(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderRequestDto{" +
                "menuName='" + menuName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
