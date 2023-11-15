package christmas.dto;

import christmas.domain.model.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class GiftMenuDtoTest {

    @DisplayName("증정메뉴가 있을 때 증정메뉴 dto객체 생성에 성공한다.")
    @Test
    void createGiftDtoFromOrderItemIfExists() {
        Optional<OrderItem> giftMenu = Optional.of(OrderItem.of("샴페인", 1));

        GiftMenuDto giftMenuDto = GiftMenuDto.from(giftMenu);
        Map<String, Integer> giftMenus = giftMenuDto.getGiftMenu();

        //then
        assertThat(giftMenus.size())
                .isEqualTo(1);
        assertThat(giftMenus.get("샴페인"))
                .isEqualTo(1);
    }

    @DisplayName("증정메뉴가 없을 때 증정메뉴 dto객체 생성에 성공한다.")
    @Test
    void createGiftDtoFromOrderItemIfNotExists() {
        Optional<OrderItem> giftMenu = Optional.empty();

        GiftMenuDto giftMenuDto = GiftMenuDto.from(giftMenu);

        //then
        assertThat(giftMenuDto.getGiftMenu())
                .isEmpty();
    }
}