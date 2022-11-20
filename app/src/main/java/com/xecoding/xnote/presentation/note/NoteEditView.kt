package com.xecoding.xnote.presentation.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.model.Note.Companion.toColor
import com.xecoding.xnote.domain.model.Note.Companion.toInt
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NoteEditView(
    navController: NavController,
    viewModel: NoteEditViewModel = hiltViewModel()
) {
    val note = viewModel.note.value
    val noteTitle = viewModel.noteTitle.value
    val noteDescription = viewModel.noteDescription.value
    val noteColor = viewModel.noteColor.value

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest {
            when (it) {
                is NoteEditViewModel.UiEvent.SaveNote ->
                    navController.navigateUp()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(noteColor.toColor())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.colorList.forEach {
                    FloatingActionButton(
                        backgroundColor = it,
                        onClick = {
                            viewModel.onEvent(EditNoteEvent.ColorChanged(it.toInt()))
                        }
                    ) {
                        note?.let { note ->
                            if (noteColor.toColor() == it)
                                Icon(imageVector = Icons.Default.Check, contentDescription = "Checked")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            BasicTextField(
                value = noteTitle,
                onValueChange = {
                    viewModel.onEvent(EditNoteEvent.TitleChanged(it))
                },
                textStyle = MaterialTheme.typography.h5
            )
            Divider(modifier = Modifier.height(1.dp).fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))
            BasicTextField(
                value = noteDescription,
                onValueChange = {
                    viewModel.onEvent(EditNoteEvent.DescriptionChanged(it))
                },
                textStyle = MaterialTheme.typography.body2
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                viewModel.onEvent(EditNoteEvent.SaveNote)
            }
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Save note")
        }
    }
}

private fun validateNote(title: String, description: String): Boolean {
    if (title.isBlank() || description.isBlank()) return false
    return true
}