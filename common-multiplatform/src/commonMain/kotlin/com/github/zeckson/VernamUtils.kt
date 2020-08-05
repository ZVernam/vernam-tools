package com.github.zeckson

expect object VernamUtils {
    fun encrypt(text: String, secret: String): String
}