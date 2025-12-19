package org.game.player;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.game.model.Item;
import org.game.model.SlotType;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Slf4j
public class Equipment {
    private Map<SlotType, Item> items = new  HashMap<>();

    public void equip(Item item) {
        if (item.getSlot() == SlotType.NONE) {
            log.warn("Нельзя надеть предмет без слота: {}", item.getName());
            return;
        }

        Item oldItem = items.put(item.getSlot(), item);

        if (oldItem != null) {
            log.info("Снято: {}, Надето: {}", oldItem.getName(), item.getName());
            //TODO: вернуть олд итем в инвентарь
        } else {
            log.info("Надет: {}", item.getName());
        }
    }

    public Optional<Item> getItem(SlotType slot) {
        return Optional.ofNullable(items.get(slot));
    }
}
