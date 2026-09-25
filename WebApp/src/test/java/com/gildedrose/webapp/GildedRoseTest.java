package com.gildedrose.webapp;

import com.gildedrose.webapp.model.Item;
import com.gildedrose.webapp.service.GildedRose;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void updateQualityIgnoresNullItemsAndUpdatesRest() {
        Item[] items = {null, new Item("foo", 10, 20)};

        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        assertThat(gildedRose.getItems()[1].getQuality()).isEqualTo(19);
    }

    @Test
    void getItemsReturnsClone() {
        Item[] items = {new Item("foo", 5, 10)};

        GildedRose gildedRose = new GildedRose(items);
        Item[] clone = gildedRose.getItems();
        clone[0].setQuality(99);

        assertThat(gildedRose.getItems()[0].getQuality()).isEqualTo(10);
    }
}
