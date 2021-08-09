package com.nemesis.notescompose.data

import android.content.Context
import com.nemesis.notescompose.data.NoteDao
import com.nemesis.notescompose.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao

    @Provides
    @Singleton
    fun providesNoteDatabase(@ApplicationContext context: Context): NoteDatabase =
        NoteDatabase.create(context)
}