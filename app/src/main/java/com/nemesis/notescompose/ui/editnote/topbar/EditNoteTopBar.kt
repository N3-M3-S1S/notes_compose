package com.nemesis.notescompose.ui.editnote.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nemesis.notescompose.R

@Composable
fun CreateNoteTopBar(
    onBackButtonClicked: () -> Unit,
    isUpdateNoteButtonEnabled: Boolean,
    onUpdateNoteButtonClicked: () -> Unit,
    onDeleteNoteButtonClicked: () -> Unit
) {
    TopAppBar(
        title = { TopBarTitle() },
        navigationIcon = { BackIcon(onClick = onBackButtonClicked) },
        actions = {
            DeleteButton(onClick = onDeleteNoteButtonClicked)
            UpdateButton(onClick = onUpdateNoteButtonClicked, enabled = isUpdateNoteButtonEnabled)
        }
    )
}

@Composable
private fun TopBarTitle() {
    Text(text = stringResource(R.string.edit_note_title))
}

@Composable
private fun BackIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
    }
}

@Composable
private fun DeleteButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
    }
}

@Composable
private fun UpdateButton(onClick: () -> Unit, enabled: Boolean) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(imageVector = Icons.Default.Save, contentDescription = "Update")
    }
}
