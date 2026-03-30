// Responsibility: Handling image manipulation, drawing text on a circular path, and final bitmap processing.
package com.shadow.forge

import android.graphics.*

object ArtifactDrawer {

    // Responsibility: Drawing hex text between the golden rings of the template.
    fun drawHexCircle(baseBitmap: Bitmap, hexText: String): Bitmap {
        val resultBitmap = baseBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(resultBitmap)
        
        val centerX = resultBitmap.width / 2f
        val centerY = resultBitmap.height / 2f
        // Радіус підбираємо під простір між кільцями на твоєму малюнку karta.png
        val radius = resultBitmap.width * 0.32f 

        val paint = Paint().apply {
            color = Color.parseColor("#D4AF37") // Золотий колір електруму
            textSize = 32f
            typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
            isAntiAlias = true
            letterSpacing = 0.1f // Щоб HEX-символи не зливалися
        }

        val path = Path().apply {
            addCircle(centerX, centerY, radius, Path.Direction.CW)
        }

        // Рівномірно розподіляємо текст по колу
        canvas.drawTextOnPath(hexText, path, 0f, 0f, paint)
        
        return resultBitmap
    }
}
