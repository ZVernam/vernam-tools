package com.github.zeckson

import java.security.MessageDigest
import java.util.*

class VernamUtils {
    companion object {
        private const val TAG = "VernamUtils"
        private val ALPHABET = "@bCd3f9h1jKlm2nN0pq4r\$tuv5wW6x7y8Zz".toCharArray()

        private fun encodeToBase64String(bytes: ByteArray): String {
            return Base64.getEncoder().encodeToString(bytes)
        }

        fun encrypt(text: String, secret: String): String {
            // Failfast
            if (text.isBlank() || secret.isBlank()) {
                return ""
            }

            val cipher = CharArray(text.length)
            for (i in text.indices) {
                val result = text.codePointAt(i) xor secret.codePointAt(i % secret.length)
                cipher[i] = toChar(result)
            }
            return cipher.joinToString("")
        }

        /**
         * Hashes provided input by default with `SHA-256`, converts to `Base64`
         * and trims to source string length by default
         * @param {String} text — text to hash
         * @param {String} [algoName=`SHA256`] — hash algorithm name
         * @return {string}
         */
        fun hash(text: String, algoName: String = "SHA-256"): String {
            if (text.isBlank()) {
                return text
            }

            val digest: MessageDigest = MessageDigest.getInstance(algoName)
            val hash: ByteArray = digest.digest(text.toByteArray(Charsets.UTF_8))
            val base64 = encodeToBase64String(hash)

            //    Log.v(TAG, "Using hash-algorithm: $algoName")
            //    Log.v(TAG, "Hash Output b64: $base64")

            return base64
        }

        private fun toChar(code: Int): Char = ALPHABET[code % ALPHABET.size]

    }
}