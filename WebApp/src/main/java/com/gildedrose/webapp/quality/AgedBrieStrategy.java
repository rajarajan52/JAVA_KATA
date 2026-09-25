package com.gildedrose.webapp.quality;

import com.gildedrose.webapp.model.Item;

public final class AgedBrieStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        int increase = item.getSellIn() > 0 ? 1 : 2;
        item.increaseQuality(increase);
        item.decreaseSellIn();
    }
}
