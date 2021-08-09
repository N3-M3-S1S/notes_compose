package com.nemesis.notescompose.ui.notelist.list.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.timestampToPrettyString

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteListItem(
    note: Note,
    isNoteSelectionActive: Boolean,
    isNoteSelected: Boolean,
    onNoteClick: (Note) -> Unit
) {
    Surface(modifier = Modifier.clickable { onNoteClick(note) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AnimatedVisibility(
                visible = isNoteSelectionActive,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Row {
                    SelectedNoteCheckbox(checked = isNoteSelected)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                NoteTitle(title = note.title)
                NoteText(text = note.text)
                NoteLastUpdateTimestamp(timestamp = note.lastUpdateTimestamp)
            }
        }
    }
}

@Composable
private fun SelectedNoteCheckbox(checked: Boolean) {
    Checkbox(checked = checked, onCheckedChange = null)
}

@Composable
private fun NoteTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun NoteText(text: String) {
    Text(
        text = text,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body2
    )
}

@Composable
private fun NoteLastUpdateTimestamp(timestamp: Long) {
    val timestampPrettyString = remember(timestamp) { timestampToPrettyString(timestamp) }
    Text(text = timestampPrettyString, style = MaterialTheme.typography.caption)
}

