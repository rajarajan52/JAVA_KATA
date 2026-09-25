package com.gildedrose.quality;

import com.gildedrose.model.Item;

public final class NormalItemStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        int degradation = item.getSellIn() > 0 ? 1 : 2;
        item.decreaseQuality(degradation);
        item.decreaseSellIn();
    }
}
