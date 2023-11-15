package christmas.domain.model;

import java.math.BigDecimal;
import java.util.Optional;

public class OrderItem {
    private final Menu menu;
    private final OrderQuantity quantity;

    private OrderItem(Menu menu, OrderQuantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem of(String menuName, Integer quantity) {
        Menu menu = findMenuByName(menuName);
        OrderQuantity orderQuantity = OrderQuantity.from(quantity);
        return new OrderItem(menu, orderQuantity);
    }

    public Money calculatePrice() {
        return menu.getPrice().multiply(Money.from(quantity.getQuantity()));
    }

    private static Menu findMenuByName(String menuName) {
        Optional<Menu> optionalMenu = Menu.findMenuByName(menuName);

        if (optionalMenu.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        return optionalMenu.get();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public String getMenuName() {
        return menu.getName();
    }
}
