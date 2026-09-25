package com.gildedrose.webapp;

import com.gildedrose.webapp.model.Item;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {

    @Test
    void itemMutationAndCopyWorkCorrectly() {
        Item item = new Item("Test Item", 5, 10);

        item.decreaseSellIn();
        item.decreaseQuality(3);
        item.increaseQuality(2);
        item.resetQuality(7);

        assertThat(item.getSellIn()).isEqualTo(4);
        assertThat(item.getQuality()).isEqualTo(7);
        assertThat(item.copy()).isEqualTo(new Item("Test Item", 4, 7));
    }

    @Test
    void qualityBoundsAndEqualityWork() {
        Item first = new Item("Test Item", 5, 10);
        Item second = new Item("Test Item", 5, 10);
        Item different = new Item("Other", 5, 10);

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
        assertThat(first.equals(null)).isFalse();
        assertThat(first.equals("not an item")).isFalse();
        assertThat(first).isNotEqualTo(different);

        first.decreaseQuality(100);
        first.increaseQuality(100);

        assertThat(first.getQuality()).isEqualTo(50);
    }
}
