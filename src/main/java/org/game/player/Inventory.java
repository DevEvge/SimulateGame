package org.game.player;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.game.model.Item;

import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
public class Inventory {
    private final int size;
    private final Map<Integer, ItemStack> slots;

    public Inventory(int size) {
        this.size = size;
        this.slots = new HashMap<>(size);
    }

    public boolean addItem(Item item) {
        for (ItemStack stack : slots.values()) {
            if (canStack(stack, item)) {
                stack.add(1);
                log.info("Додано до стаку: {} (Всього: {})", item.getName(), stack.getQuantity());
                return true;
            }
        }

        for (int i = 0; i < size; i++) {
            if (!slots.containsKey(i)) {
                slots.put(i, new ItemStack(item, 1));
                log.info("Новий слот {}: {}", i, item.getName());
                return true;
            }
        }

        log.warn("Інвентар повний! Неможливо підібрати: {}", item.getName());
        return false;
    }

    private boolean canStack(ItemStack stack, Item item) {
        boolean sameItem = stack.getItem().getId().equals(item.getId());
        boolean notFull = !stack.isFull();

        return sameItem && notFull;
    }

    public Item removeItem(int slotIndex, int amount) {
        ItemStack stack = slots.get(slotIndex);
        if (stack == null || stack.getQuantity() < amount) return null;

        Item takenItem = stack.getItem();

        stack.setQuantity(stack.getQuantity() - amount);

        if (stack.getQuantity() == 0) {
            slots.remove(slotIndex);
        }
        return  takenItem;
    }

    public ItemStack getStack(int slotIndex) {
        return slots.get(slotIndex);
    }


}
