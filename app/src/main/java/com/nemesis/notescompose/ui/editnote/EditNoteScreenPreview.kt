package com.nemesis.notescompose.ui.editnote

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.getCurrentTimestamp

@Preview
@Composable
private fun EditNoteScreenPreview() {
    EditNoteScreen(
        onBackButtonClicked = { /*TODO*/ },
        onDeleteNoteButtonClicked = { /*TODO*/ },
        isUpdateNoteButtonEnabled = true,
        onUpdateNoteButtonClicked = { /*TODO*/ },
        isNoteContentVisible = true,
        title = "Title",
        onTitleChanged = { /*TODO*/ },
        text = "Text",
        onTextChanged = { /*TODO*/ },
        lastUpdateTimestamp = getCurrentTimestamp()
    )
}