package com.xecoding.xnote.data.repository

import com.xecoding.xnote.data.datasource.XNoteDatabase
import com.xecoding.xnote.domain.model.Note
import com.xecoding.xnote.domain.repository.NoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val xNoteDatabase: XNoteDatabase
): NoteRepository {

    override fun insert(note: Note) =
        xNoteDatabase.noteDao().insert(note)

    override fun getNotes(): Flowable<List<Note>> =
        xNoteDatabase.noteDao().getNotes()

    override fun getNote(noteId: Long): Single<Note> =
        xNoteDatabase.noteDao().getNote(noteId)

    override fun deleteNote(note: Note): Completable =
        xNoteDatabase.noteDao().delete(note)

    override fun deleteNotes(): Completable =
        xNoteDatabase.noteDao().deleteNotes()

}