package com.gildedrose.webapp.quality;

import com.gildedrose.webapp.model.Item;

public final class ConjuredItemStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        int degradation = item.getSellIn() > 0 ? 2 : 4;
        item.decreaseQuality(degradation);
        item.decreaseSellIn();
    }
}
