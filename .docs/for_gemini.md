# PROJECT CONTEXT: forge

## 1. ABOUT

# About forge

## 2. PLAN

Оновлений план:
​[x] Phase 1: Init
​[x] Phase 2: HEX Encoding
​[x] Phase 3: Visual Processing (Базовий рендеринг)
​[ ] Phase 4: File Injection (Збереження + EOF допис)
## 3. STRUCTURE

```
.
├── app
│   ├── build.gradle.kts
│   ├── debug.keystore
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           ├── java
│           │   └── com
│           │       └── shadow
│           │           └── forge
│           │               ├── ArtifactDrawer.kt
│           │               ├── HexEncoder.kt
│           │               ├── InputActivity.kt
│           │               ├── Logger.kt
│           │               ├── MainActivity.kt
│           │               └── PreviewActivity.kt
│           └── res
│               ├── layout
│               │   ├── activity_input.xml
│               │   ├── activity_main.xml
│               │   └── activity_preview.xml
│               └── values
├── build.gradle.kts
├── gradle
│   └── wrapper
│       └── gradle-wrapper.properties
├── gradle.properties
└── settings.gradle.kts

13 directories, 16 files
```

## 4. LOGIC

### Logical Map (Auto-generated)
- HexEncoder.kt: Atomic utility for converting strings to HEX representation and back.
Converting raw string to a HEX-formatted string.
Decoding a HEX string back to original UTF-8 text.
- PreviewActivity.kt: UI for previewing the generated artifact and triggering the final file save.
- InputActivity.kt: Capturing artifact text and passing it to the next stage of the forge.
- Logger.kt: Atomic utility for writing application logs and errors to a physical file.
Appending log message to forge_logs.txt
- ArtifactDrawer.kt: Handling image manipulation, drawing text on a circular path, and final bitmap processing.
Drawing hex text between the golden rings of the template.
- MainActivity.kt: Entry point with global crash interception and redirection to InputActivity.
Catching all unhandled exceptions and writing them to forge_logs.txt.
