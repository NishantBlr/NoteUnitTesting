package com.nkdroid.noteunittesting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nkdroid.noteunittesting.data.local.Note

class FakeNoteRepository : NoteRepository {

    private val noteItems = mutableListOf<Note>()
    private val observableNoteItems = MutableLiveData<List<Note>>()
    private val observableTotalNotesCount = MutableLiveData<Int>()

    private fun refreshLiveData() {
        observableNoteItems.postValue(noteItems)
        observableTotalNotesCount.postValue(noteItems.size)
    }

    override suspend fun insertNote(note: Note) {
        noteItems.add(note)
        refreshLiveData()
    }

    override suspend fun deleteNote(note: Note) {
        noteItems.remove(note)
        refreshLiveData()
    }

    override fun observeAllNotes(): LiveData<List<Note>> {
        return observableNoteItems
    }

    override fun observeTotalNotesCount(): LiveData<Int> {
        return observableTotalNotesCount
    }

}