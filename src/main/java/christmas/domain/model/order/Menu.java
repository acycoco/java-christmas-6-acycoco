package christmas.domain.model.order;

import christmas.domain.model.Money;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", Money.from(6000), MenuCategory.APPETIZER),
    TAPAS("타파스", Money.from(5500), MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", Money.from(8000), MenuCategory.APPETIZER),

    T_BONE_STEAK("티본스테이크", Money.from(55000), MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", Money.from(54000), MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", Money.from(35000), MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", Money.from(25000), MenuCategory.MAIN),

    CHOCOLATE_CAKE("초코케이크", Money.from(15000), MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", Money.from(5000), MenuCategory.DESSERT),

    ZERO_COLA("제로콜라", Money.from(3000), MenuCategory.BEVERAGE),
    RED_WINE("레드와인", Money.from(60000), MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", Money.from(25000), MenuCategory.BEVERAGE);

    private final String name;
    private final Money price;
    private final MenuCategory menuCategory;

    Menu(String name, Money price, MenuCategory menuCategory) {
        this.name = name;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    public static Optional<Menu> findMenuByName(String targetName) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(targetName))
                .findFirst();
    }

    public boolean isDessert() {
        return this.menuCategory == MenuCategory.DESSERT;
    }

    public boolean isMain() {
        return this.menuCategory == MenuCategory.MAIN;
    }

    public boolean isBeverage() {
        return this.menuCategory == MenuCategory.BEVERAGE;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return menuCategory;
    }
}

