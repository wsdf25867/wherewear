# Project Guidelines

## 1. Build/Configuration Instructions

### Prerequisites
- **Java**: JDK 21
- **Kotlin**: 2.2.21
- **API Key**: A Google Generative AI API key is required.
  - Set `spring.ai.google.genai.api-key` in `src/main/resources/application.yml` or use the environment variable `SPRING_AI_GOOGLE_GENAI_API_KEY`.

### Build
To build the project, run:
```bash
./gradlew build
```

## 2. Testing Information

### Running Tests
To run all tests:
```bash
./gradlew test
```

### Adding New Tests
- Tests are located in `src/test/kotlin/`.
- The project uses JUnit 5 and `kotlin-test-junit5`.
- Integration tests should use `@SpringBootTest`.

### Example Test
Here is a verified example of a simple unit test (`SimpleVerificationTest.kt`):

```kotlin
package com.wherewear.api

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class SimpleVerificationTest {
    @Test
    fun `simple addition verification`() {
        val result = 1 + 1
        assertEquals(2, result, "1 + 1 should equal 2")
    }
}
```

## 3. Additional Development Information

### Code Style
- **Indentation**: 4 spaces.
- **Imports**: Alphabetical order.
- **Structure**:
  - `src/main/resources/prompts/`: Contains prompt templates (e.g., `analyze-outfit-template.st`). Ensure the file path references in code match this directory structure.
  - `AnalyzeOutfitService`: Core service handling AI interaction.
- **Conventions**:
  - Use data classes for DTOs.
  - Follow standard Kotlin coding conventions.

### Troubleshooting
- **Resource Not Found**: If you encounter issues loading prompt templates, verify that the `@Value` annotation path matches the file location in `src/main/resources/prompts/`.
