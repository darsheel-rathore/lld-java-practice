# LLD Java Practice 🏗️

A collection of 50 Low Level Design (LLD) projects built in Java 
for interview preparation. Each project follows clean OOP principles, 
SOLID design, and real interview-grade extensibility.

## Structure
Each project lives in its own package under `src/` with consistent structure:
- `models/` — core entities
- `strategies/` — swappable logic
- `exceptions/` — custom exceptions
- `Game.java` / `Machine.java` — orchestrator
- `Main.java` — driver with 3 interview scenarios

## Projects

| # | Project | Patterns Used | Status |
|---|---------|--------------|--------|
| 1 | Tic-Tac-Toe | Strategy, Builder, Polymorphism | ✅ Done |
| 2 | Snake and Ladder | | ⏳ Pending |
| 3 | Vending Machine | | ⏳ Pending |
| 4 | ATM | | ⏳ Pending |
| 5 | Parking Lot | | ⏳ Pending |
| 6 | Elevator System | | ⏳ Pending |
| 7 | Traffic Light System | | ⏳ Pending |
| 8 | Library Management | | ⏳ Pending |
| 9 | Cache System (LRU) | | ⏳ Pending |
| 10 | Logging Framework | | ⏳ Pending |

> More projects added weekly.

## Key Design Principles Followed
- SOLID principles throughout
- Strategy pattern for swappable logic
- Builder pattern for complex object creation
- Polymorphism over if-else chains
- Interview-style driver code (no Scanner, hardcoded scenarios)

## Tech
- Java 21
- IntelliJ IDEA
- No external dependencies
