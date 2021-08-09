package com.nemesis.notescompose.ui.notelist.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.getCurrentTimestamp

@Preview
@Composable
private fun NoteListPreview() {
    val notes =
        List(4) { index -> Note("Note № $index", "Note text", getCurrentTimestamp(), index) }
    val selectedNotes = notes.takeLast(2)

    NoteList(
        notes = notes,
        isNoteSelectionActive = true,
        isNoteSelected = selectedNotes::contains,
        onNoteClick = { /*TODO*/ }
    )
}