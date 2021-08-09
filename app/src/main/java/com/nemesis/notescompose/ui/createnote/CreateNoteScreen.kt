package com.nemesis.notescompose.ui.createnote

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.*
import com.nemesis.notescompose.ui.notecontent.NoteContent
import com.nemesis.notescompose.ui.createnote.topbar.CreateNoteTopBar

@Composable
fun CreateNoteScreen(createNoteViewModel: CreateNoteViewModel = hiltViewModel()) {
    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
    var title by rememberSaveable { mutableStateOf("") }
    var text by rememberSaveable { mutableStateOf("") }
    val isCreateNoteButtonEnabled = title.isNotBlank() and text.isNotBlank()

    CreateNoteScreen(
        onBackButtonClicked = backPressDispatcher::onBackPressed,
        isCreateNoteButtonEnabled = isCreateNoteButtonEnabled,
        onCreateNoteButtonClicked = { createNoteViewModel.createNote(title, text) },
        title = title,
        onTitleChanged = { title = it },
        text = text,
        onTextChanged = { text = it }
    )
}

@Composable
fun CreateNoteScreen(
    onBackButtonClicked: () -> Unit,
    isCreateNoteButtonEnabled: Boolean,
    onCreateNoteButtonClicked: () -> Unit,
    title: String,
    onTitleChanged: (String) -> Unit,
    text: String,
    onTextChanged: (String) -> Unit,
) {
    Scaffold(topBar = {
        CreateNoteTopBar(
            onBackButtonClicked = onBackButtonClicked,
            isCreateNoteButtonEnabled = isCreateNoteButtonEnabled,
            onCreateNoteButtonClicked = onCreateNoteButtonClicked,
        )
    }) {
        NoteContent(
            title = title,
            onTitleChanged = onTitleChanged,
            text = text,
            onTextChanged = onTextChanged
        )
    }
}