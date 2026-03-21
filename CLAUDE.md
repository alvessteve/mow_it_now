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
