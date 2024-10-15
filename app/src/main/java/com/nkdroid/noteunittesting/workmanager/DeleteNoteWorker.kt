package com.nkdroid.noteunittesting.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.nkdroid.noteunittesting.data.local.NoteDao
import com.nkdroid.noteunittesting.data.local.NoteDatabase
import com.nkdroid.noteunittesting.other.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DeleteNoteWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    @Assisted val noteDao: NoteDao
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        try {
            noteDao.getAllNotes().forEach {
                noteDao.deleteNote(it)
            }
            Log.d("DeleteNoteWorker", "All notes deleted")
            return Result.success()
        } catch (e: Exception) {
            Log.d("DeleteNoteWorker", "Notes deletion failed: ${e.localizedMessage}")
            return Result.failure()
        }
    }
}