package com.nkdroid.noteunittesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nkdroid.noteunittesting.getOrAwaitValueTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var noteItemDatabase: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup() {
        noteItemDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = noteItemDatabase.noteDao()
    }

    @After
    fun teardown() {
        noteItemDatabase.close()
    }

    @Test
    fun insertShoppingItem()  = runTest{
        val note = Note(1, "Grocery", "rice, masala")
        dao.insertNote(note)

        val allNotes = dao.observeAllNotes().getOrAwaitValueTest()
        assertThat(allNotes).contains(note)
    }

    @Test
    fun deleteShoppingItem()  = runTest{
        val note = Note(1, "Grocery", "rice, masala")
        dao.insertNote(note)
        dao.deleteNote(note)

        val allNotes = dao.observeAllNotes().getOrAwaitValueTest()
        assertThat(allNotes).doesNotContain(note)
    }

    @Test
    fun observeTotalNotesCount()  = runTest{
        val note1 = Note(1, "Grocery", "rice, masala")
        val note2 = Note(2, "Grocery", "rice, masala")
        dao.insertNote(note1)
        dao.insertNote(note2)

        val allNotes = dao.observeTotalNotesCount().getOrAwaitValueTest()
        assertThat(allNotes).isEqualTo(2)
    }
}