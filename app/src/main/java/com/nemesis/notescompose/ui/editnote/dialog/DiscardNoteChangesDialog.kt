package com.nemesis.notescompose.ui.editnote.dialog

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nemesis.notescompose.R

@Composable
fun DiscardNoteChangesDialog(
    onDiscardChangesButtonClicked: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = { DiscardChangesText() },
        confirmButton = { DiscardChangesButton(onDiscardChangesButtonClicked) },
        dismissButton = { DismissDialogButton(onDismiss) },
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun DiscardChangesText() {
    Text(text = stringResource(id = R.string.edit_note_discard_changes))
}

@Composable
private fun DiscardChangesButton(onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text = stringResource(id = android.R.string.ok))
    }
}

@Composable
private fun DismissDialogButton(onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text = stringResource(id = android.R.string.cancel))
    }
}
