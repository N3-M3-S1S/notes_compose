package com.nemesis.notescompose.ui.notelist.list.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.getCurrentTimestamp

@Preview
@Composable
private fun PreviewNoteListItems() {
    val note = Note("Title", "Text", getCurrentTimestamp(), 1)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NoteListItem(note = note, isNoteSelectionActive = true, isNoteSelected = true, onNoteClick = { /*TODO*/ })
        NoteListItem(note = note, isNoteSelectionActive = false, isNoteSelected = false, onNoteClick = { /*TODO*/ })
    }
}