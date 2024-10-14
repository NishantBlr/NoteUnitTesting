package com.nkdroid.noteunittesting.repositories

import androidx.lifecycle.LiveData
import com.nkdroid.noteunittesting.data.local.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun observeAllNotes(): LiveData<List<Note>>

    fun observeTotalNotesCount(): LiveData<Int>
}