package com.gildedrose.webapp.service;

import com.gildedrose.webapp.model.Item;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GildedRoseService {

    public List<Item> updateInventory(List<Item> items) {
        if (items == null || items.isEmpty()) {
            return List.of();
        }

        Item[] inventory = items.stream()
                .map(Item::copy)
                .toArray(Item[]::new);

        GildedRose gildedRose = new GildedRose(inventory);
        gildedRose.updateQuality();

        return Arrays.asList(inventory);
    }
}
