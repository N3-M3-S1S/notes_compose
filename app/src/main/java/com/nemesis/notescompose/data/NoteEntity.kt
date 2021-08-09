package com.nemesis.notescompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nemesis.notescompose.domain.Note

@Entity
data class NoteEntity(val title: String, val text: String, val lastUpdateTimestamp: Long) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    operator fun component4(): Int = id
}

fun NoteEntity.toNote() = Note(title, text, lastUpdateTimestamp, id)

