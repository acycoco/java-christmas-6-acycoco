package christmas.domain.model;

public class OrderRequest {
    private final String menuName;
    private final int quantity;

    public OrderRequest(String menuName, int quantity) {
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
        return "OrderRequest{" +
                "menuName='" + menuName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
