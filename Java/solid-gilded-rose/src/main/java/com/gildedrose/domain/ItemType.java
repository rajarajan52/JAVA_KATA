package com.gildedrose.domain;

import com.gildedrose.quality.AgedBrieStrategy;
import com.gildedrose.quality.BackstagePassStrategy;
import com.gildedrose.quality.ConjuredItemStrategy;
import com.gildedrose.quality.ItemQualityStrategy;
import com.gildedrose.quality.NormalItemStrategy;
import com.gildedrose.quality.SulfurasStrategy;

import java.util.Map;
import java.util.Optional;

public enum ItemType {
    NORMAL("normal", new NormalItemStrategy()),
    AGED_BRIE("Aged Brie", new AgedBrieStrategy()),
    SULFURAS("Sulfuras, Hand of Ragnaros", new SulfurasStrategy()),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert", new BackstagePassStrategy()),
    CONJURED("Conjured", new ConjuredItemStrategy());

    private static final Map<String, ItemType> LOOKUP = Map.of(
            "Aged Brie", AGED_BRIE,
            "Sulfuras, Hand of Ragnaros", SULFURAS,
            "Backstage passes to a TAFKAL80ETC concert", BACKSTAGE_PASS,
            "Conjured", CONJURED
    );

    private final String name;
    private final ItemQualityStrategy strategy;

    ItemType(String name, ItemQualityStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public ItemQualityStrategy getStrategy() {
        return strategy;
    }

    public boolean isLegendary() {
        return this == SULFURAS;
    }

    public static ItemType fromName(String name) {
        return Optional.ofNullable(name)
                .map(LOOKUP::get)
                .orElse(NORMAL);
    }
}
