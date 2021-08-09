package com.nemesis.notescompose.ui.editnote

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesis.notescompose.R
import com.nemesis.notescompose.ui.editnote.dialog.DiscardNoteChangesDialog
import com.nemesis.notescompose.ui.editnote.topbar.CreateNoteTopBar
import com.nemesis.notescompose.ui.notecontent.NoteContent
import com.nemesis.notescompose.timestampToPrettyString

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EditNoteScreen(noteId: Int, editNoteViewModel: EditNoteViewModel = hiltViewModel()) {
    editNoteViewModel.loadNote(noteId)

    val showDiscardNoteChangesDialog by editNoteViewModel.showDiscardNoteChangesConfirmation.collectAsState()
    val editNoteState by editNoteViewModel.editNoteState.collectAsState()

    if (showDiscardNoteChangesDialog) {
        DiscardNoteChangesDialog(
            onDiscardChangesButtonClicked = editNoteViewModel::discardNoteChanges,
            onDismiss = editNoteViewModel::discardNoteChangesConfirmationCanceled
        )
    }

    var title by rememberSaveable(editNoteState) {
        val title = (editNoteState as? EditNoteState.NoteReady)?.note?.title ?: ""
        mutableStateOf(title)
    }

    var text by rememberSaveable(editNoteState) {
        val text = (editNoteState as? EditNoteState.NoteReady)?.note?.text ?: ""
        mutableStateOf(text)
    }

    val lastUpdateTimestamp = remember(editNoteState) {
        (editNoteState as? EditNoteState.NoteReady)?.note?.lastUpdateTimestamp ?: 0
    }

    val isUpdateButtonEnabled =
        editNoteState is EditNoteState.NoteReady && title.isNotBlank() && text.isNotBlank()

    EditNoteScreen(
        onBackButtonClicked = { editNoteViewModel.cancelNoteEdit(title, text) },
        onDeleteNoteButtonClicked = editNoteViewModel::deleteNote,
        isUpdateNoteButtonEnabled = isUpdateButtonEnabled,
        onUpdateNoteButtonClicked = { editNoteViewModel.updateNote(title, text) },
        isNoteContentVisible = editNoteState is EditNoteState.NoteReady,
        title = title,
        onTitleChanged = { title = it },
        text = text,
        onTextChanged = { text = it },
        lastUpdateTimestamp = lastUpdateTimestamp
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EditNoteScreen(
    onBackButtonClicked: () -> Unit,
    onDeleteNoteButtonClicked: () -> Unit,
    isUpdateNoteButtonEnabled: Boolean,
    onUpdateNoteButtonClicked: () -> Unit,
    isNoteContentVisible: Boolean,
    title: String,
    onTitleChanged: (String) -> Unit,
    text: String,
    onTextChanged: (String) -> Unit,
    lastUpdateTimestamp: Long,
) {
    //BackHandler throws an exception if LocalOnBackPressedDispatcherOwner.current is null, and in preview, it is the case
    if (LocalOnBackPressedDispatcherOwner.current != null) {
        BackHandler(onBack = onBackButtonClicked)
    }
    Scaffold(topBar = {
        CreateNoteTopBar(
            onBackButtonClicked = onBackButtonClicked,
            isUpdateNoteButtonEnabled = isUpdateNoteButtonEnabled,
            onUpdateNoteButtonClicked = onUpdateNoteButtonClicked,
            onDeleteNoteButtonClicked = onDeleteNoteButtonClicked
        )
    }) {
        AnimatedVisibility(
            visible = isNoteContentVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column {
                NoteContent(
                    title = title,
                    onTitleChanged = onTitleChanged,
                    text = text,
                    onTextChanged = onTextChanged,
                    modifier = Modifier.weight(1f),
                )
                NoteLastUpdateTimestamp(
                    lastUpdateTimestamp = lastUpdateTimestamp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun NoteLastUpdateTimestamp(lastUpdateTimestamp: Long, modifier: Modifier = Modifier) {
    val timestampPrettyString = remember(lastUpdateTimestamp) {
        timestampToPrettyString(lastUpdateTimestamp)
    }
    val lastUpdateTimestampText = stringResource(
        R.string.edit_note_last_update_timestamp,
        timestampPrettyString
    )
    Text(
        text = lastUpdateTimestampText,
        modifier = modifier,
        style = MaterialTheme.typography.caption
    )
}

