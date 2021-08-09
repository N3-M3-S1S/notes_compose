package com.nemesis.notescompose.ui.createnote

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun CreateNoteScreenPreview() {
    CreateNoteScreen(
        onBackButtonClicked = { /*TODO*/ },
        isCreateNoteButtonEnabled = true,
        onCreateNoteButtonClicked = { /*TODO*/ },
        title = "Title",
        onTitleChanged = { /*TODO*/ },
        text = "Text",
        onTextChanged = { /*TODO*/ }
    )
}