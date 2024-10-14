package com.nkdroid.noteunittesting.compose

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nkdroid.noteunittesting.compose.ui.addnote.AddNoteScreen
import com.nkdroid.noteunittesting.compose.ui.theme.NoteUnitTestingTheme
import com.nkdroid.noteunittesting.other.Status
import com.nkdroid.noteunittesting.ui.MainActivity
import com.nkdroid.noteunittesting.ui.addnote.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposePracticeActivity : ComponentActivity() {
    val addNoteViewModel: AddNoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteUnitTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddNoteScreen(addNoteViewModel)
                    attachObserver()
                }
            }
        }
    }

    private fun attachObserver() {
        addNoteViewModel.insertNoteStatus.observe(this) {
            when(it.getContentIfNotHandled()?.status) {
                Status.ERROR -> {
                    Toast.makeText(this, it.peekContent().message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }

                else -> {}
            }
        }
    }
}