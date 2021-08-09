package com.nemesis.notescompose.ui.editnote

import com.nemesis.notescompose.domain.Note

sealed class EditNoteState {
    object NoteNotReady : EditNoteState()
    class NoteReady(val note: Note) : EditNoteState()
}


