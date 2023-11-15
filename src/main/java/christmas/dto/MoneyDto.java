package christmas.dto;

import christmas.domain.model.Money;

import java.math.BigDecimal;

public class MoneyDto {

    private final BigDecimal money;

    private MoneyDto(BigDecimal money) {
        this.money = money;
    }

    public static MoneyDto from(Money money) {
        return new MoneyDto(money.getAmount());
    }

    public BigDecimal getMoney() {
        return money;
    }
}
