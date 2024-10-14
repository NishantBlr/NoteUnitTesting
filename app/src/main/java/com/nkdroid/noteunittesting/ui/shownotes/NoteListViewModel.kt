package com.nkdroid.noteunittesting.ui.shownotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nkdroid.noteunittesting.data.local.Note
import com.nkdroid.noteunittesting.other.Event
import com.nkdroid.noteunittesting.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val noteListLiveData = repository.observeAllNotes()

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}