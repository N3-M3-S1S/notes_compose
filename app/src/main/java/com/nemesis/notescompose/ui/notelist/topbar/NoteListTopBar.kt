package com.nemesis.notescompose.ui.notelist.topbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nemesis.notescompose.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteListTopBar(
    isNoteSelectionButtonEnabled: Boolean,
    noteSelectionButtonClicked: () -> Unit,
    activeSelectionButtonsVisible: Boolean,
    onCancelSelectionButtonClicked: () -> Unit,
    isDeleteSelectedNotesButtonEnabled: Boolean,
    onDeleteSelectedNotesButtonClicked: () -> Unit,
) {
    TopAppBar(title = { TopBarTitle() },
        actions = {
            if (activeSelectionButtonsVisible) {
                CancelSelectionButton(onCancelSelectionButtonClicked)
                DeleteSelectedNotesButton(
                    isDeleteSelectedNotesButtonEnabled,
                    onDeleteSelectedNotesButtonClicked
                )
            } else {
                SetSelectionActiveButton(
                    isNoteSelectionButtonEnabled,
                    noteSelectionButtonClicked
                )
            }
        }
    )
}

@Composable
private fun TopBarTitle() {
    Text(text = stringResource(id = R.string.note_list_title))
}

@Composable
private fun CancelSelectionButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Default.Clear, contentDescription = "Cancel selection")
    }
}

@Composable
private fun DeleteSelectedNotesButton(buttonEnabled: Boolean = false, onClick: () -> Unit) {
    IconButton(onClick = onClick, enabled = buttonEnabled) {
        Icon(Icons.Default.Delete, contentDescription = "Delete selected notes")
    }
}

@Composable
private fun SetSelectionActiveButton(buttonEnabled: Boolean = false, onClick: () -> Unit) {
    IconButton(onClick = onClick, enabled = buttonEnabled) {
        Icon(Icons.Outlined.CheckBox, contentDescription = "Select notes")
    }
}

