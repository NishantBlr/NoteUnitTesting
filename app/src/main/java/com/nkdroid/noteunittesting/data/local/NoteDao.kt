package com.nkdroid.noteunittesting.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM notes")
    fun observeAllNotes(): LiveData<List<Note>>

    @Query("SELECT COUNT(id) FROM notes")
    fun observeTotalNotesCount(): LiveData<Int>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<Note>
}