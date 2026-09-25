# Gilded Rose Java Project Instruction Guide

## 1. Project purpose
This project is a clean Java implementation of the Gilded Rose kata. The business goal is to manage a shop inventory where each item has a `sellIn` value and a `quality` value, and every day the system updates the inventory according to strict business rules.

The implementation follows modern Java standards and the SOLID design principle by separating item behavior into strategies instead of hard-coding each rule inside one giant method.

---

## 2. Business information: step-by-step

### Step 1: Inventory is loaded
The system receives an array of items, each item containing:
- `name`
- `sellIn`
- `quality`

Example:
- `Aged Brie, 2, 0`
- `Backstage passes to a TAFKAL80ETC concert, 15, 20`
- `Sulfuras, Hand of Ragnaros, 0, 80`

### Step 2: Each item is classified
The code checks the item name and maps it to a known item type using `ItemType.fromName(...)`.

Supported item categories in this project:
- Normal item
- Aged Brie
- Sulfuras
- Backstage passes
- Conjured item

### Step 3: The corresponding behavior is selected
Each item type owns a dedicated strategy class:
- `NormalItemStrategy`
- `AgedBrieStrategy`
- `SulfurasStrategy`
- `BackstagePassStrategy`
- `ConjuredItemStrategy`

The system does not use a large if/else chain. Instead, it calls the relevant strategy object.

### Step 4: Business rules are applied
The project applies the standard Gilded Rose rules every day:

#### 4.1 Normal items
- Quality decreases by 1 when sell date is still in future
- Quality decreases by 2 when sell date has passed
- SellIn decreases by 1
- Quality cannot go below 0

#### 4.2 Aged Brie
- Quality increases by 1 before sell date
- Quality increases by 2 after sell date
- SellIn decreases by 1
- Quality cannot exceed 50

#### 4.3 Sulfuras
- Sulfuras are legendary items
- They never lose quality
- They never change sellIn
- Sulfuras quality stays at 80

#### 4.4 Backstage passes
- Quality increases by 1 when sellIn > 10
- Quality increases by 2 when sellIn <= 10
- Quality increases by 3 when sellIn <= 5
- After the concert, quality becomes 0
- SellIn decreases by 1

#### 4.5 Conjured items
- Quality decreases twice as fast as normal items
- If sellIn > 0, decrease by 2
- If sellIn <= 0, decrease by 4
- SellIn decreases by 1

### Step 5: Quality boundaries are enforced
The system applies min/max rules through `Item` methods:
- `decreaseQuality(int amount)` keeps quality above 0
- `increaseQuality(int amount)` keeps quality below 50
- `resetQuality(int newQuality)` can set fixed values like Sulfuras or post-concert quality to zero

### Step 6: The daily inventory update runs
The main update entry point is:
- `GildedRose.updateQuality()`

This method loops over the inventory and applies the strategy to each item.

---

## 3. Code-by-code explanation

### `pom.xml`
Purpose:
- Maven project configuration
- Java version setup
- JUnit 5 dependency
- JaCoCo coverage enforcement

This project is configured to run tests and fail if coverage is below the required level.

### `README.md`
Purpose:
- Quick project summary
- Test command overview
- Instructions for users

### `REQUIREMENTS_CHECKLIST.md`
Purpose:
- Maps each Gilded Rose business rule to the Java implementation
- Shows requirement verification in one place

### `src/main/java/com/gildedrose/model/Item.java`
Purpose:
- Represents each inventory item
- Keeps the properties `name`, `sellIn`, and `quality`
- Provides mutation methods for:
  - reducing quality
  - increasing quality
  - decreasing sellIn
  - resetting quality
- Implements `equals()` and `hashCode()` for test reliability

### `src/main/java/com/gildedrose/domain/QualityLimits.java`
Purpose:
- Centralizes quality boundaries
- Defines minimum and maximum quality values
- Prevents invalid values from being created by business logic

### `src/main/java/com/gildedrose/domain/ItemType.java`
Purpose:
- Enumerates all known item categories
- Maps names to rule strategies
- Identifies legendary items like Sulfuras
- Uses `fromName(...)` to resolve behavior cleanly

