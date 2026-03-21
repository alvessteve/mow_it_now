# Mowitnow

A lawn mowing simulation application demonstrating clean architecture and object-oriented design principles. This project showcases hexagonal architecture implementation with dependency injection and multiple design patterns.

## Overview

Mowitnow simulates autonomous lawn mowers navigating a rectangular grass field. The application processes text-based instructions to move multiple mowers across the lawn, tracking their positions and orientations.

## Architecture & Design Patterns

This project implements **Hexagonal Architecture** (Ports and Adapters) with clear separation between domain logic, application ports, and infrastructure adapters.

### Design Patterns
- **Command Pattern** - All instructions (grass creation, mow creation, movements) implement a common Instruction interface
- **Factory Pattern** - Instruction and domain object creation through dedicated factories
- **Repository Pattern** - Data access abstraction through port interfaces

### Tech Stack
- **Java** with **Lombok** for reduced boilerplate
- **Google Guice** for dependency injection
- **JUnit 5**, **Mockito**, and **AssertJ** for testing
- **Gradle** for build automation

## Usage

### Running from a file
```bash
./gradlew run --args="path/to/instructions.txt"
```

### Input Format
```
5 5              # Grass dimensions (width height)
1 2 N            # Mow position and orientation (x y N/E/S/W)
GAGAGAGAA        # Instructions (G=forward, A=turn right, D=turn left)
3 3 E            # Second mow
AADAADADDA       # Second mow instructions
```

### Building & Testing
```bash
# Build the project
./gradlew build

# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests ClassName
```

## Project Structure

```
src/main/java/
├── domain/           # Business logic and entities
│   ├── model/        # Domain models (Grass, Mow, Instructions)
│   ├── service/      # Domain services
│   └── factory/      # Factory implementations
├── ports/            # Application ports (interfaces)
├── adapters/         # External adapters (file, terminal, repositories)
└── infrastructure/   # DI configuration (Guice modules)
```

## About

Originally created as a training exercise to showcase modern Java development practices and clean architecture principles.

*Note: Original exercise specification (in French) is located in the resources folder.* 
