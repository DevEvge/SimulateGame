package org.game.service;

import lombok.RequiredArgsConstructor;
import org.game.config.ItemsConfig;
import org.game.model.Item;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemsConfig itemsConfig;

    public Item getItem(String id) {
        var props = itemsConfig.getItems().get(id);
        if (props == null) {
            throw new IllegalArgumentException("CRITICAL: Config not found for item: " + id);
        }

        return Item.builder()
                .id(id)
                .name(props.getName())
                .type(props.getType())
                .slot(props.getSlot())
                .price(props.getPrice())
                .minDamage(props.getMinDamage())
                .maxDamage(props.getMaxDamage())
                .critChance(props.getCritChance())
                .defense(props.getDefense())
                .healAmount(props.getHealAmount())
                .build();
    }
}
