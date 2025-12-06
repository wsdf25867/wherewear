# WhereWear

An AI-powered outfit analysis API that evaluates clothing appropriateness based on TPO (Time, Place, Occasion) context using Google's Gemini AI.

## Overview

WhereWear is a Spring Boot REST API that accepts outfit images and TPO (Time, Place, Occasion) information, then provides AI-powered analysis of whether the outfit is appropriate for the given context. The service uses Google's Generative AI (Gemini) for intelligent image analysis.

## Requirements

### Prerequisites

- **Java**: 21 (Liberica JDK recommended) - managed via SDKMAN (see `.sdkmanrc`)
- **Kotlin**: 2.2.21
- **Gradle**: Wrapper included (no separate installation needed)
- **Google Generative AI API Key**: Required for Gemini AI integration

### SDK Setup (Optional)

```bash
# Install SDKMAN if not already installed
curl -s "https://get.sdkman.io" | bash

# Auto-switch to the correct Java version
sdk env install
```

## Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd wherewear
```

### 2. Configure API Key

Edit `src/main/resources/application.yml` and set your Google Generative AI API key:

```yaml
spring:
  ai:
    google:
      genai:
        api-key: YOUR_API_KEY
```

## Running the Application

### Build

```bash
# Build the project
./gradlew build

# Clean build
./gradlew clean build
```

### Run

```bash
# Start the application
./gradlew bootRun
```

The application will start on the default port (typically `http://localhost:8080`).

## Available Scripts

| Command | Description |
|---------|-------------|
| `./gradlew build` | Build the project |
| `./gradlew clean build` | Clean and rebuild |
| `./gradlew bootRun` | Run the application |
| `./gradlew test` | Run all tests |
| `./gradlew test --tests "ClassName"` | Run specific test class |

## Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `SPRING_AI_GOOGLE_GENAI_API_KEY` | Google Generative AI API key | Yes |

Alternatively, configure directly in `application.yml`.

## Configuration

Key configuration options in `src/main/resources/application.yml`:

| Property | Default | Description |
|----------|---------|-------------|
| `spring.application.name` | WhereWear | Application name |
| `spring.servlet.multipart.max-file-size` | 10MB | Maximum upload file size |
| `spring.servlet.multipart.max-request-size` | 10MB | Maximum request size |
| `spring.ai.google.genai.chat.options.model` | gemini-2.0-flash-lite | Gemini model to use |

## API Endpoints

### Analyze Outfit

Analyzes an outfit image against TPO context.

**Endpoint:** `POST /api/v1/analyze`

**Content-Type:** `multipart/form-data`

**Request Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| `image` | File | Outfit image (JPEG, PNG, etc.) |
| `tpo` | JSON | TPO context object |

**TPO JSON Structure:**

```json
{
  "time": "여름 낮 2시",
  "place": "결혼식장",
  "occasion": "하객"
}
```

**Response:** JSON with analysis results

## Tests

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

## Project Structure

```
wherewear/
├── build.gradle.kts              # Gradle build configuration
├── settings.gradle.kts           # Gradle settings
├── gradlew                       # Gradle wrapper (Unix)
├── gradlew.bat                   # Gradle wrapper (Windows)
├── .sdkmanrc                     # SDKMAN configuration
├── HELP.md                       # Spring Boot reference links
├── src/
│   ├── main/
│   │   ├── kotlin/com/wherewear/api/
│   │   │   ├── WherewearApplication.kt    # Application entry point
│   │   │   ├── config/
│   │   │   │   └── AIConfig.kt            # AI configuration beans
│   │   │   ├── controller/
│   │   │   │   └── AnalysisController.kt  # REST API endpoints
│   │   │   ├── dto/
│   │   │   │   └── Dto.kt                 # Data transfer objects
│   │   │   └── service/
│   │   │       ├── AnalyzeService.kt      # Service interface
│   │   │       └── AIAnalyzeService.kt    # AI service implementation
│   │   └── resources/
│   │       ├── application.yml            # Application configuration
│   │       └── static/
│   │           └── index.html             # Static web interface
│   └── test/
│       └── kotlin/com/wherewear/api/
│           └── WherewearApplicationTests.kt  # Integration tests
└── gradle/
    └── wrapper/                  # Gradle wrapper files
```

## Technology Stack

- **Language:** Kotlin 2.2.21
- **Framework:** Spring Boot 4.0.0
- **Build Tool:** Gradle (Kotlin DSL)
- **AI Integration:** Spring AI 1.1.1 with Google Generative AI (Gemini)
- **JSON Processing:** Jackson Kotlin Module
- **Testing:** JUnit 5, kotlin-test-junit5

## License

<!-- TODO: Add license information -->

License information not yet specified.
