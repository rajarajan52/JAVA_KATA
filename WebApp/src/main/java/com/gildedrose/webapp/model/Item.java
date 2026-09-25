package com.gildedrose.webapp.model;

import com.gildedrose.webapp.domain.QualityLimits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Item {
    @NotBlank(message = "Item name is required")
    private String name;

    @Min(value = 0, message = "sellIn cannot be negative")
    private int sellIn;

    @Min(value = 0, message = "quality cannot be negative")
    private int quality;

    public Item() {
    }

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
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

    public Item copy() {
        return new Item(name, sellIn, quality);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Item item)) {
            return false;
        }
        return sellIn == item.sellIn && quality == item.quality && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }
}
