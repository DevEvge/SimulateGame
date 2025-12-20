package org.game.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.game.model.Item;

@Data
@AllArgsConstructor
public class ItemStack {
    private Item item;
    private int quantity;

    public void add(int amount) {
        this.quantity += amount;
    }

    public boolean isFull() {
        return quantity >= item.getMaxStack();
    }

    // Скільки ще можна докласти в цей стак
    public int getSpace() {
        return item.getMaxStack() - quantity;
    }
}
