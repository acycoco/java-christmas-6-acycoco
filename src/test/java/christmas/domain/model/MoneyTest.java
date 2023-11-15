package christmas.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class MoneyTest {

    private Money money1;
    private Money money2;

    @BeforeEach
    void setUp() {
        money1 = Money.from(1000);
        money2 = Money.from(2000);
    }

    @DisplayName("금액이 0이면 에러를 발생시킨다.")
    @Test
    void createFail() {
        //when & then
        assertThatThrownBy(() -> Money.from(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Money두개를 더한 결과가 일치한다.")
    @Test
    void addMoney() {
        //when
        Money addedmoney = money1.add(money2);

        //then
        assertThat(addedmoney.getAmount())
                .isEqualTo(BigDecimal.valueOf(3000));
    }

    @DisplayName("Money두개를 뺀 값이 정확하다.")
    @Test
    void subtractMoneySuccess() {
        //when
        Money substractedMoney = money2.subtract(money1);

        //then
        assertThat(substractedMoney.getAmount())
                .isEqualTo(BigDecimal.valueOf(1000));
    }

    @DisplayName("뺀값이 더 크면 에러가 발생한다.")
    @Test
    void subtractMoneyFail() {
        //when & then
        assertThatThrownBy(() -> money1.subtract(money2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Money두개를 곱한 결과가 일치한다.")
    @Test
    void multiply() {
        //when
        Money multipliedMoney = money1.multiply(money2);

        //then
        assertThat(multipliedMoney.getAmount())
                .isEqualTo(BigDecimal.valueOf(2000000));
    }

    @DisplayName("금액이 같으면 같은 동등성을 가진다.")
    @Test
    void testEquals() {
        //given
        Money money3 = Money.from(1000);

        //when & then
        assertThat(money1.equals(money3))
                .isEqualTo(true);
    }

}