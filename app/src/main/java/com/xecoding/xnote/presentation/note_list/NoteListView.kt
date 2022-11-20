package com.xecoding.xnote.presentation.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xecoding.xnote.presentation.Screen

@Composable
fun NoteListView(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val notes = viewModel.notes.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(notes) { note ->
                NoteListItem(
                    note = note,
                    onItemClicked = {
                        navController.navigate(Screen.NoteEditScreen.route + "/${note.id}")
                    },
                    onDeleteClicked = {

                    }
                )
            }
        }
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.NoteEditScreen.route + "/0")
            },
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Delete note"
            )
        }

    }
}