package com.nemesis.notescompose.domain

import kotlinx.coroutines.flow.Flow

interface NoteService {
    suspend fun createNote(title: String, text: String)
    suspend fun updateNote(title: String, text: String, noteId: Int)
    suspend fun deleteNote(note: Note)
    suspend fun deleteNotes(notes: List<Note>)
    suspend fun getNote(noteId: Int): Note
    fun getNotes(): Flow<List<Note>>
}