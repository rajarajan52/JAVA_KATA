package com.gildedrose.quality;

import com.gildedrose.model.Item;

public final class SulfurasStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        item.resetQuality(80);
    }
}
