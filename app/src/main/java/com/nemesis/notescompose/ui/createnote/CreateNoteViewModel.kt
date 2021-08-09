package com.nemesis.notescompose.ui.createnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesis.notescompose.domain.NoteService
import com.nemesis.notescompose.ui.navigation.Navigator
import com.nemesis.notescompose.ui.navigation.NoteListDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteService: NoteService,
    private val navigator: Navigator
) : ViewModel() {

    fun createNote(title: String, text: String) {
        viewModelScope.launch {
            noteService.createNote(title, text)
            navigator.navigate(NoteListDestination())
        }
    }

}