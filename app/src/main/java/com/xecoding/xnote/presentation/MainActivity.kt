package com.xecoding.xnote.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xecoding.xnote.presentation.note.NoteEditView
import com.xecoding.xnote.presentation.note_list.NoteListView
import com.xecoding.xnote.presentation.ui.theme.XNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NoteListScreen.route,
                        builder = {
                            composable(
                                route = Screen.NoteListScreen.route
                            ) {
                                NoteListView(navController = navController)
                            }
                            composable(
                                route = Screen.NoteEditScreen.route + "/{noteId}"
                            ) {
                                NoteEditView(navController = navController)
                            }
                        }
                    )
                }
            }
        }
    }
}