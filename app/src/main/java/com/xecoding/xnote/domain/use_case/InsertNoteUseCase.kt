package com.xecoding.xnote.domain.use_case

import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note: Note) = noteRepository.insert(note)
}