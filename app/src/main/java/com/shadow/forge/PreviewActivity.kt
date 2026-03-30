// Responsibility: UI for previewing, local saving, and remote publishing of forged artifacts.
package com.shadow.forge

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class PreviewActivity : AppCompatActivity() {

    private var currentBitmap: Bitmap? = null
    private var lastSavedPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val ivPreview = findViewById<ImageView>(R.id.ivPreview)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnPublish = findViewById<Button>(R.id.btnPublish)

        // Отримуємо дані з попереднього екрана
        val hexData = intent.getStringExtra("EXTRA_HEX") ?: ""
        val rawText = intent.getStringExtra("EXTRA_RAW") ?: ""

        try {
            // 1. Рендеринг артефакту
            val options = BitmapFactory.Options().apply { inMutable = true }
            val template = BitmapFactory.decodeResource(resources, R.drawable.karta, options)
            
            currentBitmap = ArtifactDrawer.drawHexCircle(template, hexData)
            ivPreview.setImageBitmap(currentBitmap)
            
            tvStatus.text = "Сяйво накладено: $rawText"
        } catch (e: Exception) {
            ShadowLogger.logError(this, "Preview Rendering Error: ${e.message}")
            tvStatus.text = "Помилка візуалізації"
        }

        // 2. Локальне збереження
        btnSave.setOnClickListener {
            currentBitmap?.let { bitmap ->
                val path = FileForge.forgeFile(this, bitmap, hexData)
                if (path != null) {
                    lastSavedPath = path
                    Toast.makeText(this, "Збережено в сховище", Toast.LENGTH_SHORT).show()
                    btnPublish.isEnabled = true // Дозволяємо публікацію тільки після збереження
                    tvStatus.text = "Артефакт запечатаний локально."
                } else {
                    Toast.makeText(this, "Збій локального запису", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 3. Публікація на GitHub
        btnPublish.setOnClickListener {
            lastSavedPath?.let { path ->
                val file = File(path)
                btnPublish.isEnabled = false // Блокуємо кнопку на час відправки
                tvStatus.text = "Передача в тіні Гітхабу..."

                GitHubService.uploadArtifact(file) { success, message ->
                    runOnUiThread {
                        if (success) {
                            Toast.makeText(this, "Опубліковано на вівтарі!", Toast.LENGTH_LONG).show()
                            tvStatus.text = "Справу завершено. Сяйво передано."
                            btnPublish.setBackgroundColor(android.graphics.Color.DKGRAY)
                        } else {
                            Toast.makeText(this, "Збій передачі: $message", Toast.LENGTH_LONG).show()
                            tvStatus.text = "Зв'язок перервано. Спробуй ще раз."
                            btnPublish.isEnabled = true
                        }
                    }
                }
            }
        }
    }
}