### `src/main/java/com/gildedrose/quality/ItemQualityStrategy.java`
Purpose:
- Defines the contract for all item update rules
- Makes the project extensible and follows the Strategy pattern

### `src/main/java/com/gildedrose/quality/NormalItemStrategy.java`
Purpose:
- Implements rule logic for standard items
- Applies normal degradation before and after sell date

### `src/main/java/com/gildedrose/quality/AgedBrieStrategy.java`
Purpose:
- Implements quality gain logic for Aged Brie

### `src/main/java/com/gildedrose/quality/SulfurasStrategy.java`
Purpose:
- Keeps Sulfuras fixed at 80 quality
- Does not change sellIn

### `src/main/java/com/gildedrose/quality/BackstagePassStrategy.java`
Purpose:
- Implements the special logic for backstage passes
- Applies tiered quality increases based on remaining days
- Resets quality to 0 after the event date

### `src/main/java/com/gildedrose/quality/ConjuredItemStrategy.java`
Purpose:
- Implements the double-speed degradation rule for conjured items

### `src/main/java/com/gildedrose/service/GildedRose.java`
Purpose:
- Main service class for inventory updates
- Accepts the array of items
- Loops through every item and applies the selected strategy

### `src/main/java/com/gildedrose/TexttestFixture.java`
Purpose:
- A runnable text-based sample entry point for quick demonstration
- Used to simulate inventory behavior in a console-friendly format

### `src/test/java/com/gildedrose/GildedRoseTest.java`
Purpose:
- Validates the real business behavior of the inventory system
- Covers normal items, Aged Brie, Sulfuras, Backstage passes, and Conjured items
- Ensures updates match the expected specification

### `src/test/java/com/gildedrose/ItemTypeTest.java`
Purpose:
- Verifies item type resolution and rule mapping
- Confirms legendary detection and default behavior

---

## 4. Design principles used
This project intentionally follows strong object-oriented and clean-code practices:

### Single Responsibility Principle (SRP)
Each class has one clear responsibility:
- `Item` stores data
- `ItemType` maps names to behaviors
- each strategy handles one business rule set

### Open/Closed Principle (OCP)
New item behavior can be added without changing the whole system. You add a new strategy and register it in `ItemType`.

### Liskov Substitution Principle (LSP)
All strategies satisfy the same `ItemQualityStrategy` contract, so they can be used interchangeably.

### Interface Segregation Principle (ISP)
The interface is small and focused: only `update(Item item)` is required.

### Dependency Inversion Principle (DIP)
The service depends on the strategy interface rather than concrete implementations.

---

## 5. How the project works in a real business flow

1. An inventory list is created.
2. The list is passed to `new GildedRose(items)`.
3. `updateQuality()` is called.
4. For each item:
   - detect item type
   - choose the matching behavior
   - update sellIn and quality according to the rule
5. The inventory is validated against business boundaries.
6. The updated inventory is returned and used in application logic.

---

## 6. How to run the project
Use Maven from the project folder:

```bash
cd Java/solid-gilded-rose
mvn clean verify
```

This command:
- compiles the project
- runs unit tests
- enforces code coverage
- confirms all rules pass

If you want to run only the tests:

```bash
mvn test
```

---

## 7. Coverage and quality assurance
This project includes JaCoCo coverage checking to ensure that code quality is monitored and maintained.

The expected outcome is:
- tests pass
- 100% line coverage
- 100% branch coverage

This helps keep the project reliable and prevents logic regressions.

---

## 8. Extension guidance
If a new item type is added in future, the standard extension pattern is:

1. Add a new item name to `ItemType`
2. Create a new strategy class implementing `ItemQualityStrategy`
3. Add a test covering the new rule
4. Update the requirement checklist if needed

This keeps the project clean and aligned with modern software architecture.

---

## 9. Summary
This project is a modern, testable, and business-rule-driven Java implementation of Gilded Rose. It keeps logic modular and maintainable while meeting the kata requirements with strict validation and coverage enforcement.

It is ideal for:
- code review
- teaching SOLID design
- Java kata refactoring practice
- production-style inventory rule management
