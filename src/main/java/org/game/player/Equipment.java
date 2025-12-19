package org.game.player;

import lombok.Data;
import org.game.model.Item;
import org.game.model.ItemType;
import org.game.model.SlotType;
import java.util.HashMap;
import java.util.Map;

@Data
public class Equipment {
    private Map<SlotType, Item> items;

    public void equip(Item item) {
        if (item.getSlot() == SlotType.NONE) {
            System.out.println("Цей предмет не можня одягнути");
            return;
        }
        items.put(item.getSlot(), item);
        System.out.println("Предмет экіпіровано " + item.getName());
    }

    public Item getItem(SlotType slot) {
        return items.get(slot);
    }

    public boolean hasItem(SlotType slot) {
        return items.containsKey(SlotType.MAIN_HAND);
    }


}
