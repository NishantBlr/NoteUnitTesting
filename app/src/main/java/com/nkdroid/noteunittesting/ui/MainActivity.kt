package com.nkdroid.noteunittesting.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.nkdroid.noteunittesting.R
import com.nkdroid.noteunittesting.databinding.ActivityMainBinding
import com.nkdroid.noteunittesting.ui.addnote.AddNoteFragment
import com.nkdroid.noteunittesting.ui.shownotes.FragmentNoteList
import com.nkdroid.noteunittesting.workmanager.DeleteNoteWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.flContainer, FragmentNoteList.newInstance(), "addNote")
        ft.commitAllowingStateLoss()

        initWorkManager()
    }

    private fun initWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

//        val deleteNoteWorkerOneTime = OneTimeWorkRequestBuilder<DeleteNoteWorker>()
//            .setInitialDelay(5, TimeUnit.MINUTES)
//            .setConstraints(constraints)
//            .build()
//        WorkManager.getInstance(this).enqueue(deleteNoteWorkerOneTime)

        val deleteNoteWorkerPeriodic = PeriodicWorkRequest.Builder(DeleteNoteWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(this).enqueue(deleteNoteWorkerPeriodic)
    }
}