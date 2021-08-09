package com.nemesis.notescompose.ui.notecontent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.nemesis.notescompose.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Surface(modifier = modifier.verticalScroll(scrollState)) {
        Column {
            NoteTitle(
                title = title,
                onTitleChanged = onTitleChanged,
                modifier = Modifier.fillMaxWidth()
            )
            NoteText(
                text = text,
                onTextChanged = onTextChanged,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun NoteTitle(
    title: String,
    onTitleChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NoteContentTextField(
        text = title,
        onTextChanged = onTitleChanged,
        placeholder = stringResource(id = R.string.note_content_title_placeholder),
        textStyle = MaterialTheme.typography.h5,
        modifier = modifier
    )
}

@Composable
private fun NoteText(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    NoteContentTextField(
        text = text,
        onTextChanged = onTextChanged,
        placeholder = stringResource(id = R.string.note_content_text_placeholder),
        textStyle = MaterialTheme.typography.body1,
        modifier = modifier
    )
}

@Composable
private fun NoteContentTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = { Text(text = placeholder, style = textStyle) },
        textStyle = textStyle,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent
        ),
        modifier = modifier,
    )
}

