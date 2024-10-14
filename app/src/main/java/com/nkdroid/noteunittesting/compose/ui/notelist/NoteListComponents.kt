package com.nkdroid.noteunittesting.compose.ui.notelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nkdroid.noteunittesting.data.local.Note

@Composable
fun NoteRow(
    note: Note
) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clip(RoundedCornerShape(8.dp)),
        shadowElevation = 6.dp,
        color = Color.LightGray
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
        ) {
            Text(text = note.title)
            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), thickness = 1.dp, color = Color.Blue)
            Text(text = note.description)
        }
    }
}