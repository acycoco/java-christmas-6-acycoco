package christmas.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 음수가 될 수 없습니다.");
        }
        this.amount = amount;
    }

    public static Money from(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        if (this.amount.compareTo(other.amount) < 0) {
            throw new IllegalArgumentException("[ERROR] 빼려는 금액이 현재 금액보다 큽니다.");
        }
        return new Money(this.amount.subtract(other.amount));
    }

    public Money multiply(Money other) {
        return new Money(this.amount.multiply(other.amount));
    }

    public static Money sum(List<Money> monies) {
        BigDecimal sum = monies.stream()
                .map(Money::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Money(sum);
    }

    public boolean isGreaterThanOrEqualTo(Money other) {
        return this.amount.compareTo(other.amount) >= 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                '}';
    }
}
