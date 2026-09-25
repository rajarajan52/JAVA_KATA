package com.gildedrose;

import com.gildedrose.model.Item;
import com.gildedrose.service.GildedRose;

public final class TexttestFixture {
    private TexttestFixture() {
    }

    public static void main(String[] args) {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured", 3, 6),
            new Item("Conjured", 0, 20)
        };

        GildedRose gildedRose = new GildedRose(items);

        for (int day = 1; day <= 30; day++) {
            System.out.println("-------- day " + day + " --------");
            for (Item item : items) {
                System.out.println(item);
            }
            gildedRose.updateQuality();
        }
    }
}
