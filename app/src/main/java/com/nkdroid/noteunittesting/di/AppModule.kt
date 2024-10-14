package com.nkdroid.noteunittesting.di

import android.content.Context
import androidx.room.Room
import com.nkdroid.noteunittesting.data.local.NoteDao
import com.nkdroid.noteunittesting.data.local.NoteDatabase
import com.nkdroid.noteunittesting.other.Constants
import com.nkdroid.noteunittesting.repositories.NoteRepository
import com.nkdroid.noteunittesting.repositories.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NoteDatabase::class.java, Constants.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideNoteDao(
        noteDatabase: NoteDatabase
    ) = noteDatabase.noteDao()

    @Provides
    @Singleton
    fun getNoteRepository(noteDao: NoteDao) = NoteRepositoryImpl(noteDao) as NoteRepository
}