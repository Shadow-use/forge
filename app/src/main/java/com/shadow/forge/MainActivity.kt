// Responsibility: Entry point with global crash interception and redirection to InputActivity.
package com.shadow.forge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setupGlobalExceptionHandler()
        
        // Перехід до першого робочого вікна
        startActivity(Intent(this, InputActivity::class.java))
        finish()
    }

    // Responsibility: Catching all unhandled exceptions and writing them to forge_logs.txt.
    private fun setupGlobalExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            ShadowLogger.logError(this, "CRASH in ${thread.name}: ${throwable.message}")
            // Дозволяємо системі завершити процес після запису логу
            System.exit(1)
        }
    }
}
