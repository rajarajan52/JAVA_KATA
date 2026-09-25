package com.gildedrose.webapp;

import com.gildedrose.webapp.model.Item;
import com.gildedrose.webapp.service.GildedRoseService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseServiceTest {

    private final GildedRoseService service = new GildedRoseService();

    @Test
    void updatesNormalItemBeforeSellDate() {
        var items = List.of(new Item("+5 Dexterity Vest", 10, 20));

        var result = service.updateInventory(items);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSellIn()).isEqualTo(9);
        assertThat(result.get(0).getQuality()).isEqualTo(19);
    }

    @Test
    void updatesNormalItemAfterSellDate() {
        var items = List.of(new Item("Elixir of the Mongoose", 0, 7));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(-1);
        assertThat(result.get(0).getQuality()).isEqualTo(5);
    }

    @Test
    void updatesAgedBrie() {
        var items = List.of(new Item("Aged Brie", 2, 0));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(1);
        assertThat(result.get(0).getQuality()).isEqualTo(1);
    }

    @Test
    void keepsSulfurasAtEighty() {
        var items = List.of(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(0);
        assertThat(result.get(0).getQuality()).isEqualTo(80);
    }

    @Test
    void updatesBackstagePasses() {
        var items = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(14);
        assertThat(result.get(0).getQuality()).isEqualTo(21);
    }

    @Test
    void updatesBackstagePassesAtLowerThresholds() {
        var items = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(4);
        assertThat(result.get(0).getQuality()).isEqualTo(23);
    }

    @Test
    void resetsBackstagePassesAfterConcert() {
        var items = List.of(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(-1);
        assertThat(result.get(0).getQuality()).isEqualTo(0);
    }

    @Test
    void updatesConjuredItemsTwiceAsFast() {
        var items = List.of(new Item("Conjured", 3, 10));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(2);
        assertThat(result.get(0).getQuality()).isEqualTo(8);
    }

    @Test
    void updatesExpiredConjuredItemsMoreQuickly() {
        var items = List.of(new Item("Conjured", 0, 10));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getSellIn()).isEqualTo(-1);
        assertThat(result.get(0).getQuality()).isEqualTo(6);
    }

    @Test
    void keepsQualityWithinBounds() {
        var items = List.of(new Item("Aged Brie", 0, 49));

        var result = service.updateInventory(items);

        assertThat(result.get(0).getQuality()).isEqualTo(50);
    }

    @Test
    void returnsEmptyListForNullInventory() {
        assertThat(service.updateInventory(null)).isEmpty();
    }
}
