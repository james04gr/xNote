package com.xecoding.xnote.presentation

sealed class Screen(val route: String) {
    object NoteListScreen: Screen("note_list_screen")
    object NoteEditScreen: Screen("note_edit_screen")
}
