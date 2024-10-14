package com.nkdroid.noteunittesting.compose.ui.notelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nkdroid.noteunittesting.data.local.Note

@Composable
fun NoteListScreen(
    notes: List<Note>
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note)
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(end = 10.dp, bottom = 50.dp)
                .align(Alignment.BottomEnd),
            onClick = { /*TODO*/ },
            containerColor = Color.Green,
            contentColor = Color.White
            ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
}