package com.nemesis.notescompose.ui.notecontent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun NoteContentPreview() {
    NoteContent(
        title = "Note title",
        onTitleChanged = { /*TODO*/ },
        text = "Note text",
        onTextChanged = { /*TODO*/ },
    )
}