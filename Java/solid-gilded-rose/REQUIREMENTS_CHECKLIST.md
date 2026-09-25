# Gilded Rose Requirements Check

This project satisfies the specification in `GildedRoseRequirements.md`.

## Requirement mapping

- Standard items reduce `Quality` by 1 before sell by date, by 2 after: implemented in `NormalItemStrategy`
- Quality never drops below 0: enforced in `Item.decreaseQuality`
- Aged Brie increases in quality with age: implemented in `AgedBrieStrategy`
- Quality never exceeds 50: enforced with `QualityLimits.MAX_QUALITY` and `Item.increaseQuality`
- Sulfuras are legendary and never change: implemented in `SulfurasStrategy`
- Backstage passes increase by 1, +2 at <=10 days, +3 at <=5 days, then 0 after concert: implemented in `BackstagePassStrategy`
- Conjured items degrade twice as fast: implemented in `ConjuredItemStrategy`
- Sell-in decreases for all non-legendary items: handled in each strategy via `decreaseSellIn()`
- Sulfuras quality remains 80: enforced in `SulfurasStrategy`

## Validation

- JUnit 5 tests cover all item types and rule edge cases
- JaCoCo enforces 100% line and branch coverage for the project
- `mvn clean verify` passes on JDK 17
