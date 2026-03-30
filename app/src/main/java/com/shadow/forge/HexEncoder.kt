// Responsibility: Atomic utility for converting strings to HEX representation and back.
package com.shadow.forge

object HexEncoder {

    // Responsibility: Converting raw string to a HEX-formatted string.
    fun encode(input: String): String {
        return input.toByteArray(Charsets.UTF_8).joinToString("") { byte ->
            "%02x".format(byte)
        }
    }

    // Responsibility: Decoding a HEX string back to original UTF-8 text.
    fun decode(hex: String): String {
        val output = StringBuilder()
        var i = 0
        while (i < hex.length) {
            val str = hex.substring(i, i + 2)
            output.append(str.toInt(16).toChar())
            i += 2
        }
        return output.toString()
    }
}
