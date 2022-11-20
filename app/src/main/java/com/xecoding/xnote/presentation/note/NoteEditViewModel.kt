package com.xecoding.xnote.presentation.note

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.model.Note.Companion.toInt
import com.xecoding.xnote.domain.use_case.GetSingleNoteUseCase
import com.xecoding.xnote.domain.use_case.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    private val getSingleNoteUseCase: GetSingleNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val _note = mutableStateOf<Note?>(null)
    val note = _note

    private val _noteTitle = mutableStateOf("")
    val noteTitle = _noteTitle
    private val _noteDescription = mutableStateOf("")
    val noteDescription = _noteDescription
    private val _noteColor = mutableStateOf(Note.colorList[0].toInt())
    val noteColor = _noteColor

    var currentNoteId: Long? = null

    init {
        savedStateHandle.get<String>("noteId")?.let {
            if (it != "0") {
                currentNoteId = it.toLong()
                getSingleNote(it.toLong())
            }
        }
    }

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    fun onEvent(event: EditNoteEvent) {
        when (event) {
            is EditNoteEvent.TitleChanged -> _noteTitle.value = event.value
            is EditNoteEvent.DescriptionChanged -> _noteDescription.value = event.value
            is EditNoteEvent.ColorChanged -> _noteColor.value = event.value
            is EditNoteEvent.SaveNote -> {
                val newNote = if (currentNoteId == null) {
                    Note(
                        id = -1,
                        title = _noteTitle.value,
                        description = _noteDescription.value,
                        color = _noteColor.value,
                        type = 1,
                        phone = "6934289774",
                        date = "2022-11-17 19:13:00"
                    )
                } else {
                    _note.value?.copy(
                        title = _noteTitle.value,
                        description = _noteDescription.value,
                        color = _noteColor.value
                    )
                }

                viewModelScope.launch(Dispatchers.IO) {
                    insertNoteUseCase(newNote!!)
                    _event.emit(UiEvent.SaveNote)
                }
            }
        }
    }

    fun getSingleNote(noteId: Long) = getSingleNoteUseCase(noteId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it?.let {
                _note.value = it
                _noteTitle.value = it.title
                _noteDescription.value = it.description
                _noteColor.value = it.color
            }
        }, {

        }).let {
            compositeDisposable.add(it)
        }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

    sealed class UiEvent {
        object SaveNote: UiEvent()
    }

}