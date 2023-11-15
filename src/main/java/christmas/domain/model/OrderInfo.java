package christmas.domain.model;

public class OrderInfo {

    private final Order order;
    private final December date;

    public OrderInfo(Order order, December date) {
        this.order = order;
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public December getDate() {
        return date;
    }

}
