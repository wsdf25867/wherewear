# WhereWear Development Guidelines

## Build & Configuration

### Prerequisites
- **Java**: 21.0.9-librca (Liberica JDK) - managed via SDKMAN (see `.sdkmanrc`)
- **Kotlin**: 2.2.21
- **Spring Boot**: 4.0.0
- **Gradle**: Wrapper included in project

### SDK Setup
```bash
# Install SDKMAN if not already installed
curl -s "https://get.sdkman.io" | bash

# Enable auto-env to automatically switch Java version
sdk env install
```

### Build Commands
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Clean build
./gradlew clean build
```

### Configuration
The application requires a Google Generative AI API key configured in `application.yml`:
```yaml
spring:
  ai:
    google:
      genai:
        api-key: YOUR_API_KEY
        chat:
          options:
            model: gemini-2.0-flash-lite
```

File upload limits are set to 10MB for multipart requests.

## Testing

### Test Framework
- **JUnit 5** with JUnit Platform
- **kotlin-test-junit5** for Kotlin test assertions
- **spring-boot-starter-webmvc-test** for MVC testing

### Running Tests
```bash
# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "com.wherewear.api.dto.DtoTest"

# Run a specific test method
./gradlew test --tests "com.wherewear.api.dto.DtoTest.TpoRequest should store time, place, and occasion correctly"
```

### Test Structure
Tests are located in `src/test/kotlin/com/wherewear/api/` mirroring the main source structure.

### Writing Tests
Unit test example for DTOs:
```kotlin
package com.wherewear.api.dto

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DtoTest {

    @Test
    fun `TpoRequest should store time, place, and occasion correctly`() {
        val tpo = TpoRequest(
            time = "여름 낮 2시",
            place = "결혼식장",
            occasion = "하객"
        )

        assertEquals("여름 낮 2시", tpo.time)
        assertEquals("결혼식장", tpo.place)
        assertEquals("하객", tpo.occasion)
    }
}
```

Spring Boot integration test example:
```kotlin
package com.wherewear.api

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WherewearApplicationTests {

    @Test
    fun contextLoads() {
    }
}
```

### Test Warnings
When running tests, you may see Mockito warnings about dynamic agent loading. These are informational and do not affect test execution:
```
Mockito is currently self-attaching to enable the inline-mock-maker...
WARNING: A Java agent has been loaded dynamically...
```

## Code Style

### Kotlin Conventions
- **Constructor injection** for dependencies (primary constructor parameters)
- **Expression body syntax** for simple functions: `fun method() = expression`
- **Data classes** for DTOs with named parameters
- **Interface-based design** for services (e.g., `AnalyzeService` interface with `AIAnalyzeService` implementation)

### Naming
- Package structure: `com.wherewear.api.<layer>` (controller, service, dto, config)
- Test classes: `<ClassName>Test.kt`
- Test methods: Use backtick syntax for descriptive names: `` `method should do something`() ``

### Comments
- Korean comments are used throughout the codebase
- Comments explain business logic and configuration purposes

### Spring Annotations
- `@RestController`, `@RequestMapping` for REST endpoints
- `@Service` for service implementations
- `@Configuration`, `@Bean` for configuration classes
- `@CrossOrigin(origins = ["*"])` enabled for development

### Compiler Options
Strict null-safety is enforced via compiler flags in `build.gradle.kts`:
```kotlin
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}
```

## Project Architecture

### Layers
- **Controller** (`controller/`): REST API endpoints
- **Service** (`service/`): Business logic with interface/implementation pattern
- **DTO** (`dto/`): Data transfer objects (request/response)
- **Config** (`config/`): Spring configuration beans

### API Endpoint
- `POST /api/v1/analyze`: Analyzes outfit images with TPO context
  - Accepts: `multipart/form-data` with `image` (file) and `tpo` (JSON)
  - Returns: `application/json` with analysis response

### External Dependencies
- **Spring AI** with Google Generative AI (Gemini) for image analysis
- **Jackson Kotlin Module** for JSON serialization
