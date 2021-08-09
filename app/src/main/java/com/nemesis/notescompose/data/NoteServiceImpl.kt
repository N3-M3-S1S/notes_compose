package com.nemesis.notescompose.data

import com.nemesis.notescompose.domain.Note
import com.nemesis.notescompose.domain.NoteService
import com.nemesis.notescompose.getCurrentTimestamp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteServiceImpl @Inject constructor(private val noteDao: NoteDao) : NoteService {

    override suspend fun createNote(title: String, text: String) {
        val noteEntity = NoteEntity(title.trim(), text.trim(), getCurrentTimestamp())
        noteDao.save(noteEntity)
    }

    override suspend fun updateNote(title: String, text: String, id: Int) {
        val noteEntity = NoteEntity(title.trim(), text.trim(), getCurrentTimestamp())
        noteEntity.id = id
        noteDao.update(noteEntity)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note.id)
    }

    override suspend fun deleteNotes(notes: List<Note>) {
        val notesIds = notes.map(Note::id)
        noteDao.delete(notesIds)
    }

    override suspend fun getNote(noteId: Int): Note {
        return noteDao.getById(noteId).toNote()
    }

    override fun getNotes(): Flow<List<Note>> =
        noteDao.getAll().map { noteEntities ->
            noteEntities.map(NoteEntity::toNote)
        }
}