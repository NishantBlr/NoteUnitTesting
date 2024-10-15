package com.nkdroid.noteunittesting

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.nkdroid.noteunittesting.data.local.Note
import com.nkdroid.noteunittesting.data.local.NoteDao
import com.nkdroid.noteunittesting.workmanager.DeleteNoteWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NoteApplication: Application(), Configuration.Provider {

    @Inject
    lateinit var deleteNoteWorkerFactory: DeleteNoteWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(deleteNoteWorkerFactory)
            .build()
}

class DeleteNoteWorkerFactory @Inject constructor(private val noteDao: NoteDao): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = DeleteNoteWorker(appContext, workerParameters, noteDao)

}