# Gilded Rose WebApp

This is the REST API version of the Gilded Rose inventory project. It keeps the business logic in a clean domain/service structure while exposing the rules through a Spring Boot API.

## Features

- Spring Boot 3 REST API
- SOLID-friendly business rule design
- Strategy-based item behavior
- Validation of request payloads
- Unit and controller-level testing
- JaCoCo coverage checks

## Run

```bash
./mvnw clean verify
```

or on Windows:

```powershell
mvnw.cmd clean verify
```

## API endpoints

### Health

```http
GET /api/health
```

### Demo inventory

```http
GET /api/items/demo
```

### Update inventory

```http
POST /api/items/update
```

Example request body:

```json
[
  { "name": "Aged Brie", "sellIn": 2, "quality": 0 },
  { "name": "Backstage passes to a TAFKAL80ETC concert", "sellIn": 15, "quality": 20 },
  { "name": "Sulfuras, Hand of Ragnaros", "sellIn": 0, "quality": 80 }
]
```

## Business rules implemented

- Normal items degrade by 1 before sell date, 2 after
- Aged Brie increases in quality
- Sulfuras remain constant
- Backstage passes increase in tiers and reset to 0 after the concert
- Conjured items degrade twice as fast
- Quality remains between 0 and 50, except Sulfuras which stays at 80
