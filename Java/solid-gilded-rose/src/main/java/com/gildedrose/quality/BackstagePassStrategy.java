package com.gildedrose.quality;

import com.gildedrose.model.Item;

public final class BackstagePassStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        if (item.getSellIn() > 0) {
            int increase = 1;
            if (item.getSellIn() <= 10) {
                increase++;
            }
            if (item.getSellIn() <= 5) {
                increase++;
            }

            item.increaseQuality(increase);
        }

        item.decreaseSellIn();

        if (item.getSellIn() < 0) {
            item.resetQuality(0);
        }
    }
}
