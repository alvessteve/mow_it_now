---
name: code-review
description: Perform comprehensive code review focusing on clean code, hexagonal architecture, and Java craftsmanship principles
user-invocable: true
disable-model-invocation: false
---

# Comprehensive Code Review

Perform a thorough code review of recent changes with focus on software craftsmanship and clean architecture principles.

## Review Scope

1. **Identify Changed Files**
   - Run `git status` to see uncommitted changes
   - Run `git diff` to see current modifications
   - Run `git diff main...HEAD` to see all changes in current branch

## Review Criteria

### 1. Clean Code Principles

**Single Responsibility Principle (SRP)**
- Each class/method should have one reason to change
- Methods should do one thing and do it well
- Identify violations with specific file:line references
- Check for God classes or methods doing too much

**DRY (Don't Repeat Yourself)**
- Look for duplicated logic across files
- Identify opportunities for abstraction
- Suggest shared utilities or helper methods
- Check for repeated validation or transformation logic

**Meaningful Naming**
- Classes, methods, and variables should reveal intent
- Avoid abbreviations unless universally understood
- Boolean variables should be named with is/has/should prefixes
- Methods should be verbs, classes should be nouns
- Avoid Hungarian notation

**Method Length & Complexity**
- Flag methods longer than 20 lines (consider splitting)
- Identify deeply nested conditionals (>3 levels)
- Suggest extraction of complex expressions into well-named methods
- Check cyclomatic complexity

### 2. SOLID Principles

**Single Responsibility**
- Each class should have one reason to change
- Services should have cohesive responsibilities

**Open/Closed Principle**
- Classes open for extension, closed for modification
- Check for proper use of interfaces and abstract classes

**Liskov Substitution**
- Subclasses should be substitutable for their base classes
- Check for violated contracts in overridden methods

**Interface Segregation**
- Interfaces should be client-specific, not general-purpose
- Flag fat interfaces with too many methods

**Dependency Inversion**
- High-level modules should not depend on low-level modules
- Both should depend on abstractions (interfaces/ports)
- Verify proper use of dependency injection

### 3. Hexagonal Architecture Compliance

**Port/Adapter Separation**
- Domain logic should only depend on ports (interfaces)
- Adapters should implement ports, not the reverse
- No direct dependencies from domain to infrastructure

**Dependency Direction**
- Domain should have no external dependencies
- Adapters depend on ports
- Infrastructure (DI config) wires everything together

**Layer Violations**
- Domain entities should not reference adapters
- Ports should be in separate package from adapters
- No framework dependencies in domain layer

**Use Case Purity**
- Use cases (domain/ClientUseCase) should orchestrate business logic
- No infrastructure concerns in use cases
- Services handle domain operations

### 4. Java Best Practices

**Exception Handling**
- Don't catch generic Exception unless necessary
- Provide meaningful exception messages
- Use custom domain exceptions appropriately
- Don't swallow exceptions silently
- Consider exception hierarchy (checked vs unchecked)

**Null Safety**
- Prefer Optional<T> over null returns where appropriate
- Check for potential NullPointerException risks
- Validate constructor parameters
- Use Objects.requireNonNull() for non-null assertions

**Immutability**
- Prefer immutable objects where possible
- Mark fields final when they shouldn't change
- Use defensive copies for mutable collections
- Check for proper encapsulation

**Resource Management**
- Use try-with-resources for AutoCloseable
- Check for proper stream closing
- Verify file handles are released

### 5. Dependency Injection (Guice)

**Binding Configuration**
- Verify all dependencies are properly bound in Module
- Check for missing bindings that could cause runtime errors
- Ensure appropriate scopes (SINGLETON, etc.)

**Constructor Injection**
- Prefer constructor injection over field injection
- Verify @Inject annotations are present
- Check for circular dependencies

**Interface Binding**
- Bind to interfaces, not implementations
- Verify implementation classes are not exposed

### 6. Testing

**Test Coverage**
- Identify new methods/classes without corresponding tests
- Check if edge cases are covered
- Suggest test cases for error scenarios
- Verify happy path and failure paths are tested

**Test Quality**
- Tests should be readable and maintainable
- Use descriptive test method names (should_X_when_Y)
- Avoid brittle assertions
- Check for proper use of mocking (Mockito)

**Test Organization**
- Test classes should mirror main class structure
- Use proper JUnit 5 annotations (@Test, @BeforeEach, etc.)
- Verify proper use of assertions (AssertJ fluent assertions)

**Test Independence**
- Each test should be independent
- No test ordering dependencies
- Proper setup/teardown

### 7. Security (OWASP Top 10)

**Injection Vulnerabilities**
- Check for SQL/NoSQL injection risks
- Validate all external inputs (file, command-line)
- Check for command injection in process execution

**Input Validation**
- All external input should be validated
- Check bounds, formats, and patterns
- Use whitelist validation over blacklist

**Data Exposure**
- Look for hardcoded secrets or credentials
- Check for sensitive data in error messages or logs
- Verify no sensitive data in version control

**Error Information Disclosure**
- Don't expose stack traces to users
- Error messages should be user-friendly
- Log detailed errors, show generic messages to users

**Security Misconfiguration**
- Check for insecure defaults
- Verify proper access modifiers (private/protected)
- Check for overly permissive configurations

### 8. Design Patterns Usage

**Command Pattern**
- Verify Instruction implementations follow pattern correctly
- Check for proper execute() implementations
- Ensure commands are stateless or properly encapsulated

**Factory Pattern**
- Factories should encapsulate creation logic
- Check for proper validation in factories
- Verify factory methods have clear responsibilities

**Repository Pattern**
- Repositories should abstract data access
- No business logic in repositories
- Check for proper interface definitions

### 9. Code Style & Conventions

**Naming Conventions**
- Classes: PascalCase
- Methods/variables: camelCase
- Constants: UPPER_SNAKE_CASE
- Packages: lowercase

**Code Organization**
- Proper package structure
- Related classes grouped together
- Clear separation of concerns

**Comments & Documentation**
- Javadoc for public APIs
- Complex algorithms should be explained
- Avoid obvious comments
- Keep comments up-to-date with code

**Lombok Usage**
- Appropriate use of @Getter, @AllArgsConstructor
- Check for proper access levels in annotations
- Verify @Builder usage where appropriate

## Output Format

Provide feedback structured as:

### ✅ Strengths
- List positive aspects of the code
- Highlight good patterns and practices

### ⚠️ Issues Found

#### 🔴 Critical (Must Fix)
- Security vulnerabilities
- Logic errors
- Architecture violations
- Breaking changes

#### 🟡 Important (Should Fix)
- Code quality issues
- Missing tests
- SOLID violations
- Exception handling problems

#### 🔵 Suggestions (Consider)
- Refactoring opportunities
- Performance optimizations
- Code style improvements
- Better naming

For each issue, provide:
- **File:Line reference** (e.g., `src/main/java/domain/model/Grass.java:18`)
- **Description** of the issue
- **Suggested fix** with code example if applicable
- **Rationale** explaining why it matters

## Example Review Item

```
🟡 Null Safety Issue - src/main/java/domain/service/MowService.java:23

**Issue**: Method parameter not validated for null before use

**Current Code**:
```java
public void moveMow(Mow mow, Direction direction) {
    mow.move(direction);  // NPE if mow is null
}
```

**Suggested Fix**:
```java
public void moveMow(Mow mow, Direction direction) {
    Objects.requireNonNull(mow, "mow cannot be null");
    Objects.requireNonNull(direction, "direction cannot be null");
    mow.move(direction);
}
```

**Rationale**: Fail fast with clear error messages. Prevents NullPointerException at a later point with unclear context. Makes the contract explicit.
```

## Completion

After review, ask if the user wants you to:
1. Fix any of the identified issues
2. Add missing tests for uncovered code
3. Refactor code to better align with hexagonal architecture
4. Update documentation based on findings