package com.gildedrose.webapp;

import com.gildedrose.webapp.domain.ItemType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTypeTest {

    @Test
    void resolvesAgedBrieType() {
        assertThat(ItemType.fromName("Aged Brie")).isEqualTo(ItemType.AGED_BRIE);
    }

    @Test
    void resolvesBackstagePassType() {
        assertThat(ItemType.fromName("Backstage passes to a TAFKAL80ETC concert")).isEqualTo(ItemType.BACKSTAGE_PASS);
    }

    @Test
    void resolvesConjuredType() {
        assertThat(ItemType.fromName("Conjured")).isEqualTo(ItemType.CONJURED);
    }

    @Test
    void resolvesSulfurasType() {
        assertThat(ItemType.fromName("Sulfuras, Hand of Ragnaros")).isEqualTo(ItemType.SULFURAS);
    }

    @Test
    void defaultsUnknownItemsToNormal() {
        assertThat(ItemType.fromName("Unknown Item")).isEqualTo(ItemType.NORMAL);
        assertThat(ItemType.fromName(null)).isEqualTo(ItemType.NORMAL);
    }

    @Test
    void identifiesLegendaryType() {
        assertThat(ItemType.SULFURAS.isLegendary()).isTrue();
        assertThat(ItemType.AGED_BRIE.isLegendary()).isFalse();
        assertThat(ItemType.SULFURAS.getName()).isEqualTo("Sulfuras, Hand of Ragnaros");
        assertThat(ItemType.NORMAL.getStrategy()).isNotNull();
    }
}
