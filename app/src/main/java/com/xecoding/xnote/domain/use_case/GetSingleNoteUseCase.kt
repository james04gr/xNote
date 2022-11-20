package com.xecoding.xnote.domain.use_case

import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.repository.NoteRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSingleNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(noteId: Long): Single<Note> = noteRepository.getNote(noteId)
}