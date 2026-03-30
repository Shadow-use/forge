// Responsibility: Atomic utility for writing application logs and errors to a physical file.
package com.shadow.forge

import android.content.Context
import java.io.File
import java.util.Date

object ShadowLogger {
    // Responsibility: Appending log message to forge_logs.txt
    fun logError(context: Context, message: String) {
        val logFile = File(context.filesDir, "forge_logs.txt")
        val timestamp = Date().toString()
        logFile.appendText("[$timestamp] ERROR: $message\n")
    }
}
