// Responsibility: UI for previewing and finalizing the artifact forging process.
package com.shadow.forge

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PreviewActivity : AppCompatActivity() {

    private var currentBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val ivPreview = findViewById<ImageView>(R.id.ivPreview)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val hexData = intent.getStringExtra("EXTRA_HEX") ?: ""
        val rawText = intent.getStringExtra("EXTRA_RAW") ?: ""

        try {
            // Завантаження шаблону
            val options = BitmapFactory.Options().apply { inMutable = true }
            val template = BitmapFactory.decodeResource(resources, R.drawable.karta, options)

            // Рендеринг
            currentBitmap = ArtifactDrawer.drawHexCircle(template, hexData)
            ivPreview.setImageBitmap(currentBitmap)
            
            tvStatus.text = "Готово до запечатування: $rawText"
            
        } catch (e: Exception) {
            ShadowLogger.logError(this, "Preview Error: ${e.message}")
        }

        btnSave.setOnClickListener {
            currentBitmap?.let { bitmap ->
                val path = FileForge.forgeFile(this, bitmap, hexData)
                if (path != null) {
                    Toast.makeText(this, "Артефакт збережено: $path", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Помилка при запечатуванні", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
