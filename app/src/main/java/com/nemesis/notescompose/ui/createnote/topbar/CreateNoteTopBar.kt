package com.nemesis.notescompose.ui.createnote.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nemesis.notescompose.R

@Composable
fun CreateNoteTopBar(
    onBackButtonClicked: () -> Unit,
    isCreateNoteButtonEnabled: Boolean,
    onCreateNoteButtonClicked: () -> Unit,
) {
    TopAppBar(
        title = { CreateNoteTitle() },
        navigationIcon = { BackIcon(onClick = onBackButtonClicked) },
        actions = {
            CreateButton(
                onClick = onCreateNoteButtonClicked,
                enabled = isCreateNoteButtonEnabled
            )
        }
    )
}

@Composable
private fun CreateNoteTitle() {
    Text(text = stringResource(R.string.create_note_title))
}

@Composable
private fun BackIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
    }
}

@Composable
private fun CreateButton(onClick: () -> Unit, enabled: Boolean) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(imageVector = Icons.Default.Save, contentDescription = "Create note")
    }
}
