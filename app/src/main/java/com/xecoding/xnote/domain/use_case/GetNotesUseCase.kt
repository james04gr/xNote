package com.xecoding.xnote.domain.use_case

import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.repository.NoteRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Flowable<List<Note>> = noteRepository.getNotes()
}