package com.gildedrose.webapp.controller;

import com.gildedrose.webapp.model.Item;
import com.gildedrose.webapp.service.GildedRoseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InventoryController {

    private final GildedRoseService gildedRoseService;

    public InventoryController(GildedRoseService gildedRoseService) {
        this.gildedRoseService = gildedRoseService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "gilded-rose-webapp");
    }

    @GetMapping("/items/demo")
    public List<Item> demoInventory() {
        return List.of(
                new Item("Aged Brie", 2, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Conjured", 3, 6),
                new Item("+5 Dexterity Vest", 10, 20)
        );
    }

    @PostMapping("/items/update")
    public ResponseEntity<List<Item>> updateInventory(@Valid @RequestBody List<Item> items) {
        return ResponseEntity.ok(gildedRoseService.updateInventory(items));
    }
}
