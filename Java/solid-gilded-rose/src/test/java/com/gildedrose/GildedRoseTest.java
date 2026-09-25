package com.gildedrose;

import com.gildedrose.domain.ItemType;
import com.gildedrose.model.Item;
import com.gildedrose.service.GildedRose;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normalItemDegradesByOneBeforeSellDate() {
        Item[] items = {new Item("+5 Dexterity Vest", 10, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(9, items[0].getSellIn());
        assertEquals(19, items[0].getQuality());
    }

    @Test
    void normalItemDegradesByTwoAfterSellDate() {
        Item[] items = {new Item("Normal Item", 0, 6)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(-1, items[0].getSellIn());
        assertEquals(4, items[0].getQuality());
    }

    @Test
    void agedBrieIncreasesInQuality() {
        Item[] items = {new Item("Aged Brie", 2, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(1, items[0].getSellIn());
        assertEquals(1, items[0].getQuality());
    }

    @Test
    void agedBrieIncreasesFasterAfterExpiry() {
        Item[] items = {new Item("Aged Brie", 0, 40)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(-1, items[0].getSellIn());
        assertEquals(42, items[0].getQuality());
    }

    @Test
    void qualityNeverExceedsFiftyForAgedBrie() {
        Item[] items = {new Item("Aged Brie", 5, 49)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(50, items[0].getQuality());
    }

    @Test
    void sulfurasNeverChanges() {
        Item[] items = {new Item("Sulfuras, Hand of Ragnaros", 0, 80)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, items[0].getSellIn());
        assertEquals(80, items[0].getQuality());
    }

    @Test
    void backstagePassesIncreaseByOneBeforeTheThreshold() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(14, items[0].getSellIn());
        assertEquals(21, items[0].getQuality());
    }

    @Test
    void backstagePassesIncreaseByTwoAtTenDaysOrLess() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(9, items[0].getSellIn());
        assertEquals(22, items[0].getQuality());
    }

    @Test
    void backstagePassesIncreaseByThreeAtFiveDaysOrLess() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(4, items[0].getSellIn());
        assertEquals(23, items[0].getQuality());
    }

    @Test
    void backstagePassesDropToZeroAfterTheConcert() {
        Item[] items = {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(-1, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    void conjuredItemsDegradeTwiceAsFast() {
        Item[] items = {new Item("Conjured", 3, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(2, items[0].getSellIn());
        assertEquals(8, items[0].getQuality());
    }

    @Test
    void conjuredItemsDegradeFourTimesAfterSellDate() {
        Item[] items = {new Item("Conjured", 0, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(-1, items[0].getSellIn());
        assertEquals(6, items[0].getQuality());
    }

    @Test
    void qualityNeverFallsBelowZero() {
        Item[] items = {new Item("Normal Item", 0, 0)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(0, items[0].getQuality());
    }

    @Test
    void skipsNullItemsWhenUpdatingQuality() {
        Item[] items = {null, new Item("Normal Item", 5, 10)};

        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(4, items[1].getSellIn());
        assertEquals(9, items[1].getQuality());
    }

    @Test
    void exposesInventoryMetadataAndRunsFixture() {
        Item item = new Item("Aged Brie", 2, 3);
        item.setSellIn(7);

        GildedRose app = new GildedRose(new Item[]{item});
        Item[] snapshot = app.getItems();

        assertEquals(ItemType.AGED_BRIE.getName(), item.getName());
        assertEquals("Aged Brie, 7, 3", item.toString());
        assertEquals(1, snapshot.length);
        assertEquals("Aged Brie", snapshot[0].getName());

        TexttestFixture.main(new String[0]);
    }
}
