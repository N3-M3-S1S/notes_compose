package com.nemesis.notescompose.ui.notelist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.ui.notelist.list.NoteList
import com.nemesis.notescompose.ui.notelist.topbar.NoteListTopBar

@Composable
fun NoteListScreen(noteListViewModel: NoteListViewModel = hiltViewModel()) {
    val noteListState by noteListViewModel.noteListState.collectAsState()
    val noteSelectionState by noteListViewModel.noteSelectionState.collectAsState()

    val isNoteSelectionActiveAndHasSelectedNotes =
        !(noteSelectionState as? NoteSelectionState.SelectionActive)?.selectedNotes.isNullOrEmpty()

    val notes = (noteListState as? NoteListState.NotesLoaded)?.notes ?: emptyList()

    val isNoteSelectionActiveAndNoteIsSelected: (Note) -> Boolean = {
        (noteSelectionState as? NoteSelectionState.SelectionActive)?.selectedNotes?.contains(it)
            ?: false
    }

    NoteListScreen(
        isNoteSelectionButtonEnabled = noteSelectionState !is NoteSelectionState.SelectionDisabled,
        onNoteSelectionButtonClicked = noteListViewModel::enableNotesSelection,
        isNoteSelectionActive = noteSelectionState is NoteSelectionState.SelectionActive,
        onCancelNoteSelectionButtonClicked = noteListViewModel::cancelNotesSelection,
        isDeleteSelectedNotesButtonEnabled = isNoteSelectionActiveAndHasSelectedNotes,
        onDeleteSelectedNotesButtonClicked = noteListViewModel::deleteSelectedNotes,
        onCreateNoteButtonClicked = noteListViewModel::createNote,
        notesLoading = noteListState is NoteListState.NotesLoading,
        notes = notes,
        onNoteClicked = noteListViewModel::noteClicked,
        isNoteSelected = isNoteSelectionActiveAndNoteIsSelected
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteListScreen(
    isNoteSelectionButtonEnabled: Boolean,
    onNoteSelectionButtonClicked: () -> Unit,
    isNoteSelectionActive: Boolean,
    onCancelNoteSelectionButtonClicked: () -> Unit,
    isDeleteSelectedNotesButtonEnabled: Boolean,
    onDeleteSelectedNotesButtonClicked: () -> Unit,
    onCreateNoteButtonClicked: () -> Unit,
    notesLoading: Boolean,
    notes: List<Note>,
    onNoteClicked: (Note) -> Unit,
    isNoteSelected: (Note) -> Boolean
) {
    Scaffold(topBar = {
        NoteListTopBar(
            isNoteSelectionButtonEnabled = isNoteSelectionButtonEnabled,
            noteSelectionButtonClicked = onNoteSelectionButtonClicked,
            activeSelectionButtonsVisible = isNoteSelectionActive,
            onCancelSelectionButtonClicked = onCancelNoteSelectionButtonClicked,
            isDeleteSelectedNotesButtonEnabled = isDeleteSelectedNotesButtonEnabled,
            onDeleteSelectedNotesButtonClicked = onDeleteSelectedNotesButtonClicked
        )
    },
        floatingActionButton = {
            AnimatedVisibility(
                visible = !isNoteSelectionActive && !notesLoading,
                enter = slideInVertically(initialOffsetY = { it * 2 }), //slide up
                exit = slideOutVertically(targetOffsetY = { it * 2 }) //slide down
            ) {
                CreateNoteButton(onClick = onCreateNoteButtonClicked)
            }
        }

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                notesLoading -> NotesLoadingProgressIndicator(modifier = Modifier.align(Alignment.Center))
                notes.isEmpty() -> NoNotesMessage(modifier = Modifier.align(Alignment.Center))
                else -> NoteList(
                    notes = notes,
                    isNoteSelectionActive = isNoteSelectionActive,
                    isNoteSelected = isNoteSelected,
                    onNoteClick = onNoteClicked
                )
            }
        }
    }
}

@Composable
private fun CreateNoteButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, "Create note")
    }
}

@Composable
private fun NoNotesMessage(modifier: Modifier = Modifier) {
    ProvideTextStyle(value = MaterialTheme.typography.h5) {
        Text(text = "No notes", modifier = modifier)
    }
}

@Composable
private fun NotesLoadingProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier)
}
