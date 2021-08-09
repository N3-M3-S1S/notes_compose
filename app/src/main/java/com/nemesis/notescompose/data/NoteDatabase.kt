package com.nemesis.notescompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context): NoteDatabase = Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase::class.simpleName!!
        ).build()
    }

    abstract val noteDao: NoteDao

}