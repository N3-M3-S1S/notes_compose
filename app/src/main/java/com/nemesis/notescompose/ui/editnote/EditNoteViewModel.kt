package com.nemesis.notescompose.ui.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.domain.NoteService
import com.nemesis.notescompose.ui.navigation.Navigator
import com.nemesis.notescompose.ui.navigation.NoteListDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val noteService: NoteService,
    private val navigator: Navigator,
) : ViewModel() {
    private val _editNoteState: MutableStateFlow<EditNoteState> =
        MutableStateFlow(EditNoteState.NoteNotReady)
    val editNoteState: StateFlow<EditNoteState> = _editNoteState

    private val _showDiscardNoteChangesConfirmation = MutableStateFlow(false)
    val showDiscardNoteChangesConfirmation: StateFlow<Boolean> = _showDiscardNoteChangesConfirmation

    fun loadNote(noteId: Int) {
        if (needLoadNote(noteId)) {
            viewModelScope.launch {
                val note = noteService.getNote(noteId)
                _editNoteState.value = EditNoteState.NoteReady(note)
            }
        }
    }

    private fun needLoadNote(noteId: Int): Boolean {
        val currentState = _editNoteState.value
        return currentState is EditNoteState.NoteNotReady || currentState is EditNoteState.NoteReady && currentState.note.id != noteId
    }

    fun updateNote(title: String, text: String) {
        viewModelScope.launch {
            val noteId = getLoadedNote().id
            noteService.updateNote(title, text, noteId)
            navigateToNoteList()
        }
    }

    fun cancelNoteEdit(title: String, text: String) {
        if (noteContentChanged(title, text)) {
            _showDiscardNoteChangesConfirmation.value = true
        } else {
            navigateToNoteList()
        }
    }

    private fun noteContentChanged(title: String, text: String): Boolean {
        return if (editNoteState.value is EditNoteState.NoteReady) {
            val note = getLoadedNote()
            note.title != title || note.text != text
        } else
            false
    }

    fun deleteNote() {
        viewModelScope.launch {
            val note = getLoadedNote()
            noteService.deleteNote(note)
            navigateToNoteList()
        }
    }

    fun discardNoteChanges() {
        navigateToNoteList()
    }

    private fun navigateToNoteList() {
        navigator.navigate(NoteListDestination())
    }

    private fun getLoadedNote(): Note = when (editNoteState.value) {
        EditNoteState.NoteNotReady -> error("Note is not loaded")
        is EditNoteState.NoteReady -> (editNoteState.value as EditNoteState.NoteReady).note
    }

    fun discardNoteChangesConfirmationCanceled() {
        _showDiscardNoteChangesConfirmation.value = false
    }
}