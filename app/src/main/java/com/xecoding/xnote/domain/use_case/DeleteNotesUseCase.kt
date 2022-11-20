package com.xecoding.xnote.domain.use_case

import com.xecoding.xnote.domain.repository.NoteRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(): Completable = noteRepository.deleteNotes()
}