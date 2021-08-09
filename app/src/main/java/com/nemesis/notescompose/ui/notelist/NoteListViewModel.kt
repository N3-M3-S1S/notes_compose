package com.nemesis.notescompose.ui.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.domain.NoteService
import com.nemesis.notescompose.ui.navigation.CreateNoteDestination
import com.nemesis.notescompose.ui.navigation.EditNoteDestination
import com.nemesis.notescompose.ui.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteService: NoteService,
    private val navigator: Navigator
) : ViewModel() {
    val noteListState = noteService.getNotes()
        .map { NoteListState.NotesLoaded(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NoteListState.NotesLoading)

    private val _noteSelectionState: MutableStateFlow<NoteSelectionState> =
        MutableStateFlow(NoteSelectionState.SelectionDisabled)
    val noteSelectionState: StateFlow<NoteSelectionState> = _noteSelectionState

    init {
        noteListState.map {
            if (it is NoteListState.NotesLoaded && it.notes.isNotEmpty()) {
                NoteSelectionState.SelectionInactive
            } else {
                NoteSelectionState.SelectionDisabled
            }
        }.onEach {
            _noteSelectionState.value = it
        }.launchIn(viewModelScope)
    }

    fun noteClicked(note: Note) {
        if (_noteSelectionState.value is NoteSelectionState.SelectionActive) {
            toggleNoteSelection(note)
        } else {
            navigator.navigate(EditNoteDestination(note.id))
        }
    }

    private fun toggleNoteSelection(note: Note) {
        val selectedNotes = getSelectedNotes().toMutableList()

        if (selectedNotes.contains(note)) {
            selectedNotes.remove(note)
        } else {
            selectedNotes.add(note)
        }

        _noteSelectionState.value = NoteSelectionState.SelectionActive(selectedNotes)
    }

    fun enableNotesSelection() {
        _noteSelectionState.value = NoteSelectionState.SelectionActive()
    }

    fun cancelNotesSelection() {
        _noteSelectionState.value = NoteSelectionState.SelectionInactive
    }

    fun deleteSelectedNotes() {
        viewModelScope.launch {
            val selectedNotes = getSelectedNotes()
            noteService.deleteNotes(selectedNotes)
        }
    }

    fun createNote() {
        navigator.navigate(CreateNoteDestination)
    }

    private fun getSelectedNotes() =
        (_noteSelectionState.value as? NoteSelectionState.SelectionActive)?.selectedNotes
            ?: error("Not in note selection state. Current state: ${noteSelectionState.value}")

}