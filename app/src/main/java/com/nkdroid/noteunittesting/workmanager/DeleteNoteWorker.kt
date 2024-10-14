package com.nkdroid.noteunittesting.workmanager

import android.content.Context
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nkdroid.noteunittesting.data.local.NoteDao
import com.nkdroid.noteunittesting.data.local.NoteDatabase
import com.nkdroid.noteunittesting.other.Constants

class DeleteNoteWorker(val appContext: Context, workerParams: WorkerParameters): CoroutineWorker(appContext, workerParams) {

    private lateinit var noteDao: NoteDao

    override suspend fun doWork(): Result {
        noteDao = Room.databaseBuilder(appContext, NoteDatabase::class.java, Constants.DATABASE_NAME).build().noteDao()
        try {
            noteDao.getAllNotes().forEach {
                noteDao.deleteNote(it)
            }
            println("All notes deleted")
            return Result.success()
        } catch (e: Exception) {
            println("Notes deletion failed: ${e.localizedMessage}")
            return Result.failure()
        }
    }
}