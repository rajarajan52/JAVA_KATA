package com.gildedrose.service;

import com.gildedrose.domain.ItemType;
import com.gildedrose.model.Item;

import java.util.Objects;

public final class GildedRose {
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = Objects.requireNonNull(items, "items must not be null");
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item == null) {
                continue;
            }

            ItemType.fromName(item.getName()).getStrategy().update(item);
        }
    }

    public Item[] getItems() {
        return items.clone();
    }
}
