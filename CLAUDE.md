# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Mowitnow is a lawn mowing simulation application built as an object-oriented design exercise. It simulates autonomous lawn mowers moving on a rectangular grass field based on text instructions.

## Build & Test Commands

```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Run a specific test class
./gradlew test --tests ClassName

# Run a specific test method
./gradlew test --tests ClassName.methodName

# Clean build artifacts
./gradlew clean

# Run the application from file
./gradlew run --args="path/to/instructions.txt"
```

## Architecture

The project follows **Hexagonal Architecture** (Ports and Adapters) with clear separation of concerns:

### Core Layers

**Domain Layer** (`domain/`)
- **model/**: Contains business entities (Grass, Mow, Coordinates, Orientation)
- **model/instructions/**: Command pattern implementation for all operations (GrassCreationInstruction, MowCreationInstruction, MoveMowInstruction, RotateLeftInstruction, RotateRightInstruction)
- **service/**: Domain services (GrassService, MowService, InstructionsTranslator)
- **factory/**: Factory pattern for creating instructions and domain objects
- **exceptions/**: Domain-specific exceptions (InstructionException, OutsideGrassMoveException, MalformedInstructionException)
- **ClientUseCase**: Main use case orchestrating the instruction execution flow

**Ports** (`ports/`)
- **Client**: Primary port defining the application entry point
- **MowRepository**: Port for mow persistence
- **GrassRepository**: Port for grass persistence
- **Displayer**: Port for output presentation

**Adapters** (`adapters/`)
- **input/InputAdapter**: Processes command-line arguments
- **file/FileAdapter**: Reads and processes instruction files
- **repositories/**: In-memory implementations (InMemoryMowRepository, InMemoryGrassRepository)
- **outputs/TerminalOutput**: Console output adapter

**Infrastructure** (`infrastructure/dependencyinversion/`)
- **MowitnowModule**: Google Guice dependency injection configuration with explicit bindings

### Design Patterns

- **Command Pattern**: All instructions (Grass creation, Mow creation, Mow movements) implement the Instruction interface
- **Factory Pattern**: InstructionFactory and MoveMowInstructionFactory create instruction instances from raw strings
- **Repository Pattern**: Abstract data access through MowRepository and GrassRepository ports

### Key Dependencies

- **Google Guice**: Dependency injection framework
- **Lombok**: Reduces boilerplate code (@Getter, @AllArgsConstructor, etc.)
- **JUnit 5**: Testing framework
- **Mockito**: Mocking framework for tests
- **AssertJ**: Fluent assertions for tests

## Input Format

The application processes text files with this structure:
```
5 5              # Grass dimensions (width height)
1 2 N            # Mow initial position and orientation (x y direction)
GAGAGAGAA        # Mow movement instructions (G=forward, A=rotate right, D=rotate left)
3 3 E            # Second mow position
AADAADADDA       # Second mow instructions
```

## Execution Flow

1. Application.main() receives file path or command-line arguments
2. Appropriate adapter (FileAdapter or InputAdapter) is injected via Guice
3. Raw instructions are passed to ClientUseCase via the Client port
4. InstructionsTranslator uses InstructionFactory to create Instruction objects
5. Each Instruction is executed (modifying state through services and repositories)
6. Final mow positions are displayed via the Displayer port

## Testing

- Test files are organized to mirror main source structure
- Test resources in `src/test/resources/` contain sample instruction files (mows.txt, mows_3.txt, etc.)
- Tests use JUnit 5 with Mockito for mocking and AssertJ for assertions
- MowitnowModuleTest validates dependency injection configuration
- Comprehensive edge case testing for boundary conditions and null safety

## Code Quality & Best Practices

### Architectural Compliance

**Dependency Direction**: The project strictly follows hexagonal architecture principles:
- Domain layer has NO dependencies on adapters or infrastructure
- All domain exceptions are in `domain/exceptions/` (e.g., EntityNotFoundException)
- Services in domain layer only depend on ports (interfaces) and other domain classes
- Adapters implement ports and depend on domain, never the reverse

**Layer Violations to Avoid**:
- Never import adapter classes in domain services
- Domain exceptions should not be in adapter packages
- All repository implementations must be in adapters, interfaces in ports

### Design Pattern Implementations

**Flyweight Pattern - Orientation Classes**:
- Orientation instances are singletons accessed via `Orientation.NORTH`, `Orientation.SOUTH`, etc.
- Never instantiate orientation classes directly (they are package-private)
- This reduces memory overhead as orientations are immutable and stateless

**Repository Pattern - Collection Exposure**:
- Repository methods returning collections MUST return unmodifiable views
- Use `Collections.unmodifiableList()` to prevent external modification of internal state
- Example: `InMemoryMowRepository.all()` returns unmodifiable list

### Resource Management

**File I/O**:
- Always use try-with-resources for streams and file operations
- Example in FileAdapter uses `try (Stream<String> linesStream = Files.lines(...))`
- This ensures proper resource cleanup even when exceptions occur

### Exception Handling

**Specific Exceptions**:
- Catch specific exceptions rather than broad RuntimeException
- Example in MowFactory catches `NumberFormatException | ArrayIndexOutOfBoundsException`
- Provide meaningful error messages with context (e.g., include the malformed input)

**Null Safety**:
- Use `Objects.requireNonNull()` for parameter validation
- Provides clear error messages: `Objects.requireNonNull(coordinates, "coordinates cannot be null")`
- Fail fast with explicit messages rather than allowing NPE

### Naming Conventions

**Method Names**:
- Methods should clearly express intent: `isWithinBounds()` not `outOfBounds()` when returning true for valid positions
- Test methods use format: `should_<action>_when_<condition>` or `should_<expected_behavior>`
- Boolean methods use `is`, `has`, `should` prefixes

**Exception Messages**:
- Include relevant context in exception messages
- Example: `"An error occurred when parsing the file: " + filePath`
- Use correct spelling (occurred not occured)

### Testing Guidelines

**Test Coverage Requirements**:
- All domain models should have unit tests covering edge cases
- Boundary testing is critical (min, max, zero, negative values)
- Null safety must be tested for all public methods accepting object parameters
- Test both success and failure paths

**Test Organization**:
- One test class per production class (e.g., GrassTest for Grass)
- Use `@ParameterizedTest` with `@CsvSource` or `@ValueSource` for multiple scenarios
- Use descriptive test names that document expected behavior
