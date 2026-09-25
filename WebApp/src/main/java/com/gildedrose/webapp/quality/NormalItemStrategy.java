package com.gildedrose.webapp.quality;

import com.gildedrose.webapp.model.Item;

public final class NormalItemStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        int degradation = item.getSellIn() > 0 ? 1 : 2;
        item.decreaseQuality(degradation);
        item.decreaseSellIn();
    }
}
