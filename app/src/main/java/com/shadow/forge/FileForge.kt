// Responsibility: Atomic utility for saving Bitmaps and injecting raw HEX data at the end of the file (EOF).
package com.shadow.forge

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

object FileForge {

    // Responsibility: Saving the artifact image and appending the secret HEX metadata.
    fun forgeFile(context: Context, bitmap: Bitmap, hexMetadata: String): String? {
        val fileName = "artifact_${System.currentTimeMillis()}.png"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(storageDir, fileName)

        return try {
            // 1. Зберігаємо основне зображення
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            
            // 2. Дописуємо HEX-дані в кінець файлу
            // Використовуємо спеціальні маркери, щоб "Око" знало, де шукати
            val payload = "\nSHADOW_HEX_START:$hexMetadata:END".toByteArray()
            out.write(payload)
            
            out.close()
            file.absolutePath
        } catch (e: Exception) {
            ShadowLogger.logError(context, "ForgeFile Error: ${e.message}")
            null
        }
    }
}
