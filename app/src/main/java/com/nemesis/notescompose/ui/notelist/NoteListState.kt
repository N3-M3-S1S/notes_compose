package com.nemesis.notescompose.ui.notelist

import com.nemesis.notescompose.domain.Note

sealed class NoteListState {
    object NotesLoading: NoteListState()
    data class NotesLoaded(val notes: List<Note>): NoteListState()
}
