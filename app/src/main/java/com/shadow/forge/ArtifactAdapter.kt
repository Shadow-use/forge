// Responsibility: Correctly extracting hidden HEX metadata using byte-level precision to avoid encoding issues.
private fun extractHexFromFile(file: File): String? {
    return try {
        val bytes = file.readBytes()
        val content = String(bytes, Charsets.UTF_8) // Читаємо як UTF-8
        
        val markerStart = "SHADOW_HEX_START:"
        val markerEnd = ":END"
        
        if (content.contains(markerStart) && content.contains(markerEnd)) {
            content.substringAfter(markerStart).substringBefore(markerEnd)
        } else null
    } catch (e: Exception) {
        null
    }
}
