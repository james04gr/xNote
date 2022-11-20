package com.xecoding.xnote.presentation.note

sealed class EditNoteEvent {
    data class TitleChanged(val value: String): EditNoteEvent()
    data class DescriptionChanged(val value: String): EditNoteEvent()
    data class ColorChanged(val value: Int): EditNoteEvent()
    object SaveNote: EditNoteEvent()
}