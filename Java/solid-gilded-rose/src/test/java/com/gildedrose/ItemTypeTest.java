package com.gildedrose;

import com.gildedrose.domain.ItemType;
import com.gildedrose.domain.QualityLimits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemTypeTest {

    @Test
    void shouldResolveKnownItemTypeNames() {
        assertEquals(ItemType.AGED_BRIE, ItemType.fromName("Aged Brie"));
        assertEquals(ItemType.SULFURAS, ItemType.fromName("Sulfuras, Hand of Ragnaros"));
        assertEquals(ItemType.BACKSTAGE_PASS, ItemType.fromName("Backstage passes to a TAFKAL80ETC concert"));
        assertEquals(ItemType.CONJURED, ItemType.fromName("Conjured"));
    }

    @Test
    void shouldFallbackToNormalForUnknownItemNames() {
        assertEquals(ItemType.NORMAL, ItemType.fromName("Unknown Item"));
        assertEquals(ItemType.NORMAL, ItemType.fromName(null));
    }

    @Test
    void shouldExposeLegendaryClassificationAndQualityLimits() {
        assertTrue(ItemType.SULFURAS.isLegendary());
        assertFalse(ItemType.AGED_BRIE.isLegendary());
        assertEquals("Aged Brie", ItemType.AGED_BRIE.getName());
        assertNotNull(ItemType.AGED_BRIE.getStrategy());
        assertEquals(0, QualityLimits.MIN_QUALITY);
        assertEquals(50, QualityLimits.MAX_QUALITY);
        assertEquals(80, QualityLimits.LEGENDARY_QUALITY);
        assertEquals(12, QualityLimits.clamp(12, 0, 50));
        assertEquals(50, QualityLimits.clamp(100, 0, 50));
        assertEquals(0, QualityLimits.clamp(-50, 0, 50));
    }

    @Test
    void shouldSupportItemEqualityAndMutators() {
        com.gildedrose.model.Item original = new com.gildedrose.model.Item("Aged Brie", 2, 5);
        com.gildedrose.model.Item same = new com.gildedrose.model.Item("Aged Brie", 2, 5);
        com.gildedrose.model.Item different = new com.gildedrose.model.Item("Aged Brie", 3, 5);
        com.gildedrose.model.Item differentQuality = new com.gildedrose.model.Item("Aged Brie", 2, 9);
        com.gildedrose.model.Item differentName = new com.gildedrose.model.Item("Other Item", 2, 5);

        assertEquals(original, original);
        assertEquals(original, same);
        assertEquals(original.hashCode(), same.hashCode());
        assertNotEquals(original, different);
        assertNotEquals(original, differentQuality);
        assertNotEquals(original, differentName);
        assertNotEquals(original, null);
        assertNotEquals(original, "not an item");

        original.decreaseQuality(1);
        original.increaseQuality(10);
        original.setQuality(27);
        original.setSellIn(4);

        assertEquals(27, original.getQuality());
        assertEquals(4, original.getSellIn());
        assertEquals("Aged Brie, 4, 27", original.toString());
    }
}
