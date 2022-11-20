package com.xecoding.xnote.domain.repository

import com.xecoding.xnote.domain.model.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface NoteRepository {

    fun insert(note: Note)

    fun getNotes(): Flowable<List<Note>>

    fun getNote(noteId: Long): Single<Note>

    fun deleteNote(note: Note): Completable

    fun deleteNotes(): Completable

}