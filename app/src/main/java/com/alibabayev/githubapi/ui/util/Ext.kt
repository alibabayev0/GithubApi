package com.alibabayev.githubapi.ui.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

fun Context.openUrl(url: String) {
    val urlIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    startActivity(urlIntent)
}

fun String.textToColor(): Color {
    return Color(
        (this.hashCode() * 3) % 256,
        (this.hashCode() / 2 * 4) % 256,
        (this.hashCode() / 3 * 5) % 256
    )
}

fun Date?.dateToString(format: String): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return this?.let { dateFormatter.format(it) } ?: ""
}
