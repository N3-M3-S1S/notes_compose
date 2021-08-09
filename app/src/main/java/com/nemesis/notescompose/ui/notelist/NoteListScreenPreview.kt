package com.nemesis.notescompose.ui.notelist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.getCurrentTimestamp

@Preview
@Composable
private fun NoteListScreenPreview() {
    val notes =
        List(4) { index -> Note("Note № $index", "Note text", getCurrentTimestamp(), index) }
    val selectedNotes = notes.filter { it.id.mod(2) == 0 }

    NoteListScreen(
        isNoteSelectionButtonEnabled = true,
        onNoteSelectionButtonClicked = { /*TODO*/ },
        isNoteSelectionActive = false,
        onCancelNoteSelectionButtonClicked = { /*TODO*/ },
        isDeleteSelectedNotesButtonEnabled = true,
        onDeleteSelectedNotesButtonClicked = { /*TODO*/ },
        onCreateNoteButtonClicked = { /*TODO*/ },
        notesLoading = false,
        notes = notes,
        onNoteClicked = { /*TODO*/ },
        isNoteSelected = selectedNotes::contains
    )
}