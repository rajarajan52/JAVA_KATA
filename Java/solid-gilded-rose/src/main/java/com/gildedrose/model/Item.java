package com.gildedrose.model;

import com.gildedrose.domain.QualityLimits;

import java.util.Objects;

public final class Item {
    private final String name;
    private int sellIn;
    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void decreaseSellIn() {
        sellIn--;
    }

    public void decreaseQuality(int amount) {
        quality = Math.max(QualityLimits.MIN_QUALITY, quality - amount);
    }

    public void increaseQuality(int amount) {
        quality = Math.min(QualityLimits.MAX_QUALITY, quality + amount);
    }

    public void resetQuality(int newQuality) {
        quality = newQuality;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return name + ", " + sellIn + ", " + quality;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Item item)) {
            return false;
        }
        return sellIn == item.sellIn && quality == item.quality && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality);
    }
}
