package com.nemesis.notescompose

import android.text.format.DateFormat

fun getCurrentTimestamp(): Long = System.currentTimeMillis()

fun timestampToPrettyString(timestamp: Long): String =
    DateFormat.format("dd.MM.yy HH:mm:ss", timestamp).toString()
