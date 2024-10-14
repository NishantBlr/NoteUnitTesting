package com.nkdroid.noteunittesting.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nkdroid.noteunittesting.data.local.Note
import com.nkdroid.noteunittesting.other.Event
import com.nkdroid.noteunittesting.other.Resource
import com.nkdroid.noteunittesting.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _insertNoteStatus = MutableLiveData<Event<Resource<Note>>>()
    val insertNoteStatus: LiveData<Event<Resource<Note>>> = _insertNoteStatus

    fun insertNote(title: String, description: String) {
        if (title.isEmpty() || title.isBlank()) {
            _insertNoteStatus.postValue(Event(Resource.error("Title can't be empty",null)))
            return
        }
        if (description.isEmpty() || description.isBlank()) {
            _insertNoteStatus.postValue(Event(Resource.error("Description can't be empty",null)))
            return
        }
        val note = Note(title = title, description = description)
        insertNoteIntoDb(note)
        _insertNoteStatus.postValue(Event(Resource.success(note)))
    }

    private fun insertNoteIntoDb(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }
}


//ViewModelFactory example
/**class AddNoteViewModelFactory(val args: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Int::class.java)
            .newInstance()
    }
}*/