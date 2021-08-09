package com.nemesis.notescompose.ui.notelist.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun PreviewNoteListTopBar() {
    NoteListTopBar(
        isNoteSelectionButtonEnabled = true,
        noteSelectionButtonClicked = { /*TODO*/ },
        activeSelectionButtonsVisible = true,
        onCancelSelectionButtonClicked = { /*TODO*/ },
        isDeleteSelectedNotesButtonEnabled = true,
        onDeleteSelectedNotesButtonClicked = { /*TODO*/ }
    )
}