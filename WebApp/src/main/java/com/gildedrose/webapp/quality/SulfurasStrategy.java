package com.gildedrose.webapp.quality;

import com.gildedrose.webapp.domain.QualityLimits;
import com.gildedrose.webapp.model.Item;

public final class SulfurasStrategy implements ItemQualityStrategy {
    @Override
    public void update(Item item) {
        item.resetQuality(QualityLimits.SULFURAS_QUALITY);
    }
}
