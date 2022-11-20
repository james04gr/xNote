package com.xecoding.xnote.presentation.note_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.use_case.GetNotesUseCase
import com.xecoding.xnote.domain.use_case.GetSingleNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val getSingleNoteUseCase: GetSingleNoteUseCase
) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    private val _notes = mutableStateOf<List<Note>>(emptyList())
    val notes = _notes

    init {
        getNotes()
    }

    fun getNotes() = getNotesUseCase()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            it?.let {
                _notes.value = it
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
}