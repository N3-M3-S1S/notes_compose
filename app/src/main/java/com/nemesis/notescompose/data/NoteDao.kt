package com.nemesis.notescompose.data

import androidx.room.*
import com.nemesis.notescompose.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun save(entity: NoteEntity): Long

    @Update
    suspend fun update(entity: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM NoteEntity WHERE id IN (:ids)")
    suspend fun delete(ids: List<Int>)

    @Query("SELECT * FROM NoteEntity WHERE id = :noteId")
    suspend fun getById(noteId: Int): NoteEntity

    @Query("SELECT * FROM NoteEntity ORDER BY lastUpdateTimestamp DESC")
    fun getAll(): Flow<List<NoteEntity>>

}