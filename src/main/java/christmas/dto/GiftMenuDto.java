package christmas.dto;

import christmas.domain.model.OrderItem;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GiftMenuDto {

    private final Map<String, Integer> giftMenus;

    private GiftMenuDto(Map<String, Integer> giftMenus) {
        this.giftMenus = giftMenus;
    }

    public static GiftMenuDto from(Optional<OrderItem> optionalGiftMenu) {
        Map<String, Integer> giftMenus = new HashMap<>();

        if (optionalGiftMenu.isPresent()) {
            OrderItem giftMenu = optionalGiftMenu.get();
            giftMenus.put(giftMenu.getMenuName(), giftMenu.getQuantity());
        }

        return new GiftMenuDto(giftMenus);
    }

    public Map<String, Integer> getGiftMenu() {
        return Collections.unmodifiableMap(giftMenus);
    }
}
