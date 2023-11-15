package christmas.dto;

import java.util.Collections;
import java.util.Map;

public class GiftMenuDto {

    private final Map<String, Integer> giftMenu;

    public GiftMenuDto(Map<String, Integer> giftMenu) {
        this.giftMenu = giftMenu;
    }

    public Map<String, Integer> getGiftMenu() {
        return Collections.unmodifiableMap(giftMenu);
    }
}
