package com.nemesis.notescompose.ui.editnote.topbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
private fun CreateNoteTopBarPreview() {
    CreateNoteTopBar(
        onBackButtonClicked = { /*TODO*/ },
        isUpdateNoteButtonEnabled = true,
        onUpdateNoteButtonClicked = { /*TODO*/ },
        onDeleteNoteButtonClicked = { /*TODO*/ }
    )
}