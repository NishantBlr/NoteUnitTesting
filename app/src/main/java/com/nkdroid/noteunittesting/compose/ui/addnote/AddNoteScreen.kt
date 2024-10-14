package com.nkdroid.noteunittesting.compose.ui.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nkdroid.noteunittesting.ui.addnote.AddNoteViewModel

@Composable
fun AddNoteScreen(
    addNoteViewModel: AddNoteViewModel
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            text = title,
            label = "Title",
            onTextChange = {
                if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                }) title = it
            }
        )
        NoteTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .defaultMinSize(minHeight = 100.dp),
            text = description,
            label = "Description",
            onTextChange = {
                description = it
            },
            maxLine = 10
        )
        Spacer(modifier = Modifier.weight(1f))
        NotePrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            text = "add note",
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    addNoteViewModel.insertNote(title, description)
                    title = ""
                    description = ""
                }
            }
        )
    }
}