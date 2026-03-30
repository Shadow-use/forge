// Responsibility: Capturing artifact text and passing it to the next stage of the forge.
package com.shadow.forge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val etArtifactText = findViewById<EditText>(R.id.etArtifactText)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            val rawText = etArtifactText.text.toString().trim()
            
            if (rawText.isNotEmpty()) {
                // Перетворюємо в HEX для тесту логіки
                val hexData = HexEncoder.encode(rawText)
                
                // Переходимо до вибору шаблону та візуалізації
                val intent = Intent(this, PreviewActivity::class.java).apply {
                    putExtra("EXTRA_HEX", hexData)
                    putExtra("EXTRA_RAW", rawText)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Текст не може бути порожнім", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
