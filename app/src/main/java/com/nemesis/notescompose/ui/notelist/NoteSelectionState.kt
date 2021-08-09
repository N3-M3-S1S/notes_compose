package com.nemesis.notescompose.ui.notelist

import com.nemesis.notescompose.domain.Note

sealed class NoteSelectionState {
    object SelectionDisabled : NoteSelectionState()
    object SelectionInactive : NoteSelectionState()
    data class SelectionActive(val selectedNotes: List<Note> = emptyList()) : NoteSelectionState()
}
