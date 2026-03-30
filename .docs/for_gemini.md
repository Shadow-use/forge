# PROJECT CONTEXT: forge

## 1. ABOUT

# About forge

## 2. PLAN

Тіню, твій план оновлено:
​[x] Phase 1: Init
​[x] Phase 2: HEX Encoding
​[x] Phase 3: Visual Processing (Виправлено заповнення кілець)
​[x] Phase 4: File Injection (Збереження працює)
​[ ] Phase 5: Artifact Gallery (Скелет готовий, треба дописати Адаптер).
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
│           │               ├── ArtifactAdapter.kt
│           │               ├── ArtifactDrawer.kt
│           │               ├── FileForge.kt
│           │               ├── GalleryActivity.kt
│           │               ├── GitHubService.kt
│           │               ├── HexEncoder.kt
│           │               ├── InputActivity.kt
│           │               ├── Logger.kt
│           │               ├── MainActivity.kt
│           │               └── PreviewActivity.kt
│           └── res
│               ├── drawable
│               │   └── karta.png
│               ├── layout
│               │   ├── activity_gallery.xml
│               │   ├── activity_input.xml
│               │   ├── activity_main.xml
│               │   ├── activity_preview.xml
│               │   └── item_artifact.xml
│               └── values
├── build.gradle.kts
├── gradle
│   └── wrapper
│       └── gradle-wrapper.properties
├── gradle.properties
└── settings.gradle.kts

14 directories, 23 files
```

## 4. LOGIC

### Logical Map (Auto-generated)
- HexEncoder.kt: Precise HEX to UTF-8 conversion for Cyrillic support.
- PreviewActivity.kt: UI for previewing, local saving, and remote publishing of forged artifacts.
- InputActivity.kt: Capturing artifact text and passing it to the next stage of the forge.
- Logger.kt: Atomic utility for writing application logs and errors to a physical file.
Appending log message to forge_logs.txt
- ArtifactDrawer.kt: Advanced image manipulation, focusing on aesthetic hex placement.
Drawing hex text to fill the visual void between rings.
- GalleryActivity.kt: Scanning local storage and displaying forged artifacts in a list.
- MainActivity.kt: Main navigation hub and global crash logging.
- GitHubService.kt: Atomic utility to upload images to GitHub repository using REST API.
Uploading a file by converting it to Base64 and sending a PUT request.
- ArtifactAdapter.kt: Adapting artifact files to UI with proper UTF-8 metadata extraction.
- FileForge.kt: Atomic utility for saving Bitmaps and injecting raw HEX data at the end of the file (EOF).
Saving the artifact image and appending the secret HEX metadata.
