package com.gildedrose.quality;

import com.gildedrose.model.Item;

public final class AgedBrieStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        int increase = item.getSellIn() > 0 ? 1 : 2;
        item.increaseQuality(increase);
        item.decreaseSellIn();
    }
}
