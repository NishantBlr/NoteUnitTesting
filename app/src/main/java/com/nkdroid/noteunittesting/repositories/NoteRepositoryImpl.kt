package com.nkdroid.noteunittesting.repositories

import androidx.lifecycle.LiveData
import com.nkdroid.noteunittesting.data.local.Note
import com.nkdroid.noteunittesting.data.local.NoteDao
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao): NoteRepository {

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override fun observeAllNotes(): LiveData<List<Note>> {
        return noteDao.observeAllNotes()
    }

    override fun observeTotalNotesCount(): LiveData<Int> {
        return noteDao.observeTotalNotesCount()
    }
}