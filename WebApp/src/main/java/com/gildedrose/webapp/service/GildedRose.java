package com.gildedrose.webapp.service;

import com.gildedrose.webapp.domain.ItemType;
import com.gildedrose.webapp.model.Item;

import java.util.Arrays;
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
        return Arrays.stream(items)
                .map(item -> item == null ? null : item.copy())
                .toArray(Item[]::new);
    }
}
