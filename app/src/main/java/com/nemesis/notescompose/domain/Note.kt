package com.nemesis.notescompose.domain

data class Note(
    val title: String,
    val text: String,
    val lastUpdateTimestamp: Long,
    val id: Int
)