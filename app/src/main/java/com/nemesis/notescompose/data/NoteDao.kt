package com.nemesis.notescompose.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun save(entity: NoteEntity): Long

    @Update
    suspend fun update(entity: NoteEntity)

    @Query("DELETE FROM NoteEntity WHERE id = :noteId")
    suspend fun delete(noteId: Int)

    @Query("DELETE FROM NoteEntity WHERE id IN (:noteIds)")
    suspend fun delete(noteIds: List<Int>)

    @Query("SELECT * FROM NoteEntity WHERE id = :noteId")
    suspend fun getById(noteId: Int): NoteEntity

    @Query("SELECT * FROM NoteEntity ORDER BY lastUpdateTimestamp DESC")
    fun getAll(): Flow<List<NoteEntity>>

}