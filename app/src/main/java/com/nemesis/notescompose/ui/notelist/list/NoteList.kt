package com.nemesis.notescompose.ui.notelist.list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.ui.notelist.list.item.NoteListItem


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteList(
    notes: List<Note>,
    isNoteSelectionActive: Boolean,
    isNoteSelected: (Note) -> Boolean,
    onNoteClick: (Note) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(
            items = notes,
            key = { _: Int, note: Note -> note.id }) { index: Int, note: Note ->
            NoteListItem(
                note = note,
                isNoteSelectionActive = isNoteSelectionActive,
                isNoteSelected = isNoteSelected(note),
                onNoteClick = onNoteClick
            )
            if (index != notes.lastIndex) {
                Divider()
            }
        }
    }
}
